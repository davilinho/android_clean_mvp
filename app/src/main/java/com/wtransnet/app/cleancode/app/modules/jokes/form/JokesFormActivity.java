package com.wtransnet.app.cleancode.app.modules.jokes.form;

import android.os.Bundle;
import android.widget.EditText;

import com.wtransnet.app.cleancode.R;
import com.wtransnet.app.cleancode.app.common.nav.Navigator;
import com.wtransnet.app.cleancode.app.core.activity.BaseActivity;
import com.wtransnet.app.cleancode.domain.entities.Name;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Actividad para el formulario de Jokes
 */
public class JokesFormActivity extends BaseActivity {

    @InjectView(R.id.editTxtFirstName) EditText editTxtFirstName;
    @InjectView(R.id.editTxtLastName)  EditText editTxtLastName;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override public int getContentView() {
        return R.layout.form;
    }

    @OnClick(R.id.btnGetJokes)
    public void navigateToListJokes() {
        navigator.navigateToJokesList(getName());
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
