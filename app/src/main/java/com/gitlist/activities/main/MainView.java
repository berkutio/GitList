package com.gitlist.activities.main;

import com.arellomobile.mvp.viewstate.strategy.*;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.gitlist.BaseView;

public interface MainView extends BaseView {

    @StateStrategyType(SingleStateStrategy.class)
    void test();

}
