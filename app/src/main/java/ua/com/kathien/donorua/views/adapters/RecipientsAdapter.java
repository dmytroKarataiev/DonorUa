package ua.com.kathien.donorua.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ua.com.kathien.donorua.R;
import ua.com.kathien.donorua.models.Recipient;

/**
 * Created by kathien on 8/17/16.
 */
public class RecipientsAdapter extends RecyclerView.Adapter<RecipientsAdapter.ViewHolder> {

    private ArrayList<Recipient> recipientsList;


    public class ViewHolder extends RecyclerView.ViewHolder {


        private ImageView recipientIconImageView;
        private TextView recipientNameTextView;
        private TextView bloodTypeTextView;
        private TextView cityNameTextView;
        private TextView donorCountTextView;


        public ViewHolder(View v) {
            super(v);

            recipientIconImageView = (ImageView) v.findViewById(R.id.recipient_icon);
            recipientNameTextView = (TextView) v.findViewById(R.id.recipient_name);
            bloodTypeTextView = (TextView) v.findViewById(R.id.blood_type);
            cityNameTextView = (TextView) v.findViewById(R.id.city_name);
            donorCountTextView = (TextView) v.findViewById(R.id.donor_count);

        }

    }

    public RecipientsAdapter(ArrayList<Recipient> recipients) {
        recipientsList = recipients;
    }

    @Override
    public RecipientsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipient_list_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecipientsAdapter.ViewHolder holder, int position) {

        Recipient recipient = recipientsList.get(position);

        //TODO: add full name to recipients
        holder.recipientNameTextView.setText(recipient.getFullName());
        holder.donorCountTextView.setText(String.valueOf(recipient.getNeededDonorsCount()));
        holder.cityNameTextView.setText(recipient.getCity());
        holder.bloodTypeTextView.setText(recipient.getBloodType());

    }

    @Override
    public int getItemCount() {
        return recipientsList.size();
    }

}
