package com.gitlist;


import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;

import org.androidannotations.annotations.EActivity;

@EActivity
public abstract class BaseActivity extends MvpAppCompatActivity implements BaseView  {


    @Override
    public void onErrorHandle(Throwable error) {
        showError(error.getMessage());
    }

    protected void showError(String error){
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

}
