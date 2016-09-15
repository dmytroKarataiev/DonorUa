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

import java.util.ArrayList;

import ua.com.kathien.donorua.R;
import ua.com.kathien.donorua.activities.RecipientInfoActivity;
import ua.com.kathien.donorua.models.Recipient;
import ua.com.kathien.donorua.utils.DividerItemDecoration;
import ua.com.kathien.donorua.utils.ItemClickSupport;
import ua.com.kathien.donorua.views.adapters.RecipientsAdapter;

public class RecipientListFragment extends Fragment {

    private static final String LOG_TAG = RecipientListFragment.class.getSimpleName();
    private ArrayList<Recipient> recipients;
    private RecyclerView recipientsRecyclerView;


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
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        recipientsRecyclerView.addItemDecoration(itemDecoration);

        recipientsRecyclerView.setAdapter(new RecipientsAdapter(recipients));

        ItemClickSupport.addTo(recipientsRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener(){
           @Override
           public void onItemClicked(RecyclerView recyclerView, int position, View v) {
               Recipient clickedRecipient = recipients.get(position);

               Intent recipientInfoIntent = new Intent(getActivity(), RecipientInfoActivity.class);
               recipientInfoIntent.putExtra("recipient", clickedRecipient);
               startActivity(recipientInfoIntent);
           }
        });

        return view;
    }
}
