package com.gitlist.network;

import com.gitlist.model.testmodel.MovieResp;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static org.junit.Assert.*;

public class ApiGitHubTest {

    public static final String TEST_LOAD_URL = "https://api.themoviedb.org/3/discover/movie?api_key=c45ad077780bae03716d0986fef1c801&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1";
    public static final String TEST_BASE_URL = "https://www.themoviedb.org/";

    private ApiGitHub apiGitHub;

    @Before
    public void setUp() throws Exception {
        GsonBuilder gsonBuilder = new GsonBuilder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TEST_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiGitHub = retrofit.create(ApiGitHub.class);
    }

    @Test
    public void testGetRepos() throws Exception {
        MovieResp repos = apiGitHub.getRepos(TEST_LOAD_URL).blockingGet();
        assertNotNull(repos);
        assertNotNull(repos.getResults());
        System.out.println("repos " + repos);
    }

}