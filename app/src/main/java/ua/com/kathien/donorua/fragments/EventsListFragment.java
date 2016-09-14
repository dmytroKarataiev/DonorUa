package ua.com.kathien.donorua.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ua.com.kathien.donorua.R;
import ua.com.kathien.donorua.models.DonorEvent;

public class EventsListFragment extends Fragment {

    private static final String LOG_TAG = EventsListFragment.class.getSimpleName();
    private ArrayList<DonorEvent> events;

    public EventsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events_list, container, false);

        events = getActivity().getIntent().getParcelableArrayListExtra("events");

        return view;
    }

}
