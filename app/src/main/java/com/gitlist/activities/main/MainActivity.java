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
import com.gitlist.model.testmodel.MovieResp;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

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
        mLayoutManager = new LinearLayoutManager(this);
        mRv.setLayoutManager(mLayoutManager);
        mAdapterMainActivity = new AdapterMainActivity();
        mRv.setAdapter(mAdapterMainActivity);

    }

    @Override
    public void onFirstRepoUpdate(@NonNull PresenterResult<MovieResp> result) {
        Log.e("myLogs", "onFirstRepoUpdate " + result.getResponse());
        if(result.getResponse() != null){
            mAdapterMainActivity.updateList(result.getResponse().getResults());
            mClDataLoading.setVisibility(View.GONE);
            mClNoConnection.setVisibility(View.GONE);
            mRv.setVisibility(View.VISIBLE);
        }

        if(mAdapterMainActivity.getItemCount() == 0){
            mClDataLoading.setVisibility(View.GONE);
            mClNoConnection.setVisibility(View.VISIBLE);
            mRv.setVisibility(View.GONE);
        }

    }

}
