package com.wtransnet.app.cleancode.domain.interactors.jokes.load;

import com.wtransnet.app.cleancode.domain.entities.Joke;

import java.util.List;

/**
 * Event de respuesta de la carga de Jokes
 */
public class LoadJokesEvent extends com.wtransnet.app.cleancode.domain.interactors.core.BaseEvent {

    private List<Joke> jokes;

    public void setJokes(List<Joke> jokes) {
        this.jokes = jokes;
    }

    public List<Joke> getJokes() {
        return jokes;
    }
}
