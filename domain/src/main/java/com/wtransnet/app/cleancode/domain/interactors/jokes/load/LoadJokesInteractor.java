package com.wtransnet.app.cleancode.domain.interactors.jokes.load;

import com.squareup.otto.Bus;
import com.wtransnet.app.cleancode.domain.entities.Name;
import com.wtransnet.app.cleancode.domain.interactors.core.Interactor;
import com.wtransnet.app.cleancode.domain.repository.JokesRepository;

/**
 * Interactor para la carga de los Jokes
 */
public class LoadJokesInteractor implements Interactor {

    private static final String FIRST_NAME = "Nuck";
    private static final String LAST_NAME  = "Chorris";

    private Bus bus;
    private JokesRepository repository;

    private Name name;

    public LoadJokesInteractor(Bus bus, JokesRepository repository) {
        this.bus = bus;
        this.repository = repository;
    }

    @Override public void execute() {

        LoadJokesEvent event = new LoadJokesEvent();

        try {
            event.setJokes(repository.loadJokes(name));
        } catch(LoadJokesException ex) {
            event.setError(ex);
        }

        bus.post(event);
    }

    public void setData(Name name) {

        if (name.getFirstName().isEmpty()) {
            name.setFirstName(FIRST_NAME);
        }

        if (name.getLastName().isEmpty()) {
            name.setLastName(LAST_NAME);
        }

        this.name = name;
    }
}
