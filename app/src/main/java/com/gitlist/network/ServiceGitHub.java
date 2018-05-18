package com.gitlist.network;


import com.gitlist.model.RepoItem;

import java.util.List;

import javax.inject.Inject;
import io.reactivex.Observable;

public class ServiceGitHub {

    private ApiGitHub apiGitHub;

    @Inject
    public ServiceGitHub(ApiGitHub apiGitHub) {
        this.apiGitHub = apiGitHub;
    }


    public Observable<List<RepoItem>> getReposList(String page, String perPage) {
        return apiGitHub.getRepos(page, perPage);
    }

}
