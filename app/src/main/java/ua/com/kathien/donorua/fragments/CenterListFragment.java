package ua.com.kathien.donorua.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ua.com.kathien.donorua.R;
import ua.com.kathien.donorua.models.Center;
import ua.com.kathien.donorua.utils.DividerItemDecoration;
import ua.com.kathien.donorua.views.adapters.CentersAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class CenterListFragment extends Fragment {

    private ArrayList<Center> centers;
    private RecyclerView centersRecyclerView;
    private static final String LOG_TAG = CenterListFragment.class.getSimpleName();

    public CenterListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_center_list, container, false);


        centers = getActivity().getIntent().getParcelableArrayListExtra("OurCenters");

        centersRecyclerView = (RecyclerView) view.findViewById(R.id.centers_recycler_view);

        if(centersRecyclerView == null) {
            Log.v(LOG_TAG, "centersRecyclerView is null");
            return view;
        }

        centersRecyclerView.setHasFixedSize(true);


        centersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        centersRecyclerView.addItemDecoration(itemDecoration);
        centersRecyclerView.setAdapter(new CentersAdapter(centers));


        return view;
    }
}
