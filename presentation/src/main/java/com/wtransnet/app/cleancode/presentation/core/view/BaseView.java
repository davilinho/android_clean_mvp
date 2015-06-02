package com.wtransnet.app.cleancode.presentation.core.view;

/**
 * View genérico.
 */
public interface BaseView extends com.wtransnet.app.cleancode.presentation.core.error.ErrorHandler {

    void showProgress();

    void hideProgress();

    void showSnackBar();

    void hideSnackBar();

}
