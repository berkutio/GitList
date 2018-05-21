package com.gitlist;


import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<View extends BaseView> extends MvpPresenter<View> {


    protected CompositeDisposable compositeDisposable = new CompositeDisposable();



    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }


    protected void handleError(Throwable errror){
        getViewState().onErrorHandle(errror);
    }


}
