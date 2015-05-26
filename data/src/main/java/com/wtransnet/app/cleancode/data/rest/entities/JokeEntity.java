package com.wtransnet.app.cleancode.data.rest.entities;

import java.util.Arrays;

/**
 * Bean que contiene los datos de un Joke de la respuesta de una petición REST de Jokes
 */
public class JokeEntity {

    private int id;
    private String joke;
    private String[] categories;

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

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "JokeResponse{" +
                "id=" + id +
                ", joke='" + joke + '\'' +
                ", categories=" + Arrays.toString(categories) +
                '}';
    }
}
