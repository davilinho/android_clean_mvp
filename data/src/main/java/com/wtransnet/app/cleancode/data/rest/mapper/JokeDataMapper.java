package com.wtransnet.app.cleancode.data.rest.mapper;

import com.wtransnet.app.cleancode.data.rest.entities.JokeEntity;
import com.wtransnet.app.cleancode.domain.entities.Joke;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapeador que convierte objetos de tipo JokeEntity en objetos de tipo Joke
 */
public class JokeDataMapper {

    public Joke transform(JokeEntity entity) {

        Joke joke = null;

        if (entity != null) {

            joke = new Joke();

            joke.setId(entity.getId());
            joke.setJoke(entity.getJoke());
        }

        return joke;
    }

    public List<Joke> transform(JokeEntity[] entityList) {

        List<Joke> jokeList = new ArrayList<>(entityList.length);

        Joke joke;

        for (JokeEntity entity: entityList) {

            joke = transform(entity);

            if (joke != null) {
                jokeList.add(joke);
            }
        }

        return jokeList;
    }

}
