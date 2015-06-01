package com.wtransnet.app.cleancode.app.modules.jokes.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.wtransnet.app.cleancode.R;
import com.wtransnet.app.cleancode.app.core.fragment.BaseFragment;
import com.wtransnet.app.cleancode.presentation.core.view.BaseView;
import com.wtransnet.app.cleancode.presentation.modules.jokes.detail.JokeDetailView;

import butterknife.ButterKnife;

import static com.wtransnet.app.cleancode.app.common.constants.IntentValuesConstants.FRAGMENT_DATA;

/**
 * Component created on 01/06/2015.
 *
 * @author dmartin
 */
public class JokeDetailFragment extends BaseFragment<BaseView> {

    public JokeDetailFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detail_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView txtDetailFragment = ButterKnife.findById(view, R.id.txtDetailFragment);
        txtDetailFragment.setText(getArguments().getString(FRAGMENT_DATA));

        Button btnCloseFragment = ButterKnife.findById(view, R.id.btnCloseFragment);
        btnCloseFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((JokeDetailView) getFragmentView()).closeJokeDetailFragment();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
