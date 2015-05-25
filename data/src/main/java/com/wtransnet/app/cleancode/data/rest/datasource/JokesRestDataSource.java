package com.wtransnet.app.cleancode.data.rest.datasource;

import com.wtransnet.app.cleancode.data.rest.service.JokesRestService;
import com.wtransnet.app.cleancode.domain.entities.Joke;
import com.wtransnet.app.cleancode.domain.interactors.jokes.load.LoadJokesException;
import com.wtransnet.app.cleancode.repository.jokes.datasources.JokesDataSource;

import java.util.List;

/**
 * Implementación del DataSource de Jokes para el acceso a los datos mediante REST
 */
public class JokesRestDataSource implements JokesDataSource {

    private static final String SUCCESS = "success";

    private JokesRestService restService;

    public JokesRestDataSource(JokesRestService restService) {
        this.restService = restService;
    }

    @Override public List<Joke> loadJokes() throws LoadJokesException {

        List<Joke> jokes = null;

        final com.wtransnet.app.cleancode.data.rest.entities.RestResponse restResponse = restService.loadJokes();

        if (SUCCESS.equals(restResponse.getType())) {

        }

        return jokes;
    }
}
