package com.wtransnet.app.cleancode.repository.jokes.datasources;

import com.wtransnet.app.cleancode.domain.entities.Joke;
import com.wtransnet.app.cleancode.domain.interactors.jokes.load.JokesException;
import com.wtransnet.app.cleancode.domain.entities.Name;

import java.util.List;

/**
 * Interface que define los métodos que debe de contener el DataSource de Jokes
 */
public interface JokesDataSource {

    List<Joke> loadJokes(Name name) throws JokesException;

    Joke getJoke(String id) throws JokesException;
}
