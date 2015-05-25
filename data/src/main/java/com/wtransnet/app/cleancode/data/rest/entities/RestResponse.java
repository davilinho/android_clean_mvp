package com.wtransnet.app.cleancode.data.rest.entities;

import java.util.Arrays;

/**
 * Bean que contiene los datos de respuesta de una petición REST de Jokes
 */
public class RestResponse {

    private String type;
    private JokeResponse[] value;
    private String[] categories;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JokeResponse[] getValue() {
        return value;
    }

    public void setValue(JokeResponse[] value) {
        this.value = value;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "RestResponse{" +
                "type='" + type + '\'' +
                ", value=" + Arrays.toString(value) +
                ", categories=" + Arrays.toString(categories) +
                '}';
    }
}
