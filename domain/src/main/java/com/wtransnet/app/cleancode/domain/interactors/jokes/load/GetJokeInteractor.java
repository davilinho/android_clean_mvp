package com.wtransnet.app.cleancode.domain.interactors.jokes.load;

import com.squareup.otto.Bus;
import com.wtransnet.app.cleancode.domain.entities.Name;
import com.wtransnet.app.cleancode.domain.interactors.core.Interactor;
import com.wtransnet.app.cleancode.domain.repository.JokesRepository;

/**
 * Created by dmartin on 27/05/2015.
 */
public class GetJokeInteractor implements Interactor<String> {

    private Bus bus;
    private JokesRepository repository;

    public GetJokeInteractor(Bus bus, JokesRepository repository) {
        this.bus = bus;
        this.repository = repository;
    }

    @Override
    public void execute(String data) {

        GetJokeEvent event = new GetJokeEvent();

        try {
            event.setJoke(repository.getJoke(data));
        } catch(LoadJokesException ex) {
            event.setError(ex);
        }

        bus.post(event);

    }

}
