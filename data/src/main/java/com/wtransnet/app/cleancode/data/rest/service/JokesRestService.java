package com.wtransnet.app.cleancode.data.rest.service;

import com.wtransnet.app.cleancode.data.rest.entities.RestResponse;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Interface que define los métodos necesarios para el acceso a los datos de Jokes mediante REST
 */
public interface JokesRestService {

    @GET("/jokes/random/20")
    RestResponse loadJokes(@Query("firstName") String firstName, @Query("lastName") String lastName);
}
