package com.wtransnet.app.cleancode.presentation.modules.jokes.list;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.wtransnet.app.cleancode.domain.entities.Joke;
import com.wtransnet.app.cleancode.domain.entities.Name;
import com.wtransnet.app.cleancode.domain.interactors.core.DataEvent;
import com.wtransnet.app.cleancode.domain.interactors.core.Invoker;
import com.wtransnet.app.cleancode.domain.interactors.jokes.load.LoadJokesInteractor;
import com.wtransnet.app.cleancode.presentation.core.presenter.AbstractPresenter;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Presenter para el formulario
 */
public class JokesListPresenter extends AbstractPresenter<Name, List<Joke>, JokesListView> {

    private LoadJokesInteractor loadJokesInteractor;

    public JokesListPresenter(Bus bus, Invoker<Name> invoker, LoadJokesInteractor loadJokesInteractor) {
        super(bus, invoker);
        this.loadJokesInteractor = loadJokesInteractor;
    }

    @Subscribe
    public void executePresenterResponse(DataEvent<List<Joke>> event) {
        choreographerCallback(event);
    }

    @Override
    public void attachView(JokesListView view) {
        super.attachView(view);
        getView().showProgress();
    }

    @Override
    public void manageView(DataEvent<List<Joke>> event) {
        getView().refreshJokesList(event.getData());
        getView().hideProgress();
    }

    @Override
    public void manageSpecificError(DataEvent<List<Joke>> event) {
        getView().showLoadJokesError();
    }

    public void loadJokesList(final Name name) {
        executePresenterRequest(name, loadJokesInteractor);
    }

}
