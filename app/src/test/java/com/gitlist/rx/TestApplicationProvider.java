package com.gitlist.rx;


import io.reactivex.Scheduler;
import io.reactivex.schedulers.TestScheduler;

public class TestApplicationProvider implements SchedulerProvider {

    private final TestScheduler mTestScheduler;

    public TestApplicationProvider(TestScheduler testScheduler) {
        this.mTestScheduler = testScheduler;
    }

    @Override
    public Scheduler mainThread() {
        return mTestScheduler;
    }

    @Override
    public Scheduler computation() {
        return mTestScheduler;
    }

    @Override
    public Scheduler io() {
        return mTestScheduler;
    }

}