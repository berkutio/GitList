package com.gitlist.activities.main;


import com.gitlist.model.PresenterResult;
import com.gitlist.network.ApiGitHub;
import com.gitlist.network.ServiceGitHub;
import com.gitlist.rx.TestApplicationProvider;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.schedulers.TestScheduler;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;


public class MainPresenterTest {

    public static final String TEST_BASE_URL = "https://revolut.duckdns.org/";

    @Mock
    MainView mainView;

    @Mock
    MainView$$State mainView$$State;

    private MainPresenter mainPresenter;
    private TestScheduler testScheduler;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        GsonBuilder gsonBuilder = new GsonBuilder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TEST_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ApiGitHub apiGitHub = retrofit.create(ApiGitHub.class);

        ServiceGitHub serviceGitHub = new ServiceGitHub(apiGitHub);
        testScheduler = new TestScheduler();

//        mainPresenter = new MainPresenter(serviceGitHub, new TestApplicationProvider(testScheduler));
//        mainPresenter.attachView(mainView);
//        mainPresenter.setViewState(mainView$$State);
    }

    @Test
    public void getFirstRepos() throws Exception {
        mainPresenter.getFirstRepos();
        testScheduler.triggerActions();
        verify(mainView$$State).onFirstRepoUpdate(any(PresenterResult.class));
    }

}