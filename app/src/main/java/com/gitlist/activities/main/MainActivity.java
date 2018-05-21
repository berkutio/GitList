package com.gitlist.activities.main;


import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.gitlist.App;
import com.gitlist.BaseActivity;
import com.gitlist.R;
import com.gitlist.adapters.AdapterMainActivity;
import com.gitlist.model.PresenterResult;
import com.gitlist.model.RepoItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity implements MainView {

    @InjectPresenter
    MainPresenter mainPresenter;

    @ViewById(R.id.rv)
    RecyclerView mRv;

    @ViewById(R.id.cl_data_loading)
    ConstraintLayout mClDataLoading;

    @ViewById(R.id.cl_no_connection)
    ConstraintLayout mClNoConnection;

    private AdapterMainActivity mAdapterMainActivity;
    private LinearLayoutManager mLayoutManager;

    @ProvidePresenter
    protected MainPresenter providePresenter() {
        MainPresenter mainPresenter = ((App) getApplication())
                .getComponentApp()
                .gComponentMainPresenter()
                .getMainPresenter();
        return mainPresenter;
    }


    @AfterViews
    protected void init() {
        Log.e("myLogs", "init " + mainPresenter.getRepoItemList().size());
        mLayoutManager = new LinearLayoutManager(this);
        mRv.setLayoutManager(mLayoutManager);
        mAdapterMainActivity = new AdapterMainActivity();
        mRv.setAdapter(mAdapterMainActivity);
        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();
                //Log.e("myLogs", "onScrolled");
                if(!mainPresenter.isLoading() && !mainPresenter.isLastPage()
                        && lastVisibleItemPosition + MainPresenter.PAGE_LOADING_OFFSET >= mLayoutManager.getItemCount()) {
                    Log.e("myLogs", "onScrolled add smth");
                    //mAdapterMainActivity.addProgressItem();
                    // TODO remove method argument
                    mainPresenter.getNextRepos(0);
                }
            }
        });
    }

    @Override
    public void onFirstRepoUpdate(@NonNull PresenterResult<List<RepoItem>> result) {
        //Log.e("myLogs", "onFirstRepoUpdate " + result.getResponse().size());
        if(result.getResponse() != null && result.getResponse().size() > 0){
            mAdapterMainActivity.updateList(result.getResponse());
            mClDataLoading.setVisibility(View.GONE);
            mClNoConnection.setVisibility(View.GONE);
            mRv.setVisibility(View.VISIBLE);
        }

        if(mAdapterMainActivity.getItemCount() == 0){
            mClDataLoading.setVisibility(View.GONE);
            mClNoConnection.setVisibility(View.VISIBLE);
            mRv.setVisibility(View.GONE);
        }

//        if(!mainPresenter.isLastPage()){
//            mAdapterMainActivity.addProgressItem();
//        }

    }

    @Override
    public void onNextRepoUpdate(@NonNull PresenterResult<List<RepoItem>> result) {
        if(result.getResponse() != null && result.getResponse().size() > 0){
            mAdapterMainActivity.addToList(result.getResponse());
        }
        if(mainPresenter.isLastPage()){
            Log.e("myLogs", "isLastPage");
            //mAdapterMainActivity.removeProgressItem();
            Log.e("myLogs", "isLastPage " + mAdapterMainActivity.getItemCount());
        }
    }


}
