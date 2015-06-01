package com.wtransnet.app.cleancode.app.core.error;

import android.content.Context;
import android.widget.Toast;

import com.wtransnet.app.cleancode.presentation.core.error.ErrorHandler;

/**
 * Component created on 29/05/2015.
 *
 * @author dmartin
 */
public class CommonErrorHandler {

    public void showCommonErrors(Context context) {
        Toast.makeText(context, "Generic error!!!", Toast.LENGTH_SHORT).show();
    }

}
