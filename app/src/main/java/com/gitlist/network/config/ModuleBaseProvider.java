package com.gitlist.network.config;

import android.content.res.Resources;

import com.gitlist.baseconfig.ModuleApp;
import com.gitlist.baseconfig.scopes.ScopeApp;
import com.gitlist.baseconfig.scopes.ScopePresenter;
import com.gitlist.network.BaseProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuleBaseProvider {


    @Provides
    @ScopePresenter
    public BaseProvider getBaseProvider(Resources resources){
        return new BaseProvider(resources);
    }


}
