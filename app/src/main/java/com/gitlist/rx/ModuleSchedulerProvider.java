package com.gitlist.rx;


import com.gitlist.baseconfig.scopes.ScopePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuleSchedulerProvider {


    @Provides
    @ScopePresenter
    public SchedulerProvider getApplicationProvider(){
        return new ApplicationProvider();
    }


}
