package ua.com.kathien.donorua.fragments;

import android.content.Intent;
import android.os.AsyncTask;
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
import ua.com.kathien.donorua.activities.RecipientListActivity;
import ua.com.kathien.donorua.models.Recipient;
import ua.com.kathien.donorua.utils.DividerItemDecoration;
import ua.com.kathien.donorua.utils.ItemClickSupport;
import ua.com.kathien.donorua.utils.OnlineHelper;
import ua.com.kathien.donorua.utils.RecipientsParser;
import ua.com.kathien.donorua.views.adapters.RecipientsAdapter;

public class RecipientListFragment extends Fragment {

    private ArrayList<Recipient> recipients;
    private RecyclerView recipientsRecyclerView;
    private boolean recipientsDownloaded;
    private RecipientsAdapter recipientsAdapter;

    private static final String LOG_TAG = RecipientListFragment.class.getSimpleName();
    public static final String RECIPIENT_EXTRA_TAG = "recipient";


    public RecipientListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipient_list, container, false);

        initComponents();

        recipientsRecyclerView = (RecyclerView) view.findViewById(R.id.recipients_recycler_view);


        if(recipientsRecyclerView == null) {
            Log.v(LOG_TAG, "recipientsRecyclerView is null");
            return view;
        }


        recipientsRecyclerView.setHasFixedSize(true);

        recipientsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        recipientsRecyclerView.addItemDecoration(itemDecoration);

        RecipientsShower recipientsShower = new RecipientsShower();
        recipientsShower.execute();

        ItemClickSupport.addTo(recipientsRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener(){
           @Override
           public void onItemClicked(RecyclerView recyclerView, int position, View v) {
               Recipient clickedRecipient = recipients.get(position);

               Intent recipientInfoIntent = new Intent(getActivity(), RecipientInfoActivity.class);
               recipientInfoIntent.putExtra(RECIPIENT_EXTRA_TAG, clickedRecipient);
               startActivity(recipientInfoIntent);
           }
        });

        return view;
    }

    private class RecipientsShower extends AsyncTask<Void, Void, ArrayList<Recipient>> {

        public RecipientsShower() {
            super();
        }

        @Override
        protected ArrayList<Recipient> doInBackground(Void... params) {
            recipients = parseAllRecipients();
            return recipients;
        }

        @Override
        protected void onPostExecute(ArrayList<Recipient> result) {
            if (result == null) {
                Log.i(LOG_TAG, "recipient list is null");
            }

            recipientsAdapter = new RecipientsAdapter(result);
            recipientsRecyclerView.setAdapter(recipientsAdapter);
        }

    }

    private ArrayList<Recipient> parseAllRecipients() {
        if (recipientsDownloaded) {
            return recipients;
        }
        if (!OnlineHelper.isOnline(getActivity())) {
            return null;
        }
        RecipientsParser recipientsParser = new RecipientsParser();
        recipientsParser.parseRecipients(recipients);
        recipientsDownloaded = true;

        return recipients;
    }

    public void initComponents() {
        recipients = new ArrayList<>();
        recipientsDownloaded = false;

    }

}
