package com.wtransnet.app.cleancode.app.modules.jokes.list;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.wtransnet.app.cleancode.R;
import com.wtransnet.app.cleancode.app.core.activity.AbstractActivity;
import com.wtransnet.app.cleancode.app.modules.jokes.detail.JokesDetailActivity;
import com.wtransnet.app.cleancode.domain.entities.Joke;
import com.wtransnet.app.cleancode.domain.entities.Name;
import com.wtransnet.app.cleancode.presentation.core.presenter.AbstractPresenter;
import com.wtransnet.app.cleancode.presentation.modules.jokes.list.JokesListView;
import com.wtransnet.app.cleancode.presentation.modules.jokes.list.JokesListPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;

import static com.wtransnet.app.cleancode.app.common.constants.IntentValuesConstants.ACTIVITY_DATA;


/**
 * Actividad para el listado de Jokes
 */
public class JokesListActivity extends AbstractActivity implements JokesListView, AdapterView.OnItemClickListener {

    @Inject
    JokesListPresenter presenter;

    @InjectView(R.id.list)
    ListView listView;

    private Name name;
    private List<Joke> itemsList;
    private ArrayAdapter<Joke> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeUi();
        initializeData(savedInstanceState);
    }

    @Override
    public int getContentView() {
        return R.layout.list;
    }

    @Override
    protected String getToolbarTitle() {
        return getResources().getString(R.string.titleActivityList);
    }

    @Override
    protected AbstractPresenter<Name, List<Joke>, JokesListView> getPresenter() {
        return presenter;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        if (outState != null) {
            outState.putSerializable(ACTIVITY_DATA, name);
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
        listView.setOnItemClickListener(this);
    }

    private void initializeData(Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            name = (Name) getIntent().getSerializableExtra(ACTIVITY_DATA);
            loadJokesList();
        } else {
            name = (Name) savedInstanceState.getSerializable(ACTIVITY_DATA);
        }

    }

    private void loadJokesList() {
        presenter.loadJokesList(name);
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

    @SuppressWarnings("unchecked")
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        navigator.navigateToActivity(new JokesDetailActivity(),
                String.valueOf(((Joke) parent.getItemAtPosition(position)).getId()));
    }

    @Override
    public void showSnackBar() { }

    @Override
    public void hideSnackBar() { }

}
