package com.wtransnet.app.cleancode.app.core.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.wtransnet.app.cleancode.app.core.application.JokesApplication;

import butterknife.ButterKnife;

/**
 * Activity base con la funcionalidad base de todas las demas Activities
 */
public abstract class BaseActivity extends ActionBarActivity {

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());

        this.injectDependencies();
        this.injectViews();
    }

    /**
     * Debe devolver el id del layout de la Activity
     */
    public abstract int getContentView();

    private void injectDependencies() {
        JokesApplication.get(this).inject(this);
    }

    private void injectViews() {
        ButterKnife.inject(this);
    }
}
