package com.wtransnet.app.cleancode.presentation.modules.jokes.list;

import com.squareup.otto.Bus;
import com.wtransnet.app.cleancode.domain.interactors.core.InteractorInvoker;
import com.wtransnet.app.cleancode.domain.interactors.jokes.load.LoadJokesInteractor;
import com.wtransnet.app.cleancode.presentation.bean.Name;

import javax.inject.Inject;

/**
 * Presenter para el formulario
 */
public class JokesListPresenter {

    @Inject Bus bus;
    @Inject InteractorInvoker invoker;
    @Inject LoadJokesInteractor loadJokesInteractor;

    private JokesListView view;

    public JokesListPresenter(Bus bus, InteractorInvoker invoker, LoadJokesInteractor loadJokesInteractor) {
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
        invoker.execute(loadJokesInteractor);
    }
}
