package com.wtransnet.app.cleancode.app.modules.jokes.form;

import android.os.Bundle;
import android.widget.EditText;

import com.wtransnet.app.cleancode.R;
import com.wtransnet.app.cleancode.app.core.activity.AbstractActivity;
import com.wtransnet.app.cleancode.app.modules.jokes.list.JokesListActivity;
import com.wtransnet.app.cleancode.domain.entities.Name;
import com.wtransnet.app.cleancode.presentation.core.presenter.AbstractPresenter;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Actividad para el formulario de Jokes
 */
public class JokesFormActivity extends AbstractActivity {

    @InjectView(R.id.editTxtFirstName)
    EditText editTxtFirstName;

    @InjectView(R.id.editTxtLastName)
    EditText editTxtLastName;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override public int getContentView() {
        return R.layout.form;
    }

    @Override
    protected String getToolbarTitle() {
        return null;
    }

    @Override
    protected AbstractPresenter getPresenter() {
        return null;
    }

    @SuppressWarnings("unchecked")
    @OnClick(R.id.btnGetJokes)
    public void navigateToListJokes() {
        navigator.navigateToActivity(new JokesListActivity(), getName());
    }

    private Name getName() {
        return new Name(getFirstName(), getLastName());
    }

    private String getFirstName() {
        return editTxtFirstName.getText().toString();
    }

    private String getLastName() {
        return editTxtLastName.getText().toString();
    }

}
