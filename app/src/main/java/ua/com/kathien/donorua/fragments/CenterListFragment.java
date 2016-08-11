package ua.com.kathien.donorua.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ua.com.kathien.donorua.R;
import ua.com.kathien.donorua.models.Center;

/**
 * A placeholder fragment containing a simple view.
 */
public class CenterListFragment extends Fragment {

    private ArrayList<Center> centers;
    private TextView centersText;

    public CenterListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_center_list, container, false);

        centersText = (TextView) view.findViewById(R.id.centers_text);
        centers = getActivity().getIntent().getParcelableArrayListExtra("OurCenters");
        centersText.setText(centers.toString());

        return view;
    }
}
