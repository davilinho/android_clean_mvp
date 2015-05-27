package com.wtransnet.app.cleancode.domain.interactors.jokes.load;

import com.wtransnet.app.cleancode.domain.entities.Joke;
import com.wtransnet.app.cleancode.domain.interactors.core.BaseEvent;

/**
 * Event de respuesta de la carga de un Joke de detalle.
 */
public class GetJokeEvent extends BaseEvent {

    private Joke joke;

    public void setJoke(Joke joke) {
        this.joke = joke;
    }

    public Joke getJoke() {
        return joke;
    }
}
