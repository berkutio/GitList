package com.gitlist.network;

import com.gitlist.model.testmodel.MovieResp;
import javax.inject.Inject;
import io.reactivex.Single;

public class ServiceGitHub {

    public static final String TEST_BASE_URL = "https://api.themoviedb.org/3/discover/movie?api_key=c45ad077780bae03716d0986fef1c801&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1";

    private ApiGitHub apiGitHub;

    @Inject
    public ServiceGitHub(ApiGitHub apiGitHub) {
        this.apiGitHub = apiGitHub;
    }


    public Single<MovieResp> getReposList() {
        return apiGitHub.getRepos(TEST_BASE_URL);
    }

}
