package com.gitlist.activities.main;


import android.util.Log;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.gitlist.App;
import com.gitlist.BaseActivity;
import com.gitlist.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity implements MainView {

    @InjectPresenter
    MainPresenter mainPresenter;


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
        mainPresenter.test();
    }

    @Override
    public void test() {
        Toast.makeText(this, "Test", Toast.LENGTH_LONG).show();
    }
}
