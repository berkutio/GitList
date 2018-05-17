package com.gitlist;

import android.app.Application;

import com.gitlist.baseconfig.ComponentApp;
import com.gitlist.baseconfig.DaggerComponentApp;
import com.gitlist.baseconfig.ModuleApp;

public class App extends Application {

    private ComponentApp componentApp;

    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initComponentApp();
    }

    private void initComponentApp(){
        componentApp = DaggerComponentApp.builder().moduleApp(new ModuleApp(this)).build();
    }

    public ComponentApp getComponentApp() {
        return componentApp;
    }

    public static Application getApp() {
        return instance;
    }
}
