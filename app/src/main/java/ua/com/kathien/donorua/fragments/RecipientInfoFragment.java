package ua.com.kathien.donorua.fragments;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
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
import java.util.Date;

import ua.com.kathien.donorua.R;
import ua.com.kathien.donorua.models.Recipient;

/**
 * A placeholder fragment containing a simple view.
 */
public class RecipientInfoFragment extends Fragment {

    private Recipient recipient;
    private TextView recipientTest;
    private ImageView img;
    private Bitmap bitmap;
    private ProgressDialog pDialog;
    private CoordinatorLayout coordinatorLayout;

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
    // private static final String LOG_TAG = RecipientInfoFragment.class.getSimpleName();

    public RecipientInfoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipient_info, container, false);

        img = (ImageView) view.findViewById(R.id.recipient_photo);
        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.recipient_list_coordinator_layout);

        recipient = getActivity().getIntent().getParcelableExtra(RecipientListFragment.RECIPIENT_EXTRA_TAG);
        if (recipient != null) {
            recipientTest = (TextView) view.findViewById(R.id.recipients_test_info);
            recipientTest.setText(recipient.toString());
        }

        LoadImage loadImage = new LoadImage();
        loadImage.execute();

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

    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Loading Image ....");
            pDialog.show();

        }

        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL("https://pp.vk.me/c626319/v626319162/2b47f/VMhy5BwvwtA.jpg").getContent());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap image) {

            if (image != null) {
                img.setImageBitmap(image);
                pDialog.dismiss();

            } else {

                pDialog.dismiss();
                /*Snackbar snackbar = Snackbar.
                        make(coordinatorLayout, "Image Does Not exist or Network Error", Snackbar.LENGTH_SHORT);
                snackbar.show();*/
                Log.i("Recipient Info", "Image Does Not exist or Network Error");
            }
        }
    }

}
