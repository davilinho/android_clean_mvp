package com.wtransnet.app.cleancode.data.rest.datasource;

import com.wtransnet.app.cleancode.data.rest.entities.JokeDetailResponse;
import com.wtransnet.app.cleancode.data.rest.entities.JokeEntity;
import com.wtransnet.app.cleancode.data.rest.entities.JokesListResponse;
import com.wtransnet.app.cleancode.data.rest.mapper.JokeDataMapper;
import com.wtransnet.app.cleancode.data.rest.service.JokesRestService;
import com.wtransnet.app.cleancode.domain.entities.Joke;
import com.wtransnet.app.cleancode.domain.entities.Name;
import com.wtransnet.app.cleancode.domain.interactors.jokes.load.JokesException;

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

    private Name fakeName;

    private JokeEntity[] fakeJokeEntityList;
    private List<Joke> fakeJokeList;
    private JokesListResponse fakeJokesListResponse;

    private JokeEntity fakeJokeEntity;
    private Joke fakeJoke;
    private JokeDetailResponse fakeJokeDetailResponse;

    @Mock private JokesRestService mockRestService;
    @Mock private JokeDataMapper mockMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        jokesRestDataSource = new JokesRestDataSource(mockRestService, mockMapper);

        fakeName = new Name();
        fakeName.setFirstName(FAKE_FIRST_NAME);
        fakeName.setLastName(FAKE_LAST_NAME);

        fakeJokeEntityList = new JokeEntity[0];
        fakeJokeList = new ArrayList<>();

        fakeJokesListResponse = new JokesListResponse();
        fakeJokesListResponse.setValue(fakeJokeEntityList);

        fakeJokeEntity = new JokeEntity();
        fakeJoke = new Joke();

        fakeJokeDetailResponse = new JokeDetailResponse();
        fakeJokeDetailResponse.setValue(fakeJokeEntity);
    }

    @Test
    public void testLoadJokes() throws JokesException {
        when(mockRestService.loadJokes(FAKE_FIRST_NAME, FAKE_LAST_NAME)).thenReturn(new JokesListResponse());
        jokesRestDataSource.loadJokes(fakeName);
        verify(mockRestService).loadJokes(FAKE_FIRST_NAME, FAKE_LAST_NAME);
    }

    @Test
    public void testLoadJokesSuccess() throws JokesException {

        fakeJokesListResponse.setType(SUCCESS_TYPE);

        when(mockRestService.loadJokes(FAKE_FIRST_NAME, FAKE_LAST_NAME)).thenReturn(fakeJokesListResponse);
        when(mockMapper.transform(fakeJokeEntityList)).thenReturn(fakeJokeList);

        List<Joke> resultJokeList = jokesRestDataSource.loadJokes(fakeName);

        assertThat(resultJokeList, is(fakeJokeList));

        verify(mockRestService).loadJokes(FAKE_FIRST_NAME, FAKE_LAST_NAME);
        verify(mockMapper).transform(fakeJokeEntityList);
    }

    @Test
    public void testLoadJokesFail() throws JokesException {

        when(mockRestService.loadJokes(FAKE_FIRST_NAME, FAKE_LAST_NAME)).thenReturn(new JokesListResponse());

        List<Joke> resultJokeList = jokesRestDataSource.loadJokes(fakeName);

        assertThat(resultJokeList, is(nullValue()));

        verify(mockRestService).loadJokes(FAKE_FIRST_NAME, FAKE_LAST_NAME);
        verify(mockMapper, never()).transform(any(JokeEntity[].class));
    }

    @Test
    public void testGetJoke() throws JokesException {
        when(mockRestService.getJoke(FAKE_JOKE_ID)).thenReturn(new JokeDetailResponse());
        jokesRestDataSource.getJoke(FAKE_JOKE_ID);
        verify(mockRestService).getJoke(FAKE_JOKE_ID);
    }

    @Test
    public void testGetJokeSuccess() throws JokesException {

        fakeJokeDetailResponse.setType(SUCCESS_TYPE);

        when(mockRestService.getJoke(FAKE_JOKE_ID)).thenReturn(fakeJokeDetailResponse);
        when(mockMapper.transform(fakeJokeEntity)).thenReturn(fakeJoke);

        Joke resultJoke = jokesRestDataSource.getJoke(FAKE_JOKE_ID);

        assertThat(resultJoke, is(fakeJoke));

        verify(mockRestService).getJoke(FAKE_JOKE_ID);
        verify(mockMapper).transform(fakeJokeEntity);
    }

    @Test
    public void testGetJokeFail() throws JokesException {

        when(mockRestService.getJoke(FAKE_JOKE_ID)).thenReturn(new JokeDetailResponse());

        Joke resultJoke = jokesRestDataSource.getJoke(FAKE_JOKE_ID);

        assertThat(resultJoke, is(nullValue()));

        verify(mockRestService).getJoke(FAKE_JOKE_ID);
        verify(mockMapper, never()).transform(any(JokeEntity.class));
    }

}
