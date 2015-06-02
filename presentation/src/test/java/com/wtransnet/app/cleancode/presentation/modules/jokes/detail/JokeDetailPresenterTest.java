package com.wtransnet.app.cleancode.presentation.modules.jokes.detail;

import com.squareup.otto.Bus;
import com.wtransnet.app.cleancode.domain.interactors.core.Invoker;
import com.wtransnet.app.cleancode.domain.interactors.jokes.get.GetJokeInteractor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

/**
 * Test de la clase JokeDetailPresenter
 */
public class JokeDetailPresenterTest {

    private JokeDetailPresenter jokeDetailPresenter;

    @Mock private Bus mockBus;
    @Mock private Invoker<String> mockInvoker;
    @Mock private GetJokeInteractor mockGetJokeInteractor;
    @Mock private JokeDetailView mockView;

    private String fakeMessage;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        jokeDetailPresenter = new JokeDetailPresenter(mockBus, mockInvoker, mockGetJokeInteractor);
        jokeDetailPresenter.attachView(mockView);

        fakeMessage = "Fake message";
    }

    @Test
    public void testOnResume() {
        jokeDetailPresenter.onResume();
        verify(mockBus).register(jokeDetailPresenter);
    }

    @Test public void testOnPause() {
        jokeDetailPresenter.onPause();
        verify(mockBus).unregister(jokeDetailPresenter);
    }

    @Test public void testGetJokeDetail() {
        jokeDetailPresenter.getJokeDetail(fakeMessage);
        verify(mockView).showSnackBar();
        verify(mockInvoker).execute(fakeMessage, mockGetJokeInteractor);
    }

}
