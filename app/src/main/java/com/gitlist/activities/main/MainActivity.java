package com.gitlist.activities.main;


import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.gitlist.App;
import com.gitlist.BaseActivity;
import com.gitlist.R;
import com.gitlist.adapters.AdapterMainActivity;

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

    private AdapterMainActivity adapterMainActivity;

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
        adapterMainActivity = new AdapterMainActivity();
        mRv.setAdapter(adapterMainActivity);
        mainPresenter.test();
    }

    @Override
    public void test() {
        Toast.makeText(this, "Test", Toast.LENGTH_LONG).show();
    }
}
