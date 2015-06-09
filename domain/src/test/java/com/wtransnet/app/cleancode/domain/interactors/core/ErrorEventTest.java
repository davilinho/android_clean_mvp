package com.wtransnet.app.cleancode.domain.interactors.core;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Test de la clase BaseEvent
 */
public class ErrorEventTest {

    private ErrorEvent errorEvent;

    @Mock Throwable fakeThrowable;

    @Before public void setUp() {
        MockitoAnnotations.initMocks(this);
        errorEvent = new ErrorEvent();
    }

    @Test public void testHasErrorTrue() {
        errorEvent.setError(fakeThrowable);
        assertThat(errorEvent.hasError(), is(Boolean.TRUE));
    }

    @Test public void testHasErrorFalse() {
        assertThat(errorEvent.hasError(), is(Boolean.FALSE));
    }

}
