package ua.com.kathien.donorua.fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ua.com.kathien.donorua.R;
import ua.com.kathien.donorua.activities.CenterInfoActivity;
import ua.com.kathien.donorua.models.Center;
import ua.com.kathien.donorua.utils.DividerItemDecoration;
import ua.com.kathien.donorua.utils.ItemClickSupport;
import ua.com.kathien.donorua.views.adapters.CentersAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class CenterListFragment extends Fragment {

    private ArrayList<Center> centers;
    private RecyclerView centersRecyclerView;
    private CentersAdapter centersAdapter;

    private static final String LOG_TAG = CenterListFragment.class.getSimpleName();
    public static final String CENTER_EXTRA_TAG = "center";

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
        centersAdapter = new CentersAdapter(centers);
        centersRecyclerView.setAdapter(centersAdapter);

        ItemClickSupport.addTo(centersRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener(){
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Center clickedCenter = centers.get(position);
                //Toast.makeText(getActivity(), "Clicked on center " + clickedCenter.getName(), Toast.LENGTH_SHORT).show();

                Intent centerInfoIntent = new Intent(getActivity(), CenterInfoActivity.class);
                centerInfoIntent.putExtra(CENTER_EXTRA_TAG, clickedCenter);
                startActivity(centerInfoIntent);
            }
        });
        return view;
    }
}
