package com.gitlist.network.config;

import com.gitlist.baseconfig.scopes.ScopeApp;
import com.gitlist.baseconfig.scopes.ScopePresenter;
import com.gitlist.network.BaseProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

@Module
public class ModuleRetrofit {

    @Provides
    @ScopePresenter
    Retrofit provideRetrofit(Gson gson, BaseProvider baseProvider) {
        return new Retrofit.Builder()
                .baseUrl(baseProvider.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @ScopePresenter
    Gson provideGson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

}
