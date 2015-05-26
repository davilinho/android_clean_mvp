package com.wtransnet.app.cleancode.data.rest.entities;

import java.util.Arrays;

/**
 * Bean que contiene los datos de respuesta de una petición REST de Jokes
 */
public class RestResponse {

    private String type;
    private JokeEntity[] value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JokeEntity[] getValue() {
        return value;
    }

    public void setValue(JokeEntity[] value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "RestResponse{" +
                "type='" + type + '\'' +
                ", value=" + Arrays.toString(value) +
                '}';
    }
}
