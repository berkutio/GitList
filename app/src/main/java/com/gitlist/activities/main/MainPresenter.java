package com.gitlist.activities.main;

import android.content.res.Resources;
import com.arellomobile.mvp.InjectViewState;
import com.gitlist.BasePresenter;
import com.gitlist.model.PresenterResult;
import com.gitlist.model.testmodel.MovieResp;
import com.gitlist.network.ServiceGitHub;
import com.gitlist.rx.SchedulerProvider;

import javax.inject.Inject;
import io.reactivex.observers.DisposableSingleObserver;

@InjectViewState
public class MainPresenter extends BasePresenter<MainView> {


    private ServiceGitHub serviceGitHub;
    private SchedulerProvider schedulerProvider;
    @Inject
    public MainPresenter(ServiceGitHub serviceGitHub, SchedulerProvider schedulerProvider, Resources resources) {
        this.serviceGitHub = serviceGitHub;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getFirstRepos();
    }

    public void getFirstRepos(){
        DisposableSingleObserver<MovieResp> disposableSingleObserver = serviceGitHub.getReposList()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.mainThread())
                .doOnError(this::handleError)
                .subscribeWith(new DisposableSingleObserver<MovieResp>() {
                    @Override
                    public void onSuccess(MovieResp value) {
                        getViewState().onFirstRepoUpdate(new PresenterResult<>(value));
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().onFirstRepoUpdate(new PresenterResult<>(e.getMessage()));
                    }
                });
        compositeDisposable.add(disposableSingleObserver);
        }
    }


