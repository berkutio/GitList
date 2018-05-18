package com.gitlist.network;


import javax.inject.Inject;

public class ServiceGitHub {

    private ApiGitHub apiGitHub;

    @Inject
    public ServiceGitHub(ApiGitHub apiGitHub) {
        this.apiGitHub = apiGitHub;
    }

}
