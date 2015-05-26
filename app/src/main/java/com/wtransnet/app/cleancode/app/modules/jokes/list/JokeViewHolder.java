package com.wtransnet.app.cleancode.app.modules.jokes.list;

import android.widget.TextView;

/**
 * ViewHolder para el listado de Jokes
 */
public class JokeViewHolder {

    private TextView jokeMessage;

    public TextView getJokeMessage() {
        return jokeMessage;
    }

    public void setJokeMessage(TextView jokeMessage) {
        this.jokeMessage = jokeMessage;
    }
}
