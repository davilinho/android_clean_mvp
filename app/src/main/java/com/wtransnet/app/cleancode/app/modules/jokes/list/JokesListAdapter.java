package com.wtransnet.app.cleancode.app.modules.jokes.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wtransnet.app.cleancode.R;
import com.wtransnet.app.cleancode.app.core.application.JokesApplication;
import com.wtransnet.app.cleancode.domain.entities.Joke;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

/**
 * Adapter para el listado de Jokes
 */
public class JokesListAdapter extends ArrayAdapter<Joke> implements Serializable {

    @Inject public LayoutInflater inflater;

    public JokesListAdapter(Context context, List<Joke> jokes) {
        super(context, 0, jokes);
        JokesApplication.get(context).inject(this);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        JokeViewHolder holder = null;

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.item_joke, parent, false);

            holder = new JokeViewHolder();
            holder.setJokeMessage((TextView) convertView.findViewById(R.id.jokeMessage));

            convertView.setTag(holder);

        } else {

            holder = (JokeViewHolder) convertView.getTag();
        }

        Joke joke = getItem(position);
        holder.getJokeMessage().setText(joke.getJoke());

        return convertView;
    }
}
