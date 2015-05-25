package com.wtransnet.app.cleancode.domain.repository;

import com.wtransnet.app.cleancode.domain.entities.Joke;

import java.util.List;

/**
 * Repository de Jokes
 */
public interface JokesRepository {

    List<Joke> loadJokes() throws com.wtransnet.app.cleancode.domain.interactors.jokes.load.LoadJokesException;
}
