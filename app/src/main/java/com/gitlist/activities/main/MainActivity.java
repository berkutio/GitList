package com.gitlist.activities.main;


import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapterMainActivity = new AdapterMainActivity();
        mRv.setAdapter(mAdapterMainActivity);
        mainPresenter.getFirstRepos();
    }

    @Override
    public void test() {
        Toast.makeText(this, "Test", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFirstRepoUpdate(@NonNull PresenterResult<List<RepoItem>> result) {
        Log.e("myLogs", "onFirstRepoUpdate " + result);
        if(result.getResponse() != null && result.getResponse().size() > 0){
            mClDataLoading.setVisibility(View.GONE);
            mClNoConnection.setVisibility(View.GONE);
            mAdapterMainActivity.updateList(result.getResponse());
            mRv.setVisibility(View.VISIBLE);
        }

        if(result.getError() != null) {
            showError(result.getError());
        }

        if(mAdapterMainActivity.getItemCount() == 0){
            mClDataLoading.setVisibility(View.GONE);
            mClNoConnection.setVisibility(View.VISIBLE);
            mRv.setVisibility(View.GONE);
        }

    }


}
