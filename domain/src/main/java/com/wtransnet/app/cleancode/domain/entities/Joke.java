package com.wtransnet.app.cleancode.domain.entities;

/**
 * Bean que contiene las propiedades de un Joke
 */
public class Joke {

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
        return "Joke{" +
                "id=" + id +
                ", joke='" + joke + '\'' +
                '}';
    }
}
