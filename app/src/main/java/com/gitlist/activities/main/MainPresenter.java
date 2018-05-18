package com.gitlist.activities.main;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.gitlist.BasePresenter;
import com.gitlist.network.ServiceGitHub;

import javax.inject.Inject;

@InjectViewState
public class MainPresenter extends BasePresenter<MainView> {

    private ServiceGitHub serviceGitHub;

    @Inject
    public MainPresenter(ServiceGitHub serviceGitHub) {
        this.serviceGitHub = serviceGitHub;
        Log.e("myLogs", "serviceGitHub " + serviceGitHub);
    }

    public void test(){
        getViewState().test();
    }

}
