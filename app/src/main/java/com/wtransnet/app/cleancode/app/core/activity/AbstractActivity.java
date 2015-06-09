package com.wtransnet.app.cleancode.app.core.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.wtransnet.app.cleancode.R;
import com.wtransnet.app.cleancode.app.common.nav.FragmentNavigator;
import com.wtransnet.app.cleancode.app.common.nav.Navigator;
import com.wtransnet.app.cleancode.app.core.application.JokesApplication;
import com.wtransnet.app.cleancode.app.core.error.CommonErrorHandler;
import com.wtransnet.app.cleancode.presentation.core.error.ErrorHandler;
import com.wtransnet.app.cleancode.presentation.core.presenter.AbstractPresenter;
import com.wtransnet.app.cleancode.presentation.core.view.BaseView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;

/**
 * Activity base con la funcionalidad base de todas las demas Activities
 */
public abstract class AbstractActivity<T1, T2, V extends BaseView> extends AppCompatActivity implements ErrorHandler {

    @Inject
    protected Navigator navigator;

    @Inject
    protected FragmentNavigator fragmentNavigator;

    @Inject
    CommonErrorHandler commonErrorHandler;

    @Optional @InjectView (R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContentView());

        injectDependencies();
        injectViewDependencies();

        attachToolbarTitle();
        attachView();

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (checkHasPresenter()) {
            getPresenter().onResume();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        if (checkHasPresenter()) {
            getPresenter().onPause();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (checkHasPresenter()) {
            getPresenter().detachView();
        }

    }

    @Override
    public void manageCommonErrors() {
        commonErrorHandler.showCommonErrors(this);
    }

    protected abstract int getContentView();

    protected abstract String getToolbarTitle();

    protected abstract AbstractPresenter<T1, T2, V> getPresenter();

    private void injectDependencies() {
        JokesApplication.get(this).inject(this);
    }

    private void injectViewDependencies() {
        ButterKnife.inject(this);
    }

    private void attachToolbarTitle() {

        if (toolbar != null) {
            toolbar.setTitle(getToolbarTitle());
        }
    }

    @SuppressWarnings("unchecked")
    private void attachView() {

        if (checkHasPresenter()) {
            getPresenter().attachView((V) this);
        }

    }

    private boolean checkHasPresenter() {
        return getPresenter() != null;
    }

}
