package com.wtransnet.app.cleancode.domain.interactors.jokes.load;

import com.squareup.otto.Bus;
import com.wtransnet.app.cleancode.domain.interactors.core.Interactor;
import com.wtransnet.app.cleancode.domain.repository.JokesRepository;

/**
 * Interactor para la carga de los Jokes
 */
public class LoadJokesInteractor implements Interactor {

    private Bus bus;
    private JokesRepository repository;

    public LoadJokesInteractor(Bus bus, JokesRepository repository) {
        this.bus = bus;
        this.repository = repository;
    }

    @Override public void execute() {

        LoadJokesEvent event = new LoadJokesEvent();

        try {
            event.setJokes(repository.loadJokes());
        } catch(LoadJokesException ex) {
            event.setError(ex);
        }

        bus.post(event);
    }
}
