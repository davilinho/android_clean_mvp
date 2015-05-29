package com.wtransnet.app.cleancode.domain.interactors.jokes.load;

import com.squareup.otto.Bus;
import com.wtransnet.app.cleancode.domain.entities.Joke;
import com.wtransnet.app.cleancode.domain.interactors.core.DataEvent;
import com.wtransnet.app.cleancode.domain.interactors.core.Interactor;
import com.wtransnet.app.cleancode.domain.repository.JokesRepository;

/**
 * Component created on 27/05/2015.
 * @author dmartin
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

        DataEvent<Joke> event = new DataEvent<>();

        try {
            event.setData(repository.getJoke(data));
        } catch(JokesException ex) {
            event.setError(ex);
        }

        bus.post(event);

    }

}
