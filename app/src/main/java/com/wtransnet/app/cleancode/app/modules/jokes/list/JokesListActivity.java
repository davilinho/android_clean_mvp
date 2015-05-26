package com.wtransnet.app.cleancode.app.modules.jokes.list;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.wtransnet.app.cleancode.R;
import com.wtransnet.app.cleancode.app.core.activity.BaseActivity;
import com.wtransnet.app.cleancode.domain.entities.Joke;
import com.wtransnet.app.cleancode.domain.entities.Name;
import com.wtransnet.app.cleancode.presentation.modules.jokes.list.JokesListPresenter;
import com.wtransnet.app.cleancode.presentation.modules.jokes.list.JokesListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;

import static com.wtransnet.app.cleancode.app.common.constants.IntentValuesConstants.*;


/**
 * Actividad para el listado de Jokes
 */
public class JokesListActivity extends BaseActivity implements JokesListView {

    @Inject JokesListPresenter presenter;

    @InjectView(R.id.list) ListView listView;

    private Name name;
    private List<Joke> itemsList;
    private ArrayAdapter<Joke> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.attachView(this);

        initializeUi();
        initializeData(savedInstanceState);
    }

    @Override
    public int getContentView() {
        return R.layout.list;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        if (outState != null) {
            outState.putSerializable(NAME, name);
        }

        super.onSaveInstanceState(outState);
    }

    private void initializeUi() {
        initializeListView();
    }

    private void initializeListView() {
        itemsList = new ArrayList<>();
        adapter = new JokesListAdapter(this, itemsList);
        listView.setAdapter(adapter);
    }

    private void initializeData(Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            name = (Name) getIntent().getSerializableExtra(NAME);
            this.loadJokesList(name);
        } else {
            name = (Name) savedInstanceState.getSerializable(NAME);
        }
    }

    private void loadJokesList(Name name) {
        presenter.loadJokesList(name);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void refreshJokesList(List<Joke> jokes) {
        itemsList.clear();
        itemsList.addAll(jokes);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadJokesError() {
        Toast.makeText(this, R.string.error_loading_jokes, Toast.LENGTH_SHORT).show();
    }
}
