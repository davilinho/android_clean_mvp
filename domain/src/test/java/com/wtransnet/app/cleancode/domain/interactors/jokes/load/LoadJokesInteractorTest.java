package com.wtransnet.app.cleancode.domain.interactors.jokes.load;

import com.squareup.otto.Bus;
import com.wtransnet.app.cleancode.domain.entities.Joke;
import com.wtransnet.app.cleancode.domain.entities.Name;
import com.wtransnet.app.cleancode.domain.interactors.core.DataEvent;
import com.wtransnet.app.cleancode.domain.repository.JokesRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import javax.inject.Named;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test de la clase LoadJokesInteractor
 */
public class LoadJokesInteractorTest {

    private LoadJokesInteractor loadJokesInteractor;

    @Mock private Bus mockBus;
    @Mock @Named("jokesList") private DataEvent<List<Joke>> mockEvent;
    @Mock private JokesRepository mockRepository;

    @Mock private Name fakeName;
    @Mock private List<Joke> fakeJokesList;
    @Mock private JokesException fakeLoadJokesException;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        loadJokesInteractor = new LoadJokesInteractor(mockBus, mockEvent, mockRepository);
    }

    @Test
    public void testExecuteHappyCase() throws JokesException {

        when(mockRepository.loadJokes(fakeName)).thenReturn(fakeJokesList);

        loadJokesInteractor.execute(fakeName);

        verify(mockRepository).loadJokes(fakeName);
        verify(mockEvent).setData(fakeJokesList);
        verify(mockEvent, never()).setError(fakeLoadJokesException);
        verify(mockBus).post(mockEvent);
    }

    @Test public void testExecuteWithException() throws JokesException {

        when(mockRepository.loadJokes(fakeName)).thenThrow(fakeLoadJokesException);

        loadJokesInteractor.execute(fakeName);

        verify(mockRepository).loadJokes(fakeName);
        verify(mockEvent, never()).setData(fakeJokesList);
        verify(mockEvent).setError(fakeLoadJokesException);
        verify(mockBus).post(mockEvent);
    }
}
