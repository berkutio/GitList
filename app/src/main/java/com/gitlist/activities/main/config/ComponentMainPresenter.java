package com.gitlist.activities.main.config;


import com.gitlist.activities.main.MainPresenter;
import com.gitlist.baseconfig.scopes.ScopePresenter;

import dagger.Subcomponent;

@ScopePresenter
@Subcomponent
public interface ComponentMainPresenter {

    MainPresenter getMainPresenter();

}
