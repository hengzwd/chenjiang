package com.shtoone.chenjiang.mvp.view.main.upload;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.shtoone.chenjiang.R;
import com.shtoone.chenjiang.common.Constants;
import com.shtoone.chenjiang.mvp.contract.upload.OriginalDataContract;
import com.shtoone.chenjiang.mvp.model.entity.db.OriginalData;
import com.shtoone.chenjiang.mvp.presenter.upload.OriginalDataPresenter;
import com.shtoone.chenjiang.mvp.view.adapter.OriginalDataRVAdapter;
import com.shtoone.chenjiang.mvp.view.base.BaseFragment;
import com.shtoone.chenjiang.common.ToastUtils;
import com.shtoone.chenjiang.widget.PageStateLayout;
import com.socks.library.KLog;

import java.net.ConnectException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class OriginalDataFragment extends BaseFragment<OriginalDataContract.Presenter> implements OriginalDataContract.View {

    private static final String TAG = OriginalDataFragment.class.getSimpleName();
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.pagestatelayout)
    PageStateLayout pagestatelayout;
    @BindView(R.id.ptrframelayout)
    PtrFrameLayout ptrframelayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private OriginalDataRVAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private int lastVisibleItemPosition;
    private View mFooterLoading, mFooterNotLoading, mFooterError;
    private int pagination = 0;
    private boolean isLoading;

    public static OriginalDataFragment newInstance() {
        return new OriginalDataFragment();
    }

    @NonNull
    @Override
    protected OriginalDataContract.Presenter createPresenter() {
        return new OriginalDataPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_measured_data_list, container, false);
        ButterKnife.bind(this, view);
        //这几个view必须在注入view之后，否则(ViewGroup) recyclerview.getParent()会因recyclerview为空而报异常。
        mFooterLoading = getLayoutInflater(savedInstanceState).inflate(R.layout.item_footer_loading, (ViewGroup) recyclerview.getParent(), false);
        mFooterNotLoading = getLayoutInflater(savedInstanceState).inflate(R.layout.item_footer_not_loading, (ViewGroup) recyclerview.getParent(), false);
        mFooterError = getLayoutInflater(savedInstanceState).inflate(R.layout.item_footer_error, (ViewGroup) recyclerview.getParent(), false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    private void initData() {
        setRecyclerview();
        setLoadMore();
        initPageStateLayout(pagestatelayout);
        initPtrFrameLayout(ptrframelayout);
    }

    private void setRecyclerview() {
        //下拉刷新必须得在懒加载里设置，因为下拉刷新是一进来就刷新，启动start()。
        mLinearLayoutManager = new LinearLayoutManager(_mActivity);
        recyclerview.setLayoutManager(mLinearLayoutManager);

        recyclerview.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showToast(_mActivity, Integer.toString(position));
            }
        });
        mAdapter = new OriginalDataRVAdapter();
        mAdapter.removeAllFooterView();
        recyclerview.setAdapter(mAdapter);
    }

    private void setLoadMore() {
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (mAdapter == null) {
                    return;
                }

                //判断分页加载的时机是滑动到底部。
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItemPosition + 1 == mAdapter.getItemCount()
                        //目的是判断第一页数据条数是否满足一整页。
                        && mAdapter.getItemCount() >= Constants.PAGE_SIZE) {
                    if (!isLoading) {
                        isLoading = true;
                        pagination += 1;
                        mPresenter.request(pagination);
                    }
                }

                //滑动到顶部之后就可以隐藏掉FAB了。
                if (newState == RecyclerView.SCROLL_STATE_IDLE && mLinearLayoutManager.findFirstVisibleItemPosition() == 0) {
                    fab.hide();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (mLinearLayoutManager == null) {
                    return;
                }
                lastVisibleItemPosition = mLinearLayoutManager.findLastVisibleItemPosition();

                if (dy > 5) {
                    fab.hide();
                } else if (dy < -5) {
                    fab.show();
                }
            }
        });

        mFooterError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.removeAllFooterView();
                mAdapter.addFooterView(mFooterLoading);
                mPresenter.request(pagination);
            }
        });
    }

    @Override
    public void response(List<OriginalData> mOriginalData, int pagination) {
        KLog.e("mOriginalData::" + mOriginalData.size());
        if (mOriginalData.size() > 0) {
            if (pagination == 0) {
                this.pagination = 0;//说明是第一页，或者是刷新,把页码重置为0，代表第一页。
                mAdapter.removeAllFooterView();
                if (mOriginalData.size() >= Constants.PAGE_SIZE) {
                    mAdapter.addFooterView(mFooterLoading);
                }
                mAdapter.setNewData(mOriginalData);
            } else {
                mAdapter.addData(mOriginalData);
            }
            //靠这个参数控制最后不需要请求数据
            isLoading = false;
        } else {
            if (pagination == 0) {
                pagestatelayout.showEmpty();
            } else {
                //此处一定要先清除之前加载的FooterView，否则会报错。
                mAdapter.removeAllFooterView();
                mAdapter.addFooterView(mFooterNotLoading);
                mAdapter.notifyDataSetChanged();//这里必须要notify一下，否则会报错，因为我修改了footer。
            }
        }
    }

    @Override
    public void showContent() {
        pagestatelayout.showContent();
    }

    @Override
    public void showError(Throwable t) {
        //第一页加载出错，显示方式和第一页以后的部分出错显示不同
        if (pagination == 0) {
            if (t instanceof ConnectException) {
                pagestatelayout.showNetError();
            } else {
                pagestatelayout.showError();
            }
        } else {
            mAdapter.removeAllFooterView();
            mAdapter.addFooterView(mFooterError);
        }
    }

    @Override
    public void showLoading() {
        if (pagination == 0) {
            pagestatelayout.showLoading();
        }
    }


    @OnClick(R.id.fab)
    public void onClick() {
        recyclerview.smoothScrollToPosition(0);
    }

    @Override
    public boolean isCanDoRefresh() {
        //判断RecyclerView是否在在顶部，在顶部则允许滑动下拉刷新。
        if (null != recyclerview && null != mLinearLayoutManager) {
            int position = mLinearLayoutManager.findFirstVisibleItemPosition();
            if (position >= 0) {
                if (mLinearLayoutManager.findViewByPosition(position).getTop() >= 0 && position == 0) {
                    return true;
                }
            }
        } else {
            return true;
        }
        return false;
    }
}
