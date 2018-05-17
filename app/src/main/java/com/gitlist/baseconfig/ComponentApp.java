package com.gitlist.baseconfig;


import com.gitlist.activities.main.config.ComponentMainPresenter;

import dagger.Component;

@Component(modules = ModuleApp.class)
public interface ComponentApp {

    ComponentMainPresenter gComponentMainPresenter();

}
