package com.wtransnet.app.cleancode.repository.jokes;

import com.wtransnet.app.cleancode.domain.entities.Joke;
import com.wtransnet.app.cleancode.domain.interactors.jokes.load.LoadJokesException;
import com.wtransnet.app.cleancode.domain.repository.JokesRepository;

import java.util.List;

/**
 * Implementación dummy del repositorio de Jokes
 */
public class JokesRepositoryImpl implements JokesRepository {

    private com.wtransnet.app.cleancode.repository.jokes.datasources.JokesDataSource dataSource;

    public JokesRepositoryImpl(com.wtransnet.app.cleancode.repository.jokes.datasources.JokesDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override public List<Joke> loadJokes() throws LoadJokesException {
        return dataSource.loadJokes();
    }
}
