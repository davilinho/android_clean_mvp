package com.wtransnet.app.cleancode.app.core.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import com.wtransnet.app.cleancode.app.core.application.JokesApplication;
import com.wtransnet.app.cleancode.presentation.core.view.BaseView;

/**
 * Component created on 01/06/2015.
 *
 * @author dmartin
 */
public class BaseFragment<V extends BaseView> extends Fragment {

    private V view;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        injectDependencies();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        view = (V) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        view = null;
    }

    protected V getFragmentView() {
        return view;
    }

    private void injectDependencies() {
        JokesApplication.get(getActivity()).inject(this);
    }

}
