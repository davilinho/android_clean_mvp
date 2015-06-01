package com.wtransnet.app.cleancode.presentation.core.presenter;

import com.squareup.otto.Bus;
import com.wtransnet.app.cleancode.domain.interactors.core.DataEvent;
import com.wtransnet.app.cleancode.domain.interactors.core.ErrorEvent;
import com.wtransnet.app.cleancode.domain.interactors.core.Interactor;
import com.wtransnet.app.cleancode.domain.interactors.core.Invoker;
import com.wtransnet.app.cleancode.presentation.core.view.BaseView;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Presenter base con la funcionalidad base de todas los demás Presenters.
 */
public abstract class AbstractPresenter<T1, T2, V extends BaseView> {

    private Bus bus;
    private Invoker<T1> invoker;
    private WeakReference<V> view;

    private static Map<String, AbstractPresenter> busHashMap;

    public AbstractPresenter(Bus bus, Invoker<T1> invoker) {

        this.bus = bus;
        this.invoker = invoker;

        initBusHashMap();

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

    public void onDestroy() {
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

    private void initBusHashMap() {

        if (busHashMap == null) {
            busHashMap = new HashMap<>();
        }

    }

    private void registerEventBus() {

        if (!checkBusRegistered()) {

            bus.register(this);
            setIsBusRegistered(true);

        }

    }

    private void unRegisterEventBus() {

        if (checkBusRegistered()) {

            bus.unregister(this);
            setIsBusRegistered(false);

        }

    }

    private boolean checkBusRegistered() {
        return busHashMap.containsKey(this.getClass().getName());
    }

    private void setIsBusRegistered(final boolean isBusRegistered) {

        if (isBusRegistered) {
            busHashMap.put(this.getClass().getName(), this);
        } else {
            busHashMap.remove(this.getClass().getName());
        }

    }

    private void manageCommonError(ErrorEvent event) {

        if (getView() != null) {
            getView().manageCommonErrors();
        }

    }

}
