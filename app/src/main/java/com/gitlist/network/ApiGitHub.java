package com.gitlist.network;



import com.gitlist.model.RepoItem;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiGitHub {


    @GET("users/JakeWharton/repos?")
    Single<List<RepoItem>> getRepos(@Query("page") String page, @Query("per_page") String perPage);


}
