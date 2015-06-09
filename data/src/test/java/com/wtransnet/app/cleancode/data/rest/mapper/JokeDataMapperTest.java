package com.wtransnet.app.cleancode.data.rest.mapper;

import com.wtransnet.app.cleancode.data.rest.entities.JokeEntity;
import com.wtransnet.app.cleancode.domain.entities.Joke;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Test de la clase JokeDataMapperTest
 */
public class JokeDataMapperTest {

    private static final int FAKE_JOKE_ID = 143;
    private static final String FAKE_JOKE = "Fake joke";

    private JokeDataMapper jokeDataMapper;

    @Before
    public void setUp() {
        jokeDataMapper = new JokeDataMapper();
    }

    @Test
    public void testTransformJokeEntity() {

        JokeEntity jokeEntity = createFakeJokeEntity();
        Joke joke = jokeDataMapper.transform(jokeEntity);

        assertThat(joke, is(instanceOf(Joke.class)));
        assertThat(joke.getId(), is(FAKE_JOKE_ID));
        assertThat(joke.getJoke(), is(FAKE_JOKE));
    }

    @Test
    public void testTransformJokeEntityList() {

        JokeEntity mockJokeEntityOne = mock(JokeEntity.class);
        JokeEntity mockJokeEntityTwo = mock(JokeEntity.class);

        JokeEntity[] jokeEntityList = {mockJokeEntityOne, mockJokeEntityTwo};

        List<Joke> jokeList = jokeDataMapper.transform(jokeEntityList);

        assertThat(jokeList.size(), is(2));
        assertThat(jokeList.get(0), is(instanceOf(Joke.class)));
        assertThat(jokeList.get(1), is(instanceOf(Joke.class)));
    }

    private JokeEntity createFakeJokeEntity() {

        JokeEntity jokeEntity = new JokeEntity();

        jokeEntity.setId(FAKE_JOKE_ID);
        jokeEntity.setJoke(FAKE_JOKE);

        return jokeEntity;
    }
}
