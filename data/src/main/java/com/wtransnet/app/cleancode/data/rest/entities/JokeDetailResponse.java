package com.wtransnet.app.cleancode.data.rest.entities;

import java.util.Arrays;

/**
 * Created by dmartin on 27/05/2015.
 */
public class JokeDetailResponse {

    private String type;
    private JokeEntity value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JokeEntity getValue() {
        return value;
    }

    public void setValue(JokeEntity value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "JokesListResponse{" +
                "type='" + type + '\'' +
                ", value=" + value +
                '}';
    }

}
