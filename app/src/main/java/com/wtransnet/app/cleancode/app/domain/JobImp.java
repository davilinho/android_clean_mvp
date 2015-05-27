package com.wtransnet.app.cleancode.app.domain;

import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;
import com.wtransnet.app.cleancode.domain.interactors.core.Interactor;

public class JobImp<T> extends Job {

    private T data;

    private Interactor interactor;

    public JobImp(T data, Params params, Interactor interactor) {
        super(params);
        this.data = data;
        this.interactor = interactor;
    }

    @Override
    public void onAdded() {
    }

    @Override
    public void onRun() throws Throwable {
        interactor.execute(data);
    }

    @Override
    protected void onCancel() {
    }

    @Override
    protected boolean shouldReRunOnThrowable(Throwable throwable) {
    return false;
  }
}
