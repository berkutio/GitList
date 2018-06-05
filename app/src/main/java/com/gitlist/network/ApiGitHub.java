package com.gitlist.network;

import com.gitlist.model.testmodel.MovieResp;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiGitHub {


    @GET
    Single<MovieResp> getRepos(@Url String url);


}
