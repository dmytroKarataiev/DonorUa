package ua.com.kathien.donorua.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ua.com.kathien.donorua.R;
import ua.com.kathien.donorua.models.Center;

/**
 * A placeholder fragment containing a simple view.
 */
public class CenterInfoFragment extends Fragment {

    private Center center;
    private TextView centerTestText;


    public CenterInfoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_center_info, container, false);

        center = getActivity().getIntent().getParcelableExtra(CenterListFragment.CENTER_EXTRA_TAG);
        centerTestText = (TextView) view.findViewById(R.id.center_test_info);

        if(center != null) {
            centerTestText.setText(center.toString());
        }

        return view;
    }
}
