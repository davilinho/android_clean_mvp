package com.wtransnet.app.cleancode.presentation.modules.jokes.detail;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.wtransnet.app.cleancode.domain.interactors.core.Invoker;
import com.wtransnet.app.cleancode.domain.interactors.jokes.load.GetJokeEvent;
import com.wtransnet.app.cleancode.domain.interactors.jokes.load.GetJokeInteractor;

/**
 * Presenter para el formulario
 */
public class JokeDetailPresenter {

    private final Bus bus;
    private final Invoker invoker;
    private final GetJokeInteractor getJokeInteractor;

    private JokeDetailView view;

    public JokeDetailPresenter(Bus bus, Invoker invoker, GetJokeInteractor getJokeInteractor) {
        this.bus = bus;
        this.invoker = invoker;
        this.getJokeInteractor = getJokeInteractor;
    }

    public void attachView(JokeDetailView view) {
        this.view = view;
    }

    public void onResume() {
        bus.register(this);
    }

    public void onPause() {
        bus.unregister(this);
    }

    public void getJokeDetail(String message) {
        view.showSnackBar();
        invoker.execute(message, getJokeInteractor);
    }

    @Subscribe
    public void getJoke(GetJokeEvent event) {

        view.hideSnackBar();

        if (event.hasError()) {
            view.showJokeDetailError();
        } else {
            view.showJokeDetail(event.getJoke());
        }
    }

}
