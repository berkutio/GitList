package com.gitlist.activities.main.config;


import com.gitlist.activities.main.MainPresenter;
import com.gitlist.baseconfig.scopes.ScopePresenter;
import com.gitlist.network.config.ModuleApiGitHub;
import com.gitlist.network.config.ModuleBaseProvider;
import com.gitlist.network.config.ModuleRetrofit;

import dagger.Subcomponent;

@ScopePresenter
@Subcomponent(modules = {ModuleApiGitHub.class, ModuleRetrofit.class, ModuleBaseProvider.class})
public interface ComponentMainPresenter {

    MainPresenter getMainPresenter();

}
