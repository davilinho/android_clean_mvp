package com.wtransnet.app.cleancode.domain.interactors.jokes.load;

import com.squareup.otto.Bus;
import com.wtransnet.app.cleancode.domain.entities.Joke;
import com.wtransnet.app.cleancode.domain.entities.Name;
import com.wtransnet.app.cleancode.domain.interactors.core.DataEvent;
import com.wtransnet.app.cleancode.domain.interactors.core.Interactor;
import com.wtransnet.app.cleancode.domain.repository.JokesRepository;

import java.util.List;

/**
 * Interactor para la carga de los Jokes
 */
public class LoadJokesInteractor implements Interactor<Name> {

    private static final String FIRST_NAME = "Nuck";
    private static final String LAST_NAME  = "Chorris";

    private Bus bus;
    private DataEvent<List<Joke>> event;
    private JokesRepository repository;

    public LoadJokesInteractor(Bus bus, DataEvent<List<Joke>> event, JokesRepository repository) {
        this.bus = bus;
        this.event = event;
        this.repository = repository;
    }

    @Override
    public void execute(Name name) {

        prepareData(name);

        try {
            event.setData(repository.loadJokes(name));
        } catch(JokesException ex) {
            event.setError(ex);
        }

        bus.post(event);
    }

    public void prepareData(Name name) {

        if (isEmpty(name.getFirstName())) {
            name.setFirstName(FIRST_NAME);
        }

        if (isEmpty(name.getLastName())) {
            name.setLastName(LAST_NAME);
        }

    }

    private boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }

}
