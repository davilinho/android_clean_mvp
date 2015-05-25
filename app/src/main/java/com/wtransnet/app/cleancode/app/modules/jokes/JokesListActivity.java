package com.wtransnet.app.cleancode.app.modules.jokes;

import android.os.Bundle;

import com.wtransnet.app.cleancode.R;
import com.wtransnet.app.cleancode.app.core.activity.BaseActivity;
import com.wtransnet.app.cleancode.presentation.bean.Name;
import com.wtransnet.app.cleancode.presentation.modules.jokes.list.JokesListPresenter;

import javax.inject.Inject;

import static com.wtransnet.app.cleancode.app.common.constants.IntentValuesConstants.*;


/**
 * Actividad para el listado de Jokes
 */
public class JokesListActivity extends BaseActivity {

    @Inject
    JokesListPresenter presenter;

    private Name name;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeData(savedInstanceState);
    }

    @Override public int getContentView() {
        return R.layout.list;
    }

    @Override protected void onSaveInstanceState(Bundle outState) {

        if (outState != null) {
            outState.putSerializable(NAME, name);
        }

        super.onSaveInstanceState(outState);
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

    @Override protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override protected void onPause() {
        super.onPause();
        presenter.onPause();
    }
}
