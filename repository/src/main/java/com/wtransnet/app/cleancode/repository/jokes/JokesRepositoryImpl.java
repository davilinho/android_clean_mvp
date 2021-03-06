package com.wtransnet.app.cleancode.repository.jokes;

import com.wtransnet.app.cleancode.domain.entities.Joke;
import com.wtransnet.app.cleancode.domain.entities.Name;
import com.wtransnet.app.cleancode.domain.interactors.jokes.load.JokesException;
import com.wtransnet.app.cleancode.domain.repository.JokesRepository;
import com.wtransnet.app.cleancode.repository.jokes.datasources.JokesDataSource;

import java.util.List;

/**
 * Implementación del repositorio de Jokes
 */
public class JokesRepositoryImpl implements JokesRepository {

    private JokesDataSource dataSource;

    public JokesRepositoryImpl(JokesDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Joke> loadJokes(Name name) throws JokesException {
        return dataSource.loadJokes(name);
    }

    @Override
    public Joke getJoke(String jokeId) throws JokesException {
        return dataSource.getJoke(jokeId);
    }

}
