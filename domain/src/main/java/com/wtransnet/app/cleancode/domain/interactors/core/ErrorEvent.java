package com.wtransnet.app.cleancode.domain.interactors.core;

public class ErrorEvent {

    private Throwable error = null;

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    public boolean hasError() {
        return error != null;
    }
}
