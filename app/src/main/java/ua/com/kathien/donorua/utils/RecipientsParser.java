package ua.com.kathien.donorua.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import ua.com.kathien.donorua.models.Recipient;

/**
 * This class is used to parse JSON odata and fill Recipient objects with corresponding values.
 */

public class RecipientsParser {

    private static final String REQUEST = "recipients";
    private static final String LOG_TAG = RecipientsParser.class.getSimpleName();

    public void parseRecipients(ArrayList<Recipient> recipients) {
        DonorJSONReader recipientsReader = new DonorJSONReader();
        String recipientsJSON = recipientsReader.readJSONfromURL(REQUEST);

        //FIXME: add proper null check of all jsons fetched
        if (recipientsJSON == null || recipientsJSON == "") {
            Log.e(LOG_TAG, "recipientJSON is null");
            return;
        }

        try {
            JSONObject jsonRootObject = new JSONObject(recipientsJSON);
            JSONArray recipientsArray = jsonRootObject.getJSONArray("value");

            int recipientsArrayLength = recipientsArray.length();
            for (int i = 0; i < recipientsArrayLength; i++) {
                JSONObject recipient = recipientsArray.getJSONObject(i);

                int id = recipient.getInt("id");
                String email = JSONVerifier.checkStringFromJson(recipient.getString("email"));
                String phone = JSONVerifier.checkStringFromJson(recipient.getString("phone"));
                String firstName = JSONVerifier.checkStringFromJson(recipient.getString("firstName").trim());
                String middleName = JSONVerifier.checkStringFromJson(recipient.getString("middleName").trim());
                String lastName = JSONVerifier.checkStringFromJson(recipient.getString("lastName").trim());
                Date dateOfBirth = null;

                try {
                    dateOfBirth = DataUtils.stringToDate(JSONVerifier.checkStringFromJson(recipient.getString("dateOfBirth")));
                } catch (ParseException ex) {
                    Log.d(LOG_TAG, "Parse exception in dateOfBirth in recipient " + id);
                }

                String center = JSONVerifier.checkStringFromJson(recipient.getString("center"));

                URL photo = null;
                try {
                    photo = new URL(JSONVerifier.checkStringFromJson(recipient.getString("photoImage")));
                } catch (MalformedURLException ex) {
                    Log.d(LOG_TAG, "Malformed photo URL in recipient " + id);
                }
                boolean isUrgent = recipient.getBoolean("isUrgent");

                Date dateAdded = null;
                try {
                    dateAdded = DataUtils.stringToDate(JSONVerifier.checkStringFromJson(recipient.getString("dateAdded")));
                } catch (ParseException ex) {
                    Log.d(LOG_TAG, "Parse exception in dateAdded in recipient " + id);
                }
                String contactPerson = JSONVerifier.checkStringFromJson(recipient.getString("contactPerson"));
                String bloodType = JSONVerifier.checkStringFromJson(recipient.getString("bloodGroup"));
                String disease = JSONVerifier.checkStringFromJson(recipient.getString("disease"));
                String description = JSONVerifier.checkStringFromJson(recipient.getString("description"));
                int neededDonorsCount = recipient.getInt("neededDonorsCount");
                String city = JSONVerifier.checkStringFromJson(recipient.getString("city"));
                int cityId = recipient.getInt("cityId");
                int centerId = recipient.getInt("centerId");
                String centerAddress = JSONVerifier.checkStringFromJson(recipient.getString("centerAddress"));

                URL recipientURL = null;
                try {
                    recipientURL = new URL(JSONVerifier.checkStringFromJson(recipient.getString("url")));
                } catch (MalformedURLException ex) {
                    Log.i(LOG_TAG, "Malformed recipient URL in recipient " + id);
                }


                Recipient newRecipient = new Recipient(id, email, phone, firstName, lastName,
                        middleName, dateOfBirth, center, photo, contactPerson, isUrgent, dateAdded,
                        bloodType, disease, description, neededDonorsCount, city, cityId, centerId,
                        centerAddress, recipientURL);

                recipients.add(newRecipient);
            }


        } catch (JSONException e) {
            Log.e(LOG_TAG, "Malformed JSON: " + e.getMessage());
            e.printStackTrace();
        }

    }


}
