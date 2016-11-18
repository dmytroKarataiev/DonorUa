package ua.com.kathien.donorua.fragments;

import android.location.Location;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.net.URL;

import ua.com.kathien.donorua.R;
import ua.com.kathien.donorua.models.Center;

public class CenterInfoFragment extends Fragment {

    private Center center;
    private TextView textAddress;
    private TextView textPhoneNumber;
    private TextView textAbout;
    private TextView textDescription;
    private TextView textEmail;
    private TextView textWebsite;
    private TextView textCenterName;

    private LinearLayout phoneLayout;
    private LinearLayout aboutLayout;
    private LinearLayout descriptionLayout;
    private LinearLayout emailLayout;
    private LinearLayout websiteLayout;
    private LinearLayout genderLayout;


    private View phoneDivider;
    private View aboutDivider;
    private View descriptionDivider;
    private View emailDivider;
    private View websiteDivider;


    private static final String LOG_TAG = CenterInfoFragment.class.getSimpleName();


    public CenterInfoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_center_info, container, false);

        initComponents(view);
        fillCenterItem();


        return view;
    }

    //FIXME: after layouts are gone a lot of extra free space appear
    private void fillCenterItem() {

        String centerName = center.getName();
        if (centerName != null) textCenterName.setText(centerName);

        String cityName = center.getCityName();
        String street = center.getStreet();
        String fullAddress = "";
        if (cityName == null) {
            fullAddress = street;
        } else if (street == null) {
            fullAddress = cityName;
        } else {
            fullAddress = cityName + ", " + street;
        }
        textAddress.setText(fullAddress);

        String phoneNumber = center.getPhone();
        if (phoneNumber != null) {
            textPhoneNumber.setText(phoneNumber);
        } else {
            phoneLayout.setVisibility(View.GONE);
            phoneDivider.setVisibility(View.GONE);
        }

        String about = center.getAbout();
        if (about != null) {
            textAbout.setText(about);
        } else {
            aboutLayout.setVisibility(View.GONE);
            aboutDivider.setVisibility(View.GONE);
        }

        String description = center.getDescription();
        if (description != null) {
            textDescription.setText(description);
        } else {
            descriptionLayout.setVisibility(View.GONE);
            descriptionDivider.setVisibility(View.GONE);
        }

        String email = center.getEmail();
        if (email != null) {
            textEmail.setText(email);
        } else {
            emailLayout.setVisibility(View.GONE);
            emailDivider.setVisibility(View.GONE);
        }

        URL website = center.getWebsite();
        if (website != null) {
            textWebsite.setText(website.toString());
        } else {
            websiteLayout.setVisibility(View.GONE);
            websiteDivider.setVisibility(View.GONE);
        }


        Location loc = center.getLoc();

        Center.Gender gender = center.getGender();
        if (gender.equals(Center.Gender.MALE)) {
            genderLayout.setVisibility(View.VISIBLE);
        }
    }

    private void initComponents(View v) {

        center = getActivity().getIntent().getParcelableExtra(CenterListFragment.CENTER_EXTRA_TAG);
        Log.i(LOG_TAG, center.toString());

        textCenterName = (TextView) v.findViewById(R.id.text_center_name);
        textAddress = (TextView) v.findViewById(R.id.center_info_text_address);
        textPhoneNumber = (TextView) v.findViewById(R.id.center_info_text_phone);
        textAbout = (TextView) v.findViewById(R.id.center_info_text_about);
        textDescription = (TextView) v.findViewById(R.id.center_info_text_description);
        textEmail = (TextView) v.findViewById(R.id.center_info_text_email);
        textWebsite = (TextView) v.findViewById(R.id.center_info_text_website);

        phoneLayout = (LinearLayout) v.findViewById(R.id.center_ll_phone);
        aboutLayout = (LinearLayout) v.findViewById(R.id.center_ll_about);
        descriptionLayout = (LinearLayout) v.findViewById(R.id.center_ll_description);
        emailLayout = (LinearLayout) v.findViewById(R.id.center_ll_email);
        websiteLayout = (LinearLayout) v.findViewById(R.id.center_ll_website);
        genderLayout = (LinearLayout) v.findViewById(R.id.center_ll_gender);

        phoneDivider = v.findViewById(R.id.center_phone_divider);
        aboutDivider = v.findViewById(R.id.center_about_divider);
        descriptionDivider = v.findViewById(R.id.center_description_divider);
        emailDivider = v.findViewById(R.id.center_email_divider);
        websiteDivider = v.findViewById(R.id.center_website_divider);
    }


}
