package com.shtoone.chenjiang.mvp.view.others;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.shtoone.chenjiang.R;
import com.shtoone.chenjiang.common.Constants;
import com.shtoone.chenjiang.mvp.contract.LoginContract;
import com.shtoone.chenjiang.mvp.presenter.LoginPresenter;
import com.shtoone.chenjiang.mvp.view.base.BaseFragment;
import com.shtoone.chenjiang.mvp.view.main.MainActivity;
import com.shtoone.chenjiang.utils.KeyBoardUtils;
import com.shtoone.chenjiang.widget.processbutton.iml.ActionProcessButton;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class LoginFragment extends BaseFragment<LoginContract.Presenter> implements LoginContract.View {

    private static final String TAG = LoginFragment.class.getSimpleName();
    @BindView(R.id.et_username_login_fragment)
    TextInputLayout etUsername;
    @BindView(R.id.et_password_login_fragment)
    TextInputLayout etPassword;
    @BindView(R.id.bt_login_fragment)
    ActionProcessButton btLogin;
    @BindView(R.id.fab_login_fragment)
    FloatingActionButton fab;
    @BindView(R.id.cl_login_fragment)
    CoordinatorLayout cl;
    private String username;
    private String password;
    private int fromTo;

    public static LoginFragment newInstance(int fromTo) {
        Bundle args = new Bundle();
        args.putInt(Constants.FROM_TO, fromTo);

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            fromTo = args.getInt(Constants.FROM_TO);
        }
    }

    @NonNull
    @Override
    protected LoginContract.Presenter createPresenter() {
        return new LoginPresenter(this);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (fromTo == Constants.FROM_SPLASH) {
            getFragmentManager().beginTransaction()
                    .show(getPreFragment())
                    .commit();
        }
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //进场动画
        revealShow();
        initData();
    }

    private void initData() {

        etUsername.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btLogin.setProgress(0);
                if (TextUtils.isEmpty(s)) {
                    etUsername.setError("用户名不能为空");
                    etUsername.setErrorEnabled(true);
                } else {
                    etUsername.setError("");
                    etUsername.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        etPassword.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btLogin.setProgress(0);
                if (TextUtils.isEmpty(s)) {
                    etPassword.setError("密码不能为空");
                    etPassword.setErrorEnabled(true);
                } else {
                    etPassword.setError("");
                    etPassword.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick(R.id.bt_login_fragment)
    public void onClick() {
        KeyBoardUtils.hideKeybord(btLogin, _mActivity);
        username = etUsername.getEditText().getText().toString().trim();
        password = etPassword.getEditText().getText().toString().trim();
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            btLogin.setProgress(50);
            mPresenter.login(username, password);

        } else if (TextUtils.isEmpty(username)) {
            etUsername.setErrorEnabled(true);
            etUsername.setError("");
            etUsername.setError("用户名不能为空");
        } else if (TextUtils.isEmpty(password)) {
            etUsername.setErrorEnabled(true);
            etUsername.setError("");
            etPassword.setError("密码不能为空");
        }
    }

    @Override
    public void setErrorMessage(String message) {
        btLogin.setErrorText(message);
        btLogin.setProgress(-1);
    }

    @Override
    public void setSuccessMessage() {
        //按钮提示成功信息
        btLogin.setProgress(100);
    }

    @Override
    public void go2Main() {
        btLogin.postDelayed(new Runnable() {
            @Override
            public void run() {
                _mActivity.startActivity(new Intent(_mActivity, MainActivity.class));
            }
        }, 300);

//        _mActivity.finish();
    }

    @OnClick(R.id.fab_login_fragment)
    public void onClick(View view) {
        fab.hide();
        _mActivity.startActivity(new Intent(_mActivity, RegisterActivity.class));
    }


    @Override
    public void showContent() {

    }

    @Override
    public void onResume() {
        super.onResume();
        //返回到看见此fragment时，fab显示
        fab.show();
    }

    @Override
    public void onPause() {
        super.onPause();
        //防止屏幕旋转后重画时fab显示
        fab.hide();
    }

    @Override
    public void showError(Throwable t) {
        if (t instanceof ConnectException) {
            setErrorMessage("网络异常");
        } else if (t instanceof HttpException) {
            setErrorMessage("服务器异常");
        } else if (t instanceof SocketTimeoutException) {
            setErrorMessage("连接超时");
        } else if (t instanceof JSONException) {
            setErrorMessage("解析异常");
        } else {
            setErrorMessage("数据异常");
        }
    }

    @Override
    public void showLoading() {

    }

    private void revealShow() {
        cl.post(new Runnable() {
            @Override
            public void run() {
                fab.show();
                if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
                    cl.setVisibility(View.VISIBLE);
                    return;
                }

                int cx = (cl.getLeft() + cl.getRight()) / 2;
                int cy = (cl.getTop() + cl.getBottom()) / 2;

                int w = cl.getWidth();
                int h = cl.getHeight();

                // 勾股定理 & 进一法
                int finalRadius = (int) Math.hypot(w, h);

                Animator anim = ViewAnimationUtils.createCircularReveal(cl, cx, cy, 0, finalRadius);
                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        fab.show();
                    }

                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        fab.hide();

                    }
                });
                anim.setInterpolator(new AccelerateDecelerateInterpolator());
                anim.setDuration(1000);
                anim.start();
            }
        });
    }
}


