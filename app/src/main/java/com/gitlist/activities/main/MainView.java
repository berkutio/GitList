package com.gitlist.activities.main;

import com.arellomobile.mvp.viewstate.strategy.*;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.gitlist.BaseView;
import com.gitlist.model.PresenterResult;
import com.gitlist.model.testmodel.MovieResp;

public interface MainView extends BaseView {

    @StateStrategyType(SingleStateStrategy.class)
    void onFirstRepoUpdate(PresenterResult<MovieResp> result);

}
