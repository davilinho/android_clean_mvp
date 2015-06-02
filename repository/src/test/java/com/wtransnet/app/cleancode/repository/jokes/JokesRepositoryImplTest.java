package com.wtransnet.app.cleancode.repository.jokes;

import com.wtransnet.app.cleancode.domain.entities.Name;
import com.wtransnet.app.cleancode.domain.interactors.jokes.load.JokesException;
import com.wtransnet.app.cleancode.domain.repository.JokesRepository;
import com.wtransnet.app.cleancode.repository.jokes.datasources.JokesDataSource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

/**
 * Test de la clase JokesRepositoryImpl
 */
public class JokesRepositoryImplTest {

    private JokesRepository jokesRepository;

    @Mock private JokesDataSource mockJokesDataSource;

    @Before public void setUp() {
        MockitoAnnotations.initMocks(this);
        jokesRepository = new JokesRepositoryImpl(mockJokesDataSource);
    }

    @Test public void testLoadJokes() throws JokesException {
        Name fakeName = new Name();
        jokesRepository.loadJokes(fakeName);
        verify(mockJokesDataSource).loadJokes(fakeName);
    }

    @Test public void testGetJoke() throws JokesException {
        String fakeJokeId = "FAKE JOKE ID";
        jokesRepository.getJoke(fakeJokeId);
        verify(mockJokesDataSource).getJoke(fakeJokeId);
    }

}
