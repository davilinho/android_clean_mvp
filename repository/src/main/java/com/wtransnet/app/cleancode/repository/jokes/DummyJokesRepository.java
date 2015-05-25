package com.wtransnet.app.cleancode.repository.jokes;

import com.wtransnet.app.cleancode.domain.entities.Joke;
import com.wtransnet.app.cleancode.domain.interactors.jokes.load.LoadJokesException;
import com.wtransnet.app.cleancode.domain.repository.JokesRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación dummy del repositorio de Jokes
 */
public class DummyJokesRepository implements JokesRepository {

    @Override public List<Joke> loadJokes() throws LoadJokesException {

        List<Joke> jokes = new ArrayList();
        jokes.add(new Joke());
        jokes.add(new Joke());
        jokes.add(new Joke());

        return jokes;
    }
}
