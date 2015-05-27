package com.wtransnet.app.cleancode.domain.interactors.core;

public interface Invoker<T> {

    void execute(T data, Interactor interactor);

    void execute(T data, Interactor interactor, Priority priority);

}
