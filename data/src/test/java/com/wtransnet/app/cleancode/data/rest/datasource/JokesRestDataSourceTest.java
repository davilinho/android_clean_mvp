package com.wtransnet.app.cleancode.data.rest.datasource;

import com.wtransnet.app.cleancode.data.rest.entities.JokeDetailResponse;
import com.wtransnet.app.cleancode.data.rest.entities.JokeEntity;
import com.wtransnet.app.cleancode.data.rest.entities.JokesListResponse;
import com.wtransnet.app.cleancode.data.rest.mapper.JokeDataMapper;
import com.wtransnet.app.cleancode.data.rest.service.JokesRestService;
import com.wtransnet.app.cleancode.domain.entities.Joke;
import com.wtransnet.app.cleancode.domain.interactors.jokes.load.LoadJokesException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test de la clase JokesRestDatasource
 */
public class JokesRestDataSourceTest {

    private static final String FAKE_JOKE_ID    = "JokeID";
    private static final String FAKE_FIRST_NAME = "FirstName";
    private static final String FAKE_LAST_NAME  = "LastName";

    private static final String SUCCESS_TYPE    = "success";

    private JokesRestDataSource jokesRestDataSource;

    private JokeEntity[] dummyJokeEntityList;
    private List<Joke> dummyJokeList;
    private JokesListResponse dummyJokesListResponse;

    private JokeEntity dummyJokeEntity;
    private Joke dummyJoke;
    private JokeDetailResponse dummyJokeDetailResponse;

    @Mock private JokesRestService mockRestService;
    @Mock private JokeDataMapper mockMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        jokesRestDataSource = new JokesRestDataSource(mockRestService, mockMapper);

        dummyJokeEntityList = new JokeEntity[0];
        dummyJokeList       = new ArrayList<>();

        dummyJokesListResponse = new JokesListResponse();
        dummyJokesListResponse.setValue(dummyJokeEntityList);

        dummyJokeEntity = new JokeEntity();
        dummyJoke       = new Joke();

        dummyJokeDetailResponse = new JokeDetailResponse();
        dummyJokeDetailResponse.setValue(dummyJokeEntity);
    }

    @Test
    public void testLoadJokes() throws LoadJokesException {
        when(mockRestService.loadJokes(FAKE_FIRST_NAME, FAKE_LAST_NAME)).thenReturn(new JokesListResponse());
        jokesRestDataSource.loadJokes(FAKE_FIRST_NAME, FAKE_LAST_NAME);
        verify(mockRestService).loadJokes(FAKE_FIRST_NAME, FAKE_LAST_NAME);
    }

    @Test
    public void testLoadJokesSuccess() throws LoadJokesException {

        dummyJokesListResponse.setType(SUCCESS_TYPE);

        when(mockRestService.loadJokes(FAKE_FIRST_NAME, FAKE_LAST_NAME)).thenReturn(dummyJokesListResponse);
        when(mockMapper.transform(dummyJokeEntityList)).thenReturn(dummyJokeList);

        List<Joke> resultJokeList = jokesRestDataSource.loadJokes(FAKE_FIRST_NAME, FAKE_LAST_NAME);

        assertThat(resultJokeList, is(dummyJokeList));

        verify(mockRestService).loadJokes(FAKE_FIRST_NAME, FAKE_LAST_NAME);
        verify(mockMapper).transform(dummyJokeEntityList);
    }

    @Test
    public void testLoadJokesFail() throws LoadJokesException {

        when(mockRestService.loadJokes(FAKE_FIRST_NAME, FAKE_LAST_NAME)).thenReturn(new JokesListResponse());

        List<Joke> resultJokeList = jokesRestDataSource.loadJokes(FAKE_FIRST_NAME, FAKE_LAST_NAME);

        assertThat(resultJokeList, is(nullValue()));

        verify(mockRestService).loadJokes(FAKE_FIRST_NAME, FAKE_LAST_NAME);
        verify(mockMapper, never()).transform(any(JokeEntity[].class));
    }

    @Test
    public void testGetJoke() throws LoadJokesException {
        when(mockRestService.getJoke(FAKE_JOKE_ID)).thenReturn(new JokeDetailResponse());
        jokesRestDataSource.getJoke(FAKE_JOKE_ID);
        verify(mockRestService).getJoke(FAKE_JOKE_ID);
    }

    @Test
    public void testGetJokeSuccess() throws LoadJokesException {

        dummyJokeDetailResponse.setType(SUCCESS_TYPE);

        when(mockRestService.getJoke(FAKE_JOKE_ID)).thenReturn(dummyJokeDetailResponse);
        when(mockMapper.transform(dummyJokeEntity)).thenReturn(dummyJoke);

        Joke resultJoke = jokesRestDataSource.getJoke(FAKE_JOKE_ID);

        assertThat(resultJoke, is(dummyJoke));

        verify(mockRestService).getJoke(FAKE_JOKE_ID);
        verify(mockMapper).transform(dummyJokeEntity);
    }

    @Test
    public void testGetJokeFail() throws LoadJokesException {

        when(mockRestService.getJoke(FAKE_JOKE_ID)).thenReturn(new JokeDetailResponse());

        Joke resultJoke = jokesRestDataSource.getJoke(FAKE_JOKE_ID);

        assertThat(resultJoke, is(nullValue()));

        verify(mockRestService).getJoke(FAKE_JOKE_ID);
        verify(mockMapper, never()).transform(any(JokeEntity.class));
    }

}
