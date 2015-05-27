package com.wtransnet.app.cleancode.app.core.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.wtransnet.app.cleancode.R;
import com.wtransnet.app.cleancode.app.common.nav.Navigator;
import com.wtransnet.app.cleancode.app.core.application.JokesApplication;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Activity base con la funcionalidad base de todas las demas Activities
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Inject protected Navigator navigator;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());

        injectToolbar();
        injectDependencies();
        injectViews();
    }

    /**
     * Debe devolver el id del layout de la Activity
     */
    public abstract int getContentView();

    private void injectToolbar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

    }

    private void injectDependencies() {
        JokesApplication.get(this).inject(this);
    }

    private void injectViews() {
        ButterKnife.inject(this);
    }
}
