package com.wtransnet.app.cleancode.domain.repository;

import com.wtransnet.app.cleancode.domain.entities.Joke;
import com.wtransnet.app.cleancode.domain.entities.Name;
import com.wtransnet.app.cleancode.domain.interactors.jokes.load.JokesException;

import java.util.List;

/**
 * Repository de Jokes
 */
public interface JokesRepository {

    List<Joke> loadJokes(Name name) throws JokesException;

    Joke getJoke(String message) throws JokesException;
}
