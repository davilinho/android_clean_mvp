package com.wtransnet.app.cleancode.app.common.nav;

import android.content.Context;
import android.content.Intent;

import com.wtransnet.app.cleancode.app.common.constants.IntentValuesConstants;
import com.wtransnet.app.cleancode.app.modules.jokes.JokesListActivity;
import com.wtransnet.app.cleancode.presentation.bean.Name;

/**
 * Class used to navigate through the aplication
 */
public class Navigator {

    public void navigateToJokesList(Context context, Name name) {

        if (context != null) {

            final Intent intent = new Intent(context, JokesListActivity.class);
            intent.putExtra(IntentValuesConstants.NAME, name);

            context.startActivity(intent);
        }
    }
}
