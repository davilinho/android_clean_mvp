package com.wtransnet.app.cleancode.domain.interactors.core;

public interface InteractorInvoker<T> {

    void execute(T data, Interactor interactor);

    void execute(T data, Interactor interactor, InteractorPriority priority);

}
