package com.wtransnet.app.cleancode.domain.interactors.jokes.get;

import com.squareup.otto.Bus;
import com.wtransnet.app.cleancode.domain.entities.Joke;
import com.wtransnet.app.cleancode.domain.interactors.core.DataEvent;
import com.wtransnet.app.cleancode.domain.interactors.core.Interactor;
import com.wtransnet.app.cleancode.domain.interactors.jokes.load.JokesException;
import com.wtransnet.app.cleancode.domain.repository.JokesRepository;

/**
 * Interactor para la carga del detalle de un Joke
 *
 * Created by david martin on 27/05/2015.
 */
public class GetJokeInteractor implements Interactor<String> {

    private Bus bus;
    private DataEvent<Joke> event;
    private JokesRepository repository;

    public GetJokeInteractor(Bus bus, DataEvent<Joke> event, JokesRepository repository) {
        this.bus = bus;
        this.event = event;
        this.repository = repository;
    }

    @Override
    public void execute(String jokeId) {

        try {
            event.setData(repository.getJoke(jokeId));
        } catch(JokesException ex) {
            event.setError(ex);
        }

        bus.post(event);

    }

}
