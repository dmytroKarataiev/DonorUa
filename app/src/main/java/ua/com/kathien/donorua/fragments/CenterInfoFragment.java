package ua.com.kathien.donorua.fragments;

import android.location.Location;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
    private TextView textInfo;
    private TextView textDescription;
    private TextView textEmail;
    private TextView textWebsite;
    private TextView textCenterName;

    private LinearLayout aboutLayout;
    private LinearLayout emailLayout;

    private View emailDivider;


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

    private void fillCenterItem(){

        String centerName = center.getName();
        if(centerName != null) textCenterName.setText(centerName);

        String cityName = center.getCityName();
        String street = center.getStreet();
        String fullAddress = "";

        if(cityName == null) {
            fullAddress = street;
        } else if (street == null){
            fullAddress = cityName;
        } else {
            fullAddress = cityName + ", " + street;
        }
        textAddress.setText(fullAddress);


        //FIXME: refactor null check
        
        String phoneNumber = center.getPhone();
        if(phoneNumber != null) textPhoneNumber.setText(phoneNumber);

        String about = center.getAbout();
        if (about != null){
            textInfo.setText(about);
        } else {

            aboutLayout.setVisibility(View.GONE);
        }

        String description = center.getDescription();
        if (description != null) textDescription.setText(description);

        String email = center.getEmail();
        if(email != null){
            textEmail.setText(email);
        } else {

            emailLayout.setVisibility(View.GONE);
            emailDivider.setVisibility(View.GONE);
        }

        URL website = center.getWebsite();
        if(website != null) textWebsite.setText(website.toString());

        Location loc = center.getLoc();
        Center.Gender gender = center.getGender();
    }

    private void initComponents(View v) {

        center = getActivity().getIntent().getParcelableExtra(CenterListFragment.CENTER_EXTRA_TAG);
        Log.i(LOG_TAG, center.toString());
        textCenterName = (TextView) v.findViewById(R.id.text_center_name);
        textAddress = (TextView) v.findViewById(R.id.text_address);
        textPhoneNumber = (TextView) v.findViewById(R.id.text_phone_number);
        textInfo = (TextView) v.findViewById(R.id.text_info);
        textDescription = (TextView) v.findViewById(R.id.text_description);
        textEmail = (TextView) v.findViewById(R.id.text_email);
        textWebsite = (TextView) v.findViewById(R.id.text_website);
        aboutLayout = (LinearLayout) v.findViewById(R.id.center_ll_information);
        emailLayout = (LinearLayout) v.findViewById(R.id.center_ll_email);
        emailDivider = v.findViewById(R.id.center_email_divider);
    }



}
