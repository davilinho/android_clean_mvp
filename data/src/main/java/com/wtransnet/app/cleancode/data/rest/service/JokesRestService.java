package com.wtransnet.app.cleancode.data.rest.service;

import retrofit.http.GET;

/**
 * Interface que define los métodos necesarios para el acceso a los datos de Jokes mediante REST
 */
public interface JokesRestService {

    @GET("/jokes/random/10")
    com.wtransnet.app.cleancode.data.rest.entities.RestResponse loadJokes();
}
