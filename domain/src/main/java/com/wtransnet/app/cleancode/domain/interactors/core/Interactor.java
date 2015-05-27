package com.wtransnet.app.cleancode.domain.interactors.core;

public interface Interactor<T> {

    void execute(T data);

}
