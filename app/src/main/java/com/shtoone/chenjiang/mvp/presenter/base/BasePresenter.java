package com.shtoone.chenjiang.mvp.presenter.base;


import android.support.annotation.UiThread;

import com.shtoone.chenjiang.common.RxManager;
import com.shtoone.chenjiang.mvp.contract.base.BaseContract;
import com.socks.library.KLog;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import rx.Subscriber;

/**
 * Author：leguang on 2016/10/9 0009 10:31
 * Email：langmanleguang@qq.com
 */
public abstract class BasePresenter<V extends BaseContract.View> {
    public Reference<V> mViewReference;

    //每一套mvp应该拥有一个独立的RxManager
    public RxManager mRxManager = new RxManager();

    public BasePresenter(V mView) {
        attachView(mView);
    }

    public void attachView(V view) {
        mViewReference = new WeakReference<V>(view);
    }

    public V getView() {
        return mViewReference == null ? null : mViewReference.get();
    }

    @UiThread
    public boolean isViewAttached() {
        return mViewReference != null && mViewReference.get() != null;
    }

    @UiThread
    public void detachView() {
        mRxManager.clear();
        if (mViewReference != null) {
            mViewReference.clear();
            mViewReference = null;
        }
    }

    public abstract class RxSubscriber<T> extends Subscriber<T> {

        @Override
        public void onStart() {
            super.onStart();
            KLog.e("onStart");
            getView().showLoading();
        }

        @Override
        public void onNext(T t) {
            KLog.e("onNext");
            _onNext(t);
        }


        @Override
        public void onCompleted() {
            KLog.e("onCompleted");
            getView().showContent();
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            //此处不考虑错误类型，笼统的以错误来介绍
            KLog.e("onError::" + e);
            getView().showError(e);
        }

        public abstract void _onNext(T t);

    }
}
