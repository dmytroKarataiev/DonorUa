package ua.com.kathien.donorua.fragments;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

import ua.com.kathien.donorua.R;
import ua.com.kathien.donorua.models.Center;

/**
 * A placeholder fragment containing a simple view.
 */
public class CenterInfoFragment extends Fragment {

    private Center center;
    private TextView centerTestText;


    private String street;
    private Center.Gender gender;
    private String name;
    private String description;
    private String about;
    private URL website;
    private String email;
    private String phone;
    private Location loc;
    private String cityName;

    private static final String LOG_TAG = CenterInfoFragment.class.getSimpleName();


    public CenterInfoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_center_info, container, false);

        fillCenterItem();
        centerTestText = (TextView) view.findViewById(R.id.center_test_info);

        if(center != null) {
            centerTestText.setText(center.toString());
        }

        return view;
    }

    public void fillCenterItem(){
        center = getActivity().getIntent().getParcelableExtra(CenterListFragment.CENTER_EXTRA_TAG);

        street = center.getStreet();
        gender = center.getGender();
        name = center.getName();
        description = center.getDescription();
        about = center.getAbout();
        website = center.getWebsite();
        email = center.getEmail();
        phone = center.getPhone();
        loc = center.getLoc();
        cityName = center.getCityName();
    }


}
