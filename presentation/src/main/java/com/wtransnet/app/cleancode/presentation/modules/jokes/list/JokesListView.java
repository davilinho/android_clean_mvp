package com.wtransnet.app.cleancode.presentation.modules.jokes.list;

import com.wtransnet.app.cleancode.domain.entities.Joke;

import java.util.List;

/**
 * View para el listado de Jokes
 */
public interface JokesListView {

    void refreshJokesList(List<Joke> jokes);

    void showLoadJokesError();
}
