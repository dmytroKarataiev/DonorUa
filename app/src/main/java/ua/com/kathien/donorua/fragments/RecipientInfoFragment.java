package ua.com.kathien.donorua.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.net.URL;
import java.util.Date;

import ua.com.kathien.donorua.R;
import ua.com.kathien.donorua.models.Recipient;

/**
 * A placeholder fragment containing a simple view.
 */
public class RecipientInfoFragment extends Fragment {

    private Recipient recipient;
    private TextView recipientTest;

    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date dateOFBirth;
    private String center;
    private URL photoImage;
    private String contactPerson;
    private boolean isUrgent;
    private Date dateAdded;
    private String bloodType;
    private String disease;
    private String description;
    private int neededDonorsCount;
    private String city;
    private String centerAddress;
    private URL url;
    private static final String LOG_TAG = RecipientInfoFragment.class.getSimpleName();

    public RecipientInfoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipient_info, container, false);

        recipient = getActivity().getIntent().getParcelableExtra(RecipientListFragment.RECIPIENT_EXTRA_TAG);
        if (recipient != null) {
            recipientTest = (TextView) view.findViewById(R.id.text_recipient_name);
            recipientTest.setText(recipient.toString());
        }

        return view;
    }

    public void fillRecipientItem() {
        email = recipient.getEmail();
        phone = recipient.getPhone();
        firstName = recipient.getFirstName();
        lastName = recipient.getLastName();
        middleName = recipient.getMiddleName();
        dateOFBirth = recipient.getDateOFBirth();
        center = recipient.getCenter();
        photoImage = recipient.getPhotoImage();
        contactPerson = recipient.getContactPerson();
        isUrgent = recipient.isUrgent();
        dateAdded = recipient.getDateAdded();
        bloodType = recipient.getBloodType();
        disease = recipient.getDisease();
        description = recipient.getDescription();
        neededDonorsCount = recipient.getNeededDonorsCount();
        city = recipient.getCity();
        centerAddress = recipient.getCenterAddress();
        url = recipient.getUrl();
    }



}
