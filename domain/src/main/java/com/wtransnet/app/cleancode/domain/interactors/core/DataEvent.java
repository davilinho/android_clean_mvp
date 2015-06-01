package com.wtransnet.app.cleancode.domain.interactors.core;

/**
 * Component created on 29/05/2015.
 *
 * @author dmartin
 */
public class DataEvent<T> extends ErrorEvent {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
