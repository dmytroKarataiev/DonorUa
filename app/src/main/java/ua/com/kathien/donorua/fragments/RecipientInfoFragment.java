package ua.com.kathien.donorua.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.net.URL;
import java.util.Date;

import ua.com.kathien.donorua.R;
import ua.com.kathien.donorua.models.Recipient;
import ua.com.kathien.donorua.utils.DataUtils;


public class RecipientInfoFragment extends Fragment {

    private Recipient recipient;
    private TextView recipientTest;

    private TextView textRecipientName;
    private TextView textBloodType;
    private TextView textCenter;
    private TextView textContactPerson;
    private TextView textEmail;
    private TextView textPhone;
    private TextView textDisease;
    private TextView textDescription;
    private TextView textDateAdded;

    private LinearLayout bloodTypeLayout;
    private LinearLayout centerLayout;
    private LinearLayout contactPersonLayout;
    private LinearLayout emailLayout;
    private LinearLayout phoneLayout;
    private LinearLayout diseaseLayout;
    private LinearLayout descriptionLayout;
    private LinearLayout dateAddedLayout;

    private View bloodTypeDivider;
    private View centerDivider;
    private View contactPersonDivider;
    private View emailDivider;
    private View phoneDivider;
    private View diseaseDivider;
    private View descriptionDivider;
    private View dateAddedDivider;


    private static final String LOG_TAG = RecipientInfoFragment.class.getSimpleName();

    public RecipientInfoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipient_info, container, false);

        initComponents(view);
        fillRecipientItem();

        return view;
    }

    private void fillRecipientItem() {

        String lastName = recipient.getLastName();
        String firstName = recipient.getFirstName();
        String fullName = lastName + " " + firstName;
        String middleName = recipient.getMiddleName();
        textRecipientName.setText(fullName);

        String email = recipient.getEmail();
        if(email != null) {
            textEmail.setText(email);
        } else {
            emailLayout.setVisibility(View.GONE);
            emailDivider.setVisibility(View.GONE);
        }

        String phone = recipient.getPhone();
        if(phone != null) {
            textPhone.setText(phone);
        } else {
            phoneLayout.setVisibility(View.GONE);
            phoneDivider.setVisibility(View.GONE);
        }

        Date dateOFBirth = recipient.getDateOFBirth();
        String center = recipient.getCenter();
        URL photoImage = recipient.getPhotoImage();

        String contactPerson = recipient.getContactPerson();
        if(contactPerson != null) {
            textContactPerson.setText(contactPerson);
        } else {
            contactPersonLayout.setVisibility(View.GONE);
            contactPersonDivider.setVisibility(View.GONE);
        }

        boolean isUrgent = recipient.isUrgent();

        Date dateAdded = recipient.getDateAdded();
        if(dateAdded != null) {
            textDateAdded.setText(DataUtils.dateToPrettyString(dateAdded));
        } else {
            dateAddedLayout.setVisibility(View.GONE);
            dateAddedDivider.setVisibility(View.GONE);
        }

        String bloodType = recipient.getBloodType();
        if(bloodType != null) {
            textBloodType.setText(bloodType);
        } else {
            bloodTypeLayout.setVisibility(View.GONE);
            bloodTypeDivider.setVisibility(View.GONE);
        }

        String disease = recipient.getDisease();
        if(disease != null) {
            textDisease.setText(disease);
        } else {
            diseaseLayout.setVisibility(View.GONE);
            diseaseDivider.setVisibility(View.GONE);
        }

        String description = recipient.getDescription();
        if(description != null) {
            textDescription.setText(description);
        } else {
            descriptionLayout.setVisibility(View.GONE);
            descriptionDivider.setVisibility(View.GONE);
        }
        int neededDonorsCount = recipient.getNeededDonorsCount();

        String city = recipient.getCity();
        String centerAddress = recipient.getCenterAddress();
        String centerInformation = city + ", " + center + '\n' + centerAddress;
        textCenter.setText(centerInformation);

        URL url = recipient.getUrl();
    }

    private void initComponents(View v) {

        recipient = getActivity().getIntent().getParcelableExtra(RecipientListFragment.RECIPIENT_EXTRA_TAG);

        textRecipientName = (TextView) v.findViewById(R.id.text_recipient_name);
        textBloodType = (TextView) v.findViewById(R.id.text_recipient_info_blood);
        textCenter = (TextView) v.findViewById(R.id.text_recipient_info_center);
        textContactPerson = (TextView) v.findViewById(R.id.text_recipient_info_contact_person);
        textEmail = (TextView) v.findViewById(R.id.text_recipient_info_contact_email);
        textPhone = (TextView) v.findViewById(R.id.text_recipient_info_contact_phone);
        textDisease = (TextView) v.findViewById(R.id.text_recipient_info_disease);
        textDescription = (TextView) v.findViewById(R.id.text_recipient_description);
        textDateAdded = (TextView) v.findViewById(R.id.text_recipient_date_added);


        bloodTypeLayout = (LinearLayout) v.findViewById(R.id.ll_recipient_blood_type);
        centerLayout = (LinearLayout) v.findViewById(R.id.ll_recipient_blood_center);
        contactPersonLayout = (LinearLayout) v.findViewById(R.id.ll_recipient_contact_person);
        emailLayout = (LinearLayout) v.findViewById(R.id.ll_recipient_contact_email);
        phoneLayout = (LinearLayout) v.findViewById(R.id.ll_recipient_contact_phone);
        diseaseLayout = (LinearLayout) v.findViewById(R.id.ll_recipient_disease);
        descriptionLayout = (LinearLayout) v.findViewById(R.id.ll_recipient_description);
        dateAddedLayout = (LinearLayout) v.findViewById(R.id.ll_recipient_date_added);

        bloodTypeDivider = v.findViewById(R.id.recipient_blood_type_divider);
        centerDivider = v.findViewById(R.id.recipient_blood_center_divider);
        contactPersonDivider = v.findViewById(R.id.recipient_contact_person_divider);
        emailDivider = v.findViewById(R.id.recipient_contact_email_divider);
        phoneDivider = v.findViewById(R.id.recipient_contact_phone_divider);
        diseaseDivider = v.findViewById(R.id.recipient_disease_divider);
        descriptionDivider = v.findViewById(R.id.recipient_description_divider);
        dateAddedDivider = v.findViewById(R.id.recipient_date_added_divider);
    }


}
