package com.gitlist.baseconfig;


import com.gitlist.activities.main.config.ComponentMainPresenter;
import com.gitlist.baseconfig.scopes.ScopeApp;
import com.gitlist.network.config.ModuleBaseProvider;

import dagger.Component;

@ScopeApp
@Component(modules = {ModuleApp.class})
public interface ComponentApp {

    ComponentMainPresenter gComponentMainPresenter();


}
