package com.wtransnet.app.cleancode.app.di;

import android.app.Application;

import com.path.android.jobqueue.JobManager;
import com.squareup.otto.Bus;
import com.wtransnet.app.cleancode.app.common.nav.Navigator;
import com.wtransnet.app.cleancode.app.core.application.JokesApplication;
import com.wtransnet.app.cleancode.app.core.eventbus.AndroidBus;
import com.wtransnet.app.cleancode.app.domain.InteractorInvokerImp;
import com.wtransnet.app.cleancode.app.modules.jokes.JokesFormActivity;
import com.wtransnet.app.cleancode.app.modules.jokes.JokesListActivity;
import com.wtransnet.app.cleancode.app.net.OkHttpClientFactory;
import com.wtransnet.app.cleancode.app.net.RetrofitJacksonConverter;
import com.wtransnet.app.cleancode.data.rest.datasource.JokesRestDataSource;
import com.wtransnet.app.cleancode.data.rest.service.JokesRestService;
import com.wtransnet.app.cleancode.domain.interactors.core.InteractorInvoker;
import com.wtransnet.app.cleancode.domain.interactors.jokes.load.LoadJokesInteractor;
import com.wtransnet.app.cleancode.domain.repository.JokesRepository;
import com.wtransnet.app.cleancode.presentation.modules.jokes.list.JokesListPresenter;
import com.wtransnet.app.cleancode.repository.jokes.JokesRepositoryImpl;
import com.wtransnet.app.cleancode.repository.jokes.datasources.JokesDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Módulo general de la aplicación
 */
@Module(
    injects = {
            JokesApplication.class,
            JokesFormActivity.class,
            JokesListActivity.class,
            JokesListPresenter.class,
            LoadJokesInteractor.class
    }
)
public class AppModule {

    private final Application app;

    public AppModule(Application app) {
        this.app = app;
    }

    @Provides @Singleton Bus provideEventbus() {
        return new AndroidBus();
    }

    @Provides @Singleton
    Navigator provideNavigator() {
        return new Navigator();
    }

    @Provides @Singleton JobManager provideJobManager() {
        return new JobManager(app);
    }

    @Provides @Singleton
    InteractorInvoker provideInteractorInvoker(JobManager jobManager) {
        return new InteractorInvokerImp(jobManager);
    }

    // Services

    @Provides @Singleton
    JokesRestService provideApiService() {

        try {
            return new RestAdapter.Builder()
                    .setEndpoint("http://api.icndb.com/")
                    .setConverter(new RetrofitJacksonConverter())
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setClient(new OkClient(OkHttpClientFactory.createSecuredClient(app)))
                    .build()
                    .create(JokesRestService.class);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // DataSources

    @Provides @Singleton
    JokesDataSource provideJokesDataSource(JokesRestService jokesRestService) {
        return new JokesRestDataSource(jokesRestService);
    }

    // Repositories

    @Provides @Singleton
    JokesRepository provideJokesRepository(JokesDataSource jokesDataSource) {
        return new JokesRepositoryImpl(jokesDataSource);
    }

    // Interactors

    @Provides @Singleton
    LoadJokesInteractor provideLoadJokesInteractor(Bus bus, JokesRepository repository) {
        return new LoadJokesInteractor(bus, repository);
    }

    // Presenters

    @Provides @Singleton
    JokesListPresenter provideJokesListPresenter(Bus bus, InteractorInvoker invoker, LoadJokesInteractor interactor) {
        return new JokesListPresenter(bus, invoker, interactor);
    }
}
