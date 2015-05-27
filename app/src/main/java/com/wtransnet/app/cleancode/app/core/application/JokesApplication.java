package com.wtransnet.app.cleancode.app.core.application;

import android.app.Application;
import android.content.Context;


import com.wtransnet.app.cleancode.app.di.AppModule;

import dagger.ObjectGraph;

/**
 * Contexto de la aplicación
 */
public class JokesApplication  extends Application {

    private ObjectGraph objectGraph;

    @Override public void onCreate() {
        super.onCreate();
        initObjectGraph();
    }

    private void initObjectGraph() {
        objectGraph = ObjectGraph.create(new AppModule(this));
        inject(this);
    }

    public void inject(Object object) {
        objectGraph.inject(object);
    }

    public static JokesApplication get(Context context) {
        return (JokesApplication) context.getApplicationContext();
    }

    public ObjectGraph getObjectGraph() {

        if (objectGraph == null) {
            objectGraph = ObjectGraph.create(new AppModule(this));
        }

        return objectGraph;

    }
}
