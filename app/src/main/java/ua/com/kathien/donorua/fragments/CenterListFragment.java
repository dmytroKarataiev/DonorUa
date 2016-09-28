package ua.com.kathien.donorua.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
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
import ua.com.kathien.donorua.activities.CenterListActivity;
import ua.com.kathien.donorua.models.Center;
import ua.com.kathien.donorua.models.City;
import ua.com.kathien.donorua.utils.CentersParser;
import ua.com.kathien.donorua.utils.CityParser;
import ua.com.kathien.donorua.utils.DividerItemDecoration;
import ua.com.kathien.donorua.utils.ItemClickSupport;
import ua.com.kathien.donorua.utils.OnlineHelper;
import ua.com.kathien.donorua.views.adapters.CentersAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class CenterListFragment extends Fragment {

    private ArrayList<Center> centers;
    private boolean centersDownloaded;
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

        initComponents();

        centersRecyclerView = (RecyclerView) view.findViewById(R.id.centers_recycler_view);

        if(centersRecyclerView == null) {
            Log.v(LOG_TAG, "centersRecyclerView is null");
            return view;
        }


        centersRecyclerView.setHasFixedSize(true);
        centersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        centersRecyclerView.addItemDecoration(itemDecoration);

        CentersShower centersShower = new CentersShower();
        centersShower.execute();

        ItemClickSupport.addTo(centersRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener(){
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Center clickedCenter = centers.get(position);
                Intent centerInfoIntent = new Intent(getActivity(), CenterInfoActivity.class);
                centerInfoIntent.putExtra(CENTER_EXTRA_TAG, clickedCenter);
                startActivity(centerInfoIntent);
            }
        });
        return view;
    }

    private class CentersShower extends AsyncTask<Void, Void, ArrayList<Center>> {

        public CentersShower() {
            super();
        }

        @Override
        protected ArrayList<Center> doInBackground(Void... params) {
            if (centersDownloaded) {
                return centers;
            } else if (!OnlineHelper.isOnline(getActivity())) {
                return null;
            } else {
                CentersParser centersParser = new CentersParser();
                centersParser.parseCenters(centers);
                centersDownloaded = true;
            }
            return centers;
        }


        @Override
        protected void onPostExecute(ArrayList<Center> result) {

            if (result == null) {
                Log.i(LOG_TAG, "center list is null");
                return;
            }

            centersAdapter = new CentersAdapter(result);
            centersRecyclerView.setAdapter(centersAdapter);
        }

    }


    private void initComponents() {

        centers = new ArrayList<Center>();
        centersDownloaded = false;


    }

}
