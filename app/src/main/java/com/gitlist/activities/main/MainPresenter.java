package com.gitlist.activities.main;

import android.content.res.Resources;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.gitlist.BasePresenter;
import com.gitlist.R;
import com.gitlist.model.PresenterResult;
import com.gitlist.model.RepoItem;
import com.gitlist.network.ServiceGitHub;
import com.gitlist.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

@InjectViewState
public class MainPresenter extends BasePresenter<MainView> {

    Map<String, Integer> map = new HashMap<>();

    // TODO remove method from prod
    public void showDuplicates(){
        map.clear();
        Log.e("myLogs", "____________DUPLICATES_______________" );
        for (RepoItem repoItem: repoItemList.get(repoItemList.size() - 1)) {
            if (map.get(repoItem.getFull_name()) == null) {
                map.put(repoItem.getFull_name(), 1);
            } else {
                Log.e("myLogs", "title " + repoItem.getFull_name());
                map.put(repoItem.getFull_name(), map.get(repoItem.getFull_name()) + 1);
            }
        }
        Log.e("myLogs", "_____________________________________" );

    }

    public static final int PAGE_LOADING_OFFSET = 3;

    private static final int FIRST_PAGE = 1;
    private static int currentPage = 1;

    private boolean isLoading;
    private boolean isLastPage;

    private ServiceGitHub serviceGitHub;
    private SchedulerProvider schedulerProvider;
    private Resources resources;

    private List<List<RepoItem>> repoItemList;

    @Inject
    public MainPresenter(ServiceGitHub serviceGitHub, SchedulerProvider schedulerProvider, Resources resources) {
        this.serviceGitHub = serviceGitHub;
        this.schedulerProvider = schedulerProvider;
        this.resources = resources;
        repoItemList = new ArrayList<>();
        //Log.e("myLogs", "MainPresenter res " + resources);
    }

    @Override
    public void attachView(MainView view) {
        super.attachView(view);
        getFirstRepos();
        Log.e("myLogs", "attachView " + repoItemList.size());
        Log.e("myLogs", "attachView  isLoading " + isLoading);
        Log.e("myLogs", "attachView isLastPage " + isLastPage);
        Log.e("myLogs", "attachView isLastPage " + currentPage);
    }

    @Override
    public void detachView(MainView view) {
        super.detachView(view);
        Log.e("myLogs", "detachView " + repoItemList.size());
        //showDuplicates();
        Log.e("myLogs", "detachView  isLoading " + isLoading);
        Log.e("myLogs", "detachView isLastPage " + isLastPage);
        Log.e("myLogs", "detachView isLastPage " + currentPage);
    }

    private void addAll(List<RepoItem> value){
        Log.e("myLogs", "addAll " + repoItemList.size());

        repoItemList.add(value);

        Log.e("myLogs", "addAll " + repoItemList.size());
    }

    public void getFirstRepos(){
        if(repoItemList.size() == 0) {
            DisposableObserver<List<RepoItem>> disposableObserver = serviceGitHub.getReposList(String.valueOf(FIRST_PAGE), resources.getString(R.string.repos_per_page))
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.mainThread()).subscribeWith(new DisposableObserver<List<RepoItem>>() {
                        @Override
                        public void onNext(List<RepoItem> value) {
                            Log.e("myLogs", "getFirstRepos onNext");
                            if(value != null){
                                addAll(value);
                            }
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
            currentPage = 2;
        } else {
            ArrayList<RepoItem> list = new ArrayList<>();
            for (List<RepoItem> itemList: repoItemList) {
                list.addAll(itemList);
            }
            getViewState().onFirstRepoUpdate(new PresenterResult<>(list));
        }
    }


    public void getNextRepos(int page){
        isLoading = true;
        DisposableObserver<List<RepoItem>> disposableObserver = serviceGitHub.getReposList(String.valueOf(currentPage++), resources.getString(R.string.repos_per_page))
                .delay(5, TimeUnit.SECONDS)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.mainThread())
                .subscribeWith(new DisposableObserver<List<RepoItem>>() {
                    @Override
                    public void onNext(List<RepoItem> value) {
                        Log.e("myLogs", "getNextRepos onNext");
                        if(value != null && value.size() < Integer.parseInt(resources.getString(R.string.repos_per_page))){
                            isLastPage = true;
                        } else {
                            isLastPage = false;
                        }
                        if(value != null){
                            addAll(value);
                        }
                        getViewState().onNextRepoUpdate(new PresenterResult<>(value));
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().onNextRepoUpdate(new PresenterResult<>(e.getMessage()));
                    }

                    @Override
                    public void onComplete() {
                        isLoading = false;
                    }
                });
        compositeDisposable.add(disposableObserver);
    }

    public boolean isLoading() {
        return isLoading;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public List<List<RepoItem>> getRepoItemList() {
        return repoItemList;
    }
}
