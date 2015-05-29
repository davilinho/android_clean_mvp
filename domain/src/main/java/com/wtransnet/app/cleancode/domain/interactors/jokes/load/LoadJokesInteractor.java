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
    private JokesRepository repository;

    public LoadJokesInteractor(Bus bus, JokesRepository repository) {
        this.bus = bus;
        this.repository = repository;
    }

    @Override
    public void execute(Name data) {

        prepareData(data);

        DataEvent<List<Joke>> event = new DataEvent<>();

        try {
            event.setData(repository.loadJokes(data));
        } catch(JokesException ex) {
            event.setError(ex);
        }

        bus.post(event);
    }

    public void prepareData(Name name) {

        if (name.getFirstName().isEmpty()) {
            name.setFirstName(FIRST_NAME);
        }

        if (name.getLastName().isEmpty()) {
            name.setLastName(LAST_NAME);
        }

    }

}
