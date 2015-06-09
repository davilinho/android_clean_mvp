package com.wtransnet.app.cleancode.app.domain;


import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.Params;
import com.wtransnet.app.cleancode.domain.interactors.core.Interactor;
import com.wtransnet.app.cleancode.domain.interactors.core.Invoker;
import com.wtransnet.app.cleancode.domain.interactors.core.Priority;

import java.util.Timer;
import java.util.TimerTask;

public class InvokerImp<T> implements Invoker<T> {

    private JobManager jobManager;

    public InvokerImp(JobManager jobManager) {
        this.jobManager = jobManager;
    }

    @Override public void execute(T data, Interactor<T> interactor) {
        execute(data, interactor, Priority.MEDIUM);
    }

    @Override public void execute(final T data, final Interactor<T> interactor, final Priority priority) {

        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                jobManager.addJob(interactorToJob(data, interactor, priority));
            }

        }, 2000);
    }

    private Job interactorToJob(T data, Interactor<T> interactor, Priority priority) {
        Params params = new Params(priority.getPriorityValue());
        return new JobImp<>(data, params, interactor);
    }
}
