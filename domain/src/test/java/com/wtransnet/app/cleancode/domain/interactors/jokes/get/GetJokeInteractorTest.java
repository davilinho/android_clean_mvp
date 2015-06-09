package com.wtransnet.app.cleancode.domain.interactors.jokes.get;

import com.squareup.otto.Bus;
import com.wtransnet.app.cleancode.domain.entities.Joke;
import com.wtransnet.app.cleancode.domain.interactors.core.DataEvent;
import com.wtransnet.app.cleancode.domain.interactors.jokes.load.JokesException;
import com.wtransnet.app.cleancode.domain.repository.JokesRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.inject.Named;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test de la clase GetJokeInteractor
 */
public class GetJokeInteractorTest {

    private static final String FAKE_JOKE_ID = "JokeID";

    private GetJokeInteractor getJokeInteractor;

    @Mock private Bus mockBus;
    @Mock @Named("joke") private DataEvent<Joke> mockEvent;
    @Mock private JokesRepository mockRepository;

    @Mock private Joke fakeJoke;
    @Mock private JokesException fakeJokesException;

    @Before public void setUp() {
        MockitoAnnotations.initMocks(this);
        getJokeInteractor = new GetJokeInteractor(mockBus, mockEvent, mockRepository);
    }

    @Test public void testExecuteHappyCase() throws JokesException {

        when(mockRepository.getJoke(FAKE_JOKE_ID)).thenReturn(fakeJoke);

        getJokeInteractor.execute(FAKE_JOKE_ID);

        verify(mockRepository).getJoke(FAKE_JOKE_ID);
        verify(mockEvent).setData(fakeJoke);
        verify(mockEvent, never()).setError(fakeJokesException);
        verify(mockBus).post(mockEvent);
    }

    @Test public void testExecuteWithException() throws JokesException {

        when(mockRepository.getJoke(FAKE_JOKE_ID)).thenThrow(fakeJokesException);

        getJokeInteractor.execute(FAKE_JOKE_ID);

        verify(mockRepository).getJoke(FAKE_JOKE_ID);
        verify(mockEvent, never()).setData(any(Joke.class));
        verify(mockEvent).setError(fakeJokesException);
        verify(mockBus).post(mockEvent);
    }

}
