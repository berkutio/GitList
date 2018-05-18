package com.gitlist.rx;

import io.reactivex.Scheduler;

public interface SchedulerProvider {

    Scheduler mainThread();

    Scheduler computation();

    Scheduler io();

}
