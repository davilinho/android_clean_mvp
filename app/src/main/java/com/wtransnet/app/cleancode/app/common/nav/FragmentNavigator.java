package com.wtransnet.app.cleancode.app.common.nav;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.wtransnet.app.cleancode.R;
import com.wtransnet.app.cleancode.app.core.fragment.BaseFragment;

import java.io.Serializable;

import static com.wtransnet.app.cleancode.app.common.constants.IntentValuesConstants.FRAGMENT_DATA;

/**
 * Component created on 01/06/2015.
 *
 * @author dmartin
 */
public class FragmentNavigator<T extends Serializable> {

    public FragmentNavigator() { }

    public void navigateToFragment(Activity activity, BaseFragment fragment, T data) {

        if (activity != null && fragment != null) {

            if (data != null) {
                prepareData(fragment, data);
            }

            navigate(activity, fragment);

        }

    }

    private void prepareData(BaseFragment fragment, T data) {

        Bundle bundle = new Bundle();
        bundle.putSerializable(FRAGMENT_DATA, data);
        fragment.setArguments(bundle);

    }

    private void navigate(Activity activity, BaseFragment fragment) {

        FragmentTransaction fragmentTransaction = activity.getFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.replace(R.id.contentFrame, fragment);
        fragmentTransaction.addToBackStack(fragment.getTag());
        fragmentTransaction.commit();

    }

}
