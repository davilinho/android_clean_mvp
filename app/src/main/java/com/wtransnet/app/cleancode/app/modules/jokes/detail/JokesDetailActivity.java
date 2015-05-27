package com.wtransnet.app.cleancode.app.modules.jokes.detail;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.nispok.snackbar.listeners.ActionClickListener;
import com.wtransnet.app.cleancode.R;
import com.wtransnet.app.cleancode.app.core.activity.BaseActivity;
import com.wtransnet.app.cleancode.domain.entities.Joke;
import com.wtransnet.app.cleancode.presentation.modules.jokes.detail.JokeDetailPresenter;
import com.wtransnet.app.cleancode.presentation.modules.jokes.detail.JokeDetailView;

import javax.inject.Inject;

import butterknife.InjectView;

import static com.wtransnet.app.cleancode.app.common.constants.IntentValuesConstants.ID;

/**
 * Created by dmartin on 26/05/2015.
 */
public class JokesDetailActivity extends BaseActivity implements JokeDetailView {

    @InjectView(R.id.txtDetail) TextView txtDetail;

    @Inject JokeDetailPresenter presenter;

    private String id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.attachView(this);

        initializeData(savedInstanceState);
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
    public int getContentView() {
        return R.layout.detail;
    }

    private void initializeData(Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            id = getIntent().getStringExtra(ID);
        } else {
            id = savedInstanceState.getString(ID);
        }

        getJokeDetail();

    }

    private void getJokeDetail() {
        presenter.getJokeDetail(id);
    }

    @Override
    public void showSnackBar() {
        Snackbar.with(getApplicationContext()).text(R.string.loadingData)
                .actionLabel(R.string.back)
                .actionColorResource(android.R.color.holo_orange_light)
                .actionListener(new ActionClickListener() {
                    @Override
                    public void onActionClicked(Snackbar snackbar) {
                        onBackPressed();
                    }
                }).show(this);
    }

    @Override
    public void hideSnackBar() {
        SnackbarManager.dismiss();
    }

    @Override
    public void showJokeDetail(Joke joke) {
        txtDetail.setText(joke.getJoke());
    }

    @Override
    public void showJokeDetailError() {
        Toast.makeText(this, R.string.error_loading_detail_joke, Toast.LENGTH_SHORT).show();
    }
}
