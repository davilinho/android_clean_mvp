package com.wtransnet.app.cleancode.app.common.nav;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.wtransnet.app.cleancode.app.common.constants.IntentValuesConstants;

import java.io.Serializable;

/**
 * Class used to navigate through the aplication
 */
public class Navigator<T extends Serializable> {

    private Context context;

    public Navigator(Context context) {
        this.context = context;
    }

    public void navigateToActivity(Activity activity, T data) {

        if (context != null) {

            final Intent intent = new Intent(context, activity.getClass());
            intent.putExtra(IntentValuesConstants.ACTIVITY_DATA, data);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(intent);

        }

    }

}
