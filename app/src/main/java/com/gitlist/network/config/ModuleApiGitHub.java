package com.gitlist.network.config;

import com.gitlist.baseconfig.scopes.ScopePresenter;
import com.gitlist.network.ApiGitHub;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ModuleApiGitHub {


    @Provides
    @ScopePresenter
    public ApiGitHub provideApiGitHub(Retrofit retrofit){
        return retrofit.create(ApiGitHub.class);
    }


}
