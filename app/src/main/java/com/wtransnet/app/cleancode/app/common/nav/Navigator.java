package com.wtransnet.app.cleancode.app.common.nav;

import android.content.Context;
import android.content.Intent;

import com.wtransnet.app.cleancode.app.common.constants.IntentValuesConstants;
import com.wtransnet.app.cleancode.app.modules.jokes.detail.JokesDetailActivity;
import com.wtransnet.app.cleancode.app.modules.jokes.list.JokesListActivity;
import com.wtransnet.app.cleancode.domain.entities.Name;

/**
 * Class used to navigate through the aplication
 */
public class Navigator {

    private Context context;

    public Navigator(Context context) {
        this.context = context;
    }

    public void navigateToJokesList(Name name) {

        if (context != null) {

            final Intent intent = new Intent(context, JokesListActivity.class);
            intent.putExtra(IntentValuesConstants.NAME, name);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(intent);

        }

    }

    public void navigateToJokeDetail(String id) {

        if (context != null) {

            final Intent intent = new Intent(context, JokesDetailActivity.class);
            intent.putExtra(IntentValuesConstants.ID, id);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(intent);

        }

    }
}
