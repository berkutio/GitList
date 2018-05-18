package com.gitlist.network;

import com.gitlist.model.RepoItem;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;


public class ApiGitHubTest {

    public static final String TEST_BASE_URL = "https://api.github.com/";
    private String TEST_REPOS_PER_PAGE = "15";
    private String TEST_PAGE = "1";

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
        List<RepoItem> repos = apiGitHub.getRepos(TEST_PAGE, TEST_REPOS_PER_PAGE).blockingFirst();
        assertNotNull(repos);
        assertEquals(Integer.parseInt(TEST_REPOS_PER_PAGE), repos.size());

        for (RepoItem repoItem : repos) {
            assertNotNull(repoItem);
            assertNotNull(repoItem.getFull_name());
            assertNotNull(repoItem.getDescription());
            assertNotNull(repoItem.getHtml_url());
        }
    }

}