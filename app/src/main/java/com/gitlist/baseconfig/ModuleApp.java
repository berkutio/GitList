package com.gitlist.baseconfig;


import android.app.Application;
import android.content.res.Resources;

import com.gitlist.baseconfig.scopes.ScopeApp;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuleApp {

    private final Application application;

    public ModuleApp(Application application) {
        this.application = application;
    }

    @Provides
    @ScopeApp
    Application provideApplication() {
        return application;
    }

    @Provides
    @ScopeApp
    Resources provideResources() {
        return application.getResources();
    }

}
