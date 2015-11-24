package com.wtransnet.app.cleancode.app.di;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;

import com.path.android.jobqueue.JobManager;
import com.squareup.otto.Bus;
import com.wtransnet.app.cleancode.app.common.nav.FragmentNavigator;
import com.wtransnet.app.cleancode.app.common.nav.Navigator;
import com.wtransnet.app.cleancode.app.core.application.JokesApplication;
import com.wtransnet.app.cleancode.app.core.error.CommonErrorHandler;
import com.wtransnet.app.cleancode.app.core.eventbus.AndroidBus;
import com.wtransnet.app.cleancode.app.domain.InvokerImp;
import com.wtransnet.app.cleancode.app.modules.jokes.detail.JokeDetailFragment;
import com.wtransnet.app.cleancode.app.modules.jokes.detail.JokesDetailActivity;
import com.wtransnet.app.cleancode.app.modules.jokes.form.JokesFormActivity;
import com.wtransnet.app.cleancode.app.modules.jokes.list.JokesListActivity;
import com.wtransnet.app.cleancode.app.modules.jokes.list.JokesListAdapter;
import com.wtransnet.app.cleancode.app.net.OkHttpClientFactory;
import com.wtransnet.app.cleancode.app.net.RetrofitJacksonConverter;
import com.wtransnet.app.cleancode.data.rest.datasource.JokesRestDataSource;
import com.wtransnet.app.cleancode.data.rest.mapper.JokeDataMapper;
import com.wtransnet.app.cleancode.data.rest.service.JokesRestService;
import com.wtransnet.app.cleancode.domain.entities.Joke;
import com.wtransnet.app.cleancode.domain.entities.Name;
import com.wtransnet.app.cleancode.domain.interactors.core.DataEvent;
import com.wtransnet.app.cleancode.domain.interactors.core.Invoker;
import com.wtransnet.app.cleancode.domain.interactors.jokes.get.GetJokeInteractor;
import com.wtransnet.app.cleancode.domain.interactors.jokes.load.LoadJokesInteractor;
import com.wtransnet.app.cleancode.domain.repository.JokesRepository;
import com.wtransnet.app.cleancode.presentation.modules.jokes.detail.JokeDetailPresenter;
import com.wtransnet.app.cleancode.presentation.modules.jokes.list.JokesListPresenter;
import com.wtransnet.app.cleancode.repository.jokes.JokesRepositoryImpl;
import com.wtransnet.app.cleancode.repository.jokes.datasources.JokesDataSource;

import java.util.List;

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
            JokesDetailActivity.class,
            JokesListAdapter.class,
            JokesListPresenter.class,
            JokeDetailPresenter.class,
            LoadJokesInteractor.class,
            GetJokeInteractor.class,
            CommonErrorHandler.class,
            JokeDetailFragment.class
    }
)
public class AppModule {

    private final Application app;

    public AppModule(Application app) {
        this.app = app;
    }

    @Provides @Singleton
    LayoutInflater provideLayoutInflater() {
        return (LayoutInflater) app.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Provides @Singleton
    Bus provideEventbus() {
        return new AndroidBus();
    }

    @Provides @Singleton
    Navigator provideNavigator() {
        return new Navigator(app.getApplicationContext());
    }

    @Provides @Singleton
    FragmentNavigator provideFragmentNavigator() {
        return new FragmentNavigator();
    }

    @Provides @Singleton
    JobManager provideJobManager() {
        return new JobManager(app);
    }

    @Provides @Singleton
    Invoker<Name> provideInvokerName(JobManager jobManager) {
        return new InvokerImp<>(jobManager);
    }

    @Provides @Singleton
    Invoker<String> provideInvokerString(JobManager jobManager) {
        return new InvokerImp<>(jobManager);
    }

    // Services

    @Provides @Singleton
    JokesRestService provideRestService() {

        JokesRestService restService = null;

        try {
            restService = new RestAdapter.Builder()
                    .setEndpoint("http://api.icndb.com/")
                    .setConverter(new RetrofitJacksonConverter())
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setClient(new OkClient(OkHttpClientFactory.createSecuredClient(app)))
                    .build()
                    .create(JokesRestService.class);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return restService;
    }

    // Mappers

    @Provides @Singleton
    JokeDataMapper provideJokeDataMapper() {
        return new JokeDataMapper();
    }

    // DataSources

    @Provides @Singleton
    JokesDataSource provideJokesDataSource(JokesRestService jokesRestService, JokeDataMapper jokeDataMapper) {
        return new JokesRestDataSource(jokesRestService, jokeDataMapper);
    }

    // Repositories

    @Provides @Singleton
    JokesRepository provideJokesRepository(JokesDataSource jokesDataSource) {
        return new JokesRepositoryImpl(jokesDataSource);
    }

    // Events (No pueden ser singleton ya que tienen estado)

    @Provides DataEvent<List<Joke>> provideJokesListEvent() {
        return new DataEvent<>();
    }

    @Provides DataEvent<Joke> provideJokeEvent() {
        return new DataEvent<>();
    }

    // Interactors (No pueden ser singleton ya que Event se debe de generar para cada instancia)

    @Provides
    LoadJokesInteractor provideLoadJokesInteractor(Bus bus, DataEvent<List<Joke>> event, JokesRepository repository) {
        return new LoadJokesInteractor(bus, event, repository);
    }

    @Provides
    GetJokeInteractor provideGetJokeInteractor(Bus bus, DataEvent<Joke> event, JokesRepository repository) {
        return new GetJokeInteractor(bus, event, repository);
    }

    // Presenters

    @Provides @Singleton
    JokesListPresenter provideJokesListPresenter(Bus bus, Invoker<Name> invoker, LoadJokesInteractor interactor) {
        return new JokesListPresenter(bus, invoker, interactor);
    }

    @Provides @Singleton
    JokeDetailPresenter provideJokeDetailPresenter(Bus bus, Invoker<String> invoker, GetJokeInteractor interactor) {
        return new JokeDetailPresenter(bus, invoker, interactor);
    }

    // Errors

    @Provides @Singleton
    CommonErrorHandler provideCommonErrorHandler() {
        return new CommonErrorHandler();
    }

    // Fragments

    @Provides
    JokeDetailFragment provideJokeDetailFragment() {
        return new JokeDetailFragment();
    }

}
