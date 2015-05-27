package com.wtransnet.app.cleancode.app.domain;


import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.Params;
import com.wtransnet.app.cleancode.domain.interactors.core.Interactor;
import com.wtransnet.app.cleancode.domain.interactors.core.InteractorInvoker;
import com.wtransnet.app.cleancode.domain.interactors.core.InteractorPriority;

public class InteractorInvokerImp<T> implements InteractorInvoker<T> {

    private JobManager jobManager;

    public InteractorInvokerImp(JobManager jobManager) {
        this.jobManager = jobManager;
    }

    @Override public void execute(T data, Interactor interactor) {
        execute(data, interactor, InteractorPriority.MEDIUM);
    }

    @Override public void execute(T data, Interactor interactor, InteractorPriority priority) {
        jobManager.addJob(interactorToJob(data, interactor, priority));
    }

    private Job interactorToJob(T data, Interactor interactor, InteractorPriority priority) {
        Params params = new Params(priority.getPriorityValue());
        return new InteractorJobImp<T>(data, params, interactor);
    }
}
