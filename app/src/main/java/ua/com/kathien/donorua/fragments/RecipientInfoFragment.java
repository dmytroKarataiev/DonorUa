package ua.com.kathien.donorua.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ua.com.kathien.donorua.R;
import ua.com.kathien.donorua.models.Recipient;

/**
 * A placeholder fragment containing a simple view.
 */
public class RecipientInfoFragment extends Fragment {

    private Recipient recipient;
    private TextView recipientTest;
    // private static final String LOG_TAG = RecipientInfoFragment.class.getSimpleName();

    public RecipientInfoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipient_info, container, false);

        recipient = getActivity().getIntent().getParcelableExtra(RecipientListFragment.RECIPIENT_EXTRA_TAG);
        if(recipient != null) {
            recipientTest = (TextView) view.findViewById(R.id.recipients_test_info);
            recipientTest.setText(recipient.toString());
        }



        return view;
    }
}
