package com.wtransnet.app.cleancode.data.rest.datasource;

import com.wtransnet.app.cleancode.data.rest.entities.JokeDetailResponse;
import com.wtransnet.app.cleancode.data.rest.entities.JokesListResponse;
import com.wtransnet.app.cleancode.data.rest.mapper.JokeDataMapper;
import com.wtransnet.app.cleancode.data.rest.service.JokesRestService;
import com.wtransnet.app.cleancode.domain.entities.Joke;
import com.wtransnet.app.cleancode.domain.entities.Name;
import com.wtransnet.app.cleancode.domain.interactors.jokes.load.JokesException;
import com.wtransnet.app.cleancode.repository.jokes.datasources.JokesDataSource;

import java.util.List;

/**
 * Implementación del DataSource de Jokes para el acceso a los datos mediante REST
 */
public class JokesRestDataSource implements JokesDataSource {

    private static final String SUCCESS = "success";

    private JokesRestService restService;
    private JokeDataMapper mapper;

    public JokesRestDataSource(JokesRestService restService, JokeDataMapper mapper) {
        this.restService = restService;
        this.mapper = mapper;
    }

    @Override
    public List<Joke> loadJokes(Name name) throws JokesException {

        List<Joke> jokes = null;

        final JokesListResponse restResponse = restService.loadJokes(name.getFirstName(), name.getLastName());

        if (SUCCESS.equals(restResponse.getType())) {
            jokes = mapper.transform(restResponse.getValue());
        }

        return jokes;

    }

    @Override
    public Joke getJoke(String id) throws JokesException {

        Joke joke = null;

        final JokeDetailResponse restResponse = restService.getJoke(id);

        if (SUCCESS.equals(restResponse.getType())) {
            joke = mapper.transform(restResponse.getValue());
        }

        return joke;

    }

}
