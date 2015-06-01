package com.wtransnet.app.cleancode.presentation.modules.jokes.detail;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.wtransnet.app.cleancode.domain.entities.Joke;
import com.wtransnet.app.cleancode.domain.interactors.core.DataEvent;
import com.wtransnet.app.cleancode.domain.interactors.core.Invoker;
import com.wtransnet.app.cleancode.domain.interactors.jokes.load.GetJokeInteractor;
import com.wtransnet.app.cleancode.presentation.core.presenter.AbstractPresenter;

/**
 * Presenter para el formulario
 */
public class JokeDetailPresenter extends AbstractPresenter<String, Joke, JokeDetailView> {

    private GetJokeInteractor getJokeInteractor;

    public JokeDetailPresenter(Bus bus, Invoker<String> invoker, GetJokeInteractor getJokeInteractor) {
        super(bus, invoker);
        this.getJokeInteractor = getJokeInteractor;
    }

    @Subscribe
    public void executePresenterResponse(DataEvent<Joke> event) {
        getView().hideSnackBar();
        choreographerCallback(event);
    }

    @Override
    public void manageView(DataEvent<Joke> event) {
        getView().showJokeDetail(event.getData());
    }

    @Override
    public void manageSpecificError(DataEvent<Joke> event) {
        getView().showJokeDetailError();
    }

    public void getJokeDetail(String message) {
        getView().showSnackBar();
        executePresenterRequest(message, getJokeInteractor);
    }

}
