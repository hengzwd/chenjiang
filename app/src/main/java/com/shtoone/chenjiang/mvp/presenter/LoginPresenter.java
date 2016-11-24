package com.shtoone.chenjiang.mvp.presenter;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.shtoone.chenjiang.BaseApplication;
import com.shtoone.chenjiang.common.Constants;
import com.shtoone.chenjiang.mvp.contract.LoginContract;
import com.shtoone.chenjiang.mvp.model.HttpHelper;
import com.shtoone.chenjiang.mvp.model.entity.bean.UserInfoBean;
import com.shtoone.chenjiang.mvp.presenter.base.BasePresenter;
import com.shtoone.chenjiang.utils.AESCryptUtils;
import com.shtoone.chenjiang.utils.SharedPreferencesUtils;

import java.security.GeneralSecurityException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Author：leguang on 2016/10/14 0014 13:17
 * Email：langmanleguang@qq.com
 */
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    private static final String TAG = LoginPresenter.class.getSimpleName();
    private UserInfoBean mUserInfoBean;


    public LoginPresenter(LoginContract.View mView) {
        super(mView);
    }

    @Override
    public void login(final String username, final String password) {
        String encryptRegisterCode = (String) SharedPreferencesUtils.get(BaseApplication.mContext, Constants.REGISTER_CODE, "");

        //进行解密
        if (TextUtils.isEmpty(encryptRegisterCode)) {
            getView().setErrorMessage("注册码丢失，请重新注册");
            return;
        }

        String registerCode = "";
        try {
            registerCode = AESCryptUtils.decrypt(Constants.ENCRYPT_KEY, encryptRegisterCode);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        HttpHelper.getInstance().initService().login(username, password, Constants.OSTYPE, registerCode).enqueue(new Callback<UserInfoBean>() {
            @Override
            public void onResponse(Call<UserInfoBean> call, Response<UserInfoBean> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 0) {
                        BaseApplication.mUserInfoBean = mUserInfoBean = response.body();
                        savaData(username, password);

                        getView().setSuccessMessage();
                        //进入管理层界面
                        getView().go2Main();
                    } else {
                        getView().setErrorMessage(response.body().getDescription());
                    }
                } else {
                    getView().setErrorMessage("服务器异常");
                }
            }

            @Override
            public void onFailure(Call<UserInfoBean> call, Throwable t) {
                getView().showError(t);
            }
        });
    }

    private void savaData(String username, String password) {
        try {
            String usernameEncrypted = AESCryptUtils.encrypt(Constants.ENCRYPT_KEY, username);
            String passwordEncrypted = AESCryptUtils.encrypt(Constants.ENCRYPT_KEY, password);
            String userIdEncrypted = AESCryptUtils.encrypt(Constants.ENCRYPT_KEY, mUserInfoBean.getUserId());
            String userInfoEncrypted = AESCryptUtils.encrypt(Constants.ENCRYPT_KEY, new Gson().toJson(mUserInfoBean));

            SharedPreferencesUtils.put(BaseApplication.mContext, Constants.USERNAME, usernameEncrypted);
            SharedPreferencesUtils.put(BaseApplication.mContext, Constants.PASSWORD, passwordEncrypted);
            SharedPreferencesUtils.put(BaseApplication.mContext, Constants.USER_ID, userIdEncrypted);
            SharedPreferencesUtils.put(BaseApplication.mContext, Constants.USER_INFO_BEAN, userInfoEncrypted);

        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {

    }


}
