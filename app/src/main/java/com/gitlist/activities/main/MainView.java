package com.gitlist.activities.main;

import com.arellomobile.mvp.viewstate.strategy.*;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.gitlist.BaseView;
import com.gitlist.model.PresenterResult;
import com.gitlist.model.RepoItem;

import java.util.List;

public interface MainView extends BaseView {

    @StateStrategyType(SingleStateStrategy.class)
    void test();

    @StateStrategyType(SingleStateStrategy.class)
    void onFirstRepoUpdate(PresenterResult<List<RepoItem>> result);

}
