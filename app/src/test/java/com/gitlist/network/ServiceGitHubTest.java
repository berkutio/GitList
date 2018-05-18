package com.gitlist.network;

import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.junit.Before;
import org.junit.Test;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;


public class ServiceGitHubTest {

    public static final String TEST_BASE_URL = "https://revolut.duckdns.org/";

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

//        StorageApiCurrencyMonitor storageApiCurrencyMonitor = mock(StorageApiCurrencyMonitor.class);
//        when(storageApiCurrencyMonitor.insertCurrencyList()).thenReturn(new long[]{1});
//        when(storageApiCurrencyMonitor.getAllListUsers()).thenReturn(Single.create(e -> e.onSuccess(new ArrayList<>())));
//
//        serviceCurrencyMonitor = new ServiceCurrencyMonitor(apiCurrencyMonitor, storageApiCurrencyMonitor);

    }

    @Test
    public void getReposList() throws Exception {
    }

}