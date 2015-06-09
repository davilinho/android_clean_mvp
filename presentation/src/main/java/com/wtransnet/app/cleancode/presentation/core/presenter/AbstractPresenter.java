package com.wtransnet.app.cleancode.presentation.core.presenter;

import com.squareup.otto.Bus;
import com.wtransnet.app.cleancode.domain.interactors.core.DataEvent;
import com.wtransnet.app.cleancode.domain.interactors.core.ErrorEvent;
import com.wtransnet.app.cleancode.domain.interactors.core.Interactor;
import com.wtransnet.app.cleancode.domain.interactors.core.Invoker;
import com.wtransnet.app.cleancode.presentation.core.view.BaseView;

import java.lang.ref.WeakReference;

/**
 * Presenter base con la funcionalidad base de todas los demás Presenters.
 */
public abstract class AbstractPresenter<T1, T2, V extends BaseView> {

    private Bus bus;
    private Invoker<T1> invoker;
    private WeakReference<V> view;

    public AbstractPresenter(Bus bus, Invoker<T1> invoker) {
        this.bus = bus;
        this.invoker = invoker;
    }

    public void attachView(V view) {
        this.view = new WeakReference<>(view);
    }

    public void detachView() {
        this.view = null;
    }

    public void onResume() {
        registerEventBus();
    }

    public void onPause() {
        unRegisterEventBus();
    }

    protected void executePresenterRequest(T1 data, Interactor<T1> interactor) {
        invoker.execute(data, interactor);
    }

    protected V getView() {

        if (view == null) {
            throw new NullPointerException("View is NULL.");
        }

        return view.get();

    }

    protected void choreographerCallback(DataEvent<T2> event) {

        if (event.hasError()) {
            manageCommonError(event);
            manageSpecificError(event);
        } else {
            manageView(event);
        }

    }

    protected abstract void manageView(DataEvent<T2> event);

    protected abstract void manageSpecificError(DataEvent<T2> event);

    private void registerEventBus() {
        bus.register(this);
    }

    private void unRegisterEventBus() {
        bus.unregister(this);
    }

    private void manageCommonError(ErrorEvent event) {

        if (getView() != null) {
            getView().manageCommonErrors();
        }

    }

}
