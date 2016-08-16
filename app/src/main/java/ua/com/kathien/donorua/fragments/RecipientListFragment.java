package ua.com.kathien.donorua.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ua.com.kathien.donorua.R;
import ua.com.kathien.donorua.models.Recipient;
import ua.com.kathien.donorua.views.adapters.RecipientsAdapter;

public class RecipientListFragment extends Fragment {

    private static final String LOG_TAG = RecipientListFragment.class.getSimpleName();
    private ArrayList<Recipient> recipients;
    private RecyclerView recipientsRecyclerView;
    private RecyclerView.Adapter recipientsAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    public RecipientListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipient_list, container, false);

        recipients = getActivity().getIntent().getParcelableArrayListExtra("MyRecipients");
        recipientsRecyclerView = (RecyclerView) view.findViewById(R.id.recipients_recycler_view);

        if(recipientsRecyclerView == null) {
            Log.v(LOG_TAG, "recipientsRecyclerView is null");
            return view;
        }

        recipientsRecyclerView.setHasFixedSize(true);

        recipientsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recipientsRecyclerView.setAdapter(new RecipientsAdapter(recipients));

        return view;
    }
}
