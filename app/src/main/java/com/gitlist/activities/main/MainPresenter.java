package com.gitlist.activities.main;

import com.arellomobile.mvp.InjectViewState;
import com.gitlist.BasePresenter;

import javax.inject.Inject;

@InjectViewState
public class MainPresenter extends BasePresenter<MainView> {

    @Inject
    public MainPresenter() {

    }

    public void test(){
        getViewState().test();
    }

}
