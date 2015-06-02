package com.wtransnet.app.cleancode.presentation.modules.jokes.list;

import com.squareup.otto.Bus;
import com.wtransnet.app.cleancode.domain.entities.Name;
import com.wtransnet.app.cleancode.domain.interactors.core.Invoker;
import com.wtransnet.app.cleancode.domain.interactors.jokes.load.LoadJokesInteractor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Test de la clase JokesListPresenter
 */
public class JokesListPresenterTest {

    private JokesListPresenter jokesListPresenter;

    @Mock private Bus mockBus;
    @Mock private Invoker<Name> mockInvoker;
    @Mock private LoadJokesInteractor mockLoadJokesInteractor;
    @Mock private JokesListView mockView;

    @Mock private Name fakeName;

    @Before public void setUp() {
        MockitoAnnotations.initMocks(this);
        jokesListPresenter = new JokesListPresenter(mockBus, mockInvoker, mockLoadJokesInteractor);
        jokesListPresenter.attachView(mockView);
    }

    @Test public void testOnResume() {
        jokesListPresenter.onResume();
        verify(mockBus).register(jokesListPresenter);
    }

    @Test public void testOnPause() {
        jokesListPresenter.onPause();
        verify(mockBus).unregister(jokesListPresenter);
    }

    @Test public void testLoadJokesList() {

        Name name = mock(Name.class);

        jokesListPresenter.loadJokesList(name);
        verify(mockInvoker).execute(name, mockLoadJokesInteractor);
    }

}
