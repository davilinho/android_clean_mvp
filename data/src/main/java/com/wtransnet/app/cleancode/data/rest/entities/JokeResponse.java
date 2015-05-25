package com.wtransnet.app.cleancode.data.rest.entities;

/**
 * Bean que contiene los datos de un Joke de la respuesta de una petición REST de Jokes
 */
public class JokeResponse {

    private int id;
    private String joke;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    @Override
    public String toString() {
        return "JokeResponse{" +
                "id=" + id +
                ", joke='" + joke + '\'' +
                '}';
    }
}
