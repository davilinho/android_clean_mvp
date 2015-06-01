package com.wtransnet.app.cleancode.presentation.modules.jokes.detail;

import com.wtransnet.app.cleancode.domain.entities.Joke;
import com.wtransnet.app.cleancode.presentation.core.view.BaseView;

/**
 * View para el detalle del Joke.
 */
public interface JokeDetailView extends BaseView {

    void showJokeDetail(Joke joke);

    void showJokeDetailError();
}
