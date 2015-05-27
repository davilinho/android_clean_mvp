package com.wtransnet.app.cleancode.presentation.modules.jokes.list;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.wtransnet.app.cleancode.domain.interactors.core.Invoker;
import com.wtransnet.app.cleancode.domain.interactors.jokes.load.LoadJokesEvent;
import com.wtransnet.app.cleancode.domain.interactors.jokes.load.LoadJokesInteractor;
import com.wtransnet.app.cleancode.domain.entities.Name;

/**
 * Presenter para el formulario
 */
public class JokesListPresenter {

    private final Bus bus;
    private final Invoker invoker;
    private final LoadJokesInteractor loadJokesInteractor;

    private JokesListView view;

    public JokesListPresenter(Bus bus, Invoker invoker, LoadJokesInteractor loadJokesInteractor) {
        this.bus = bus;
        this.invoker = invoker;
        this.loadJokesInteractor = loadJokesInteractor;
    }

    public void attachView(JokesListView view) {
        this.view = view;
    }

    public void onResume() {
        bus.register(this);
    }

    public void onPause() {
        bus.unregister(this);
    }

    public void loadJokesList(Name name) {
        invoker.execute(name, loadJokesInteractor);
    }

    @Subscribe
    public void loadJokes(LoadJokesEvent event) {

        if (event.hasError()) {
            view.showLoadJokesError();
        } else {
            view.refreshJokesList(event.getJokes());
        }
    }

}
