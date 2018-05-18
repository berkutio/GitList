package com.gitlist.activities.main;

import android.content.res.Resources;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.gitlist.BasePresenter;
import com.gitlist.model.PresenterResult;
import com.gitlist.model.RepoItem;
import com.gitlist.network.ServiceGitHub;
import com.gitlist.rx.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

@InjectViewState
public class MainPresenter extends BasePresenter<MainView> {

    private static int currentPage = 1;

    private ServiceGitHub serviceGitHub;
    private SchedulerProvider schedulerProvider;
    //private Resources resources;

    @Inject
    public MainPresenter(ServiceGitHub serviceGitHub, SchedulerProvider schedulerProvider) {
        this.serviceGitHub = serviceGitHub;
        this.schedulerProvider = schedulerProvider;
        //this.resources = resources;
        //Log.e("myLogs", "MainPresenter res " + resources);
    }

    public void test(){
        getViewState().test();
    }


    public void getFirstRepos(){
        DisposableObserver<List<RepoItem>> disposableObserver = serviceGitHub.getReposList(String.valueOf(currentPage), "15")
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.mainThread()).subscribeWith(new DisposableObserver<List<RepoItem>>() {
                    @Override
                    public void onNext(List<RepoItem> value) {
                        getViewState().onFirstRepoUpdate(new PresenterResult<>(value));
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().onFirstRepoUpdate(new PresenterResult<>(e.getMessage()));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        compositeDisposable.add(disposableObserver);
    }


}
