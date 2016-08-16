package ua.com.kathien.donorua.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ua.com.kathien.donorua.R;
import ua.com.kathien.donorua.models.Center;

/**
 * Created by kathien on 8/13/16.
 */
public class CentersAdapter extends RecyclerView.Adapter<CentersAdapter.ViewHolder> {
    private ArrayList<Center> centersList;


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView centerNameTextView;
        public TextView centerAddressTextView;

        public ViewHolder(View v) {
            super(v);

            centerNameTextView = (TextView) v.findViewById(R.id.text_center_name);
            centerAddressTextView = (TextView) v.findViewById(R.id.text_center_address);
        }

    }

    public CentersAdapter(ArrayList<Center> centers) {
        centersList = centers;
    }

    @Override
    public CentersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.center_list_row, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(CentersAdapter.ViewHolder holder, int position) {

        Center center = centersList.get(position);

        holder.centerNameTextView.setText(center.getName());
        holder.centerAddressTextView.setText(center.getCityName() + ", " + center.getStreet());

    }

    @Override
    public int getItemCount() {
        return centersList.size();
    }
}
