package com.wtransnet.app.cleancode.data.rest.service;

import com.wtransnet.app.cleancode.data.rest.entities.JokeDetailResponse;
import com.wtransnet.app.cleancode.data.rest.entities.JokesListResponse;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Interface que define los métodos necesarios para el acceso a los datos de Jokes mediante REST
 */
public interface JokesRestService {

    @GET("/jokes/random/20")
    JokesListResponse loadJokes(@Query("firstName") String firstName, @Query("lastName") String lastName);

    @GET("/jokes/{id}")
    JokeDetailResponse getJoke(@Path("id") String id);
}
