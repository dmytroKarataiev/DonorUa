package ua.com.kathien.donorua.utils;

import android.location.Location;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import ua.com.kathien.donorua.models.Center;
import ua.com.kathien.donorua.models.City;

/**
 * Created by kathien on 8/11/16.
 */
public class CentersParser {

    private ArrayList<City> cities = new ArrayList<>();
    private static final String REQUEST = "centers";
    private static final String LOG_TAG = CentersParser.class.getSimpleName();

    public void parseCenters(ArrayList<Center> centers) {

        //Parsing list of cities into the ArrayList to fill Center objects with city names
        CityParser cityParser = new CityParser();
        cityParser.parseCities(cities);


        DonorJSONReader centersReader = new DonorJSONReader();
        String centersJSON = centersReader.readJSONfromURL(REQUEST);

        if(centersJSON == null) {
            Log.i(LOG_TAG, "JSON Centers is null");
            return;
        }

        try {
            JSONObject jsonRootObject = new JSONObject(centersJSON);
            JSONArray centersArray = jsonRootObject.getJSONArray("value");

            for(int i=0; i < centersArray.length(); i++){
                JSONObject center = centersArray.getJSONObject(i);

                String street = center.getString("street");
                int cityId = center.getInt("cityId");

                //Creating String with city name in ukrainian to put it into center object
                String cityName = null;
                for(City city : cities) {
                    if(city.getId() == cityId) {
                        cityName = city.getNameUA();

                        break;
                    }
                }

                int id = center.getInt("id");
                Center.Gender gender = Center.Gender.ANYONE;
                try {
                    gender = Center.Gender.values()[center.getInt("sex")];
                } catch (JSONException ex) {
                    Log.d(LOG_TAG, "Sex field is null in Center" + id);
                }


                boolean ignoreRegion = center.getBoolean("ignoreRegion");
                String description = center.getString("description");
                String about = center.getString("about");

                URL website = null;
                try {
                    website = new URL(center.getString("website"));
                } catch (MalformedURLException ex) {
                    Log.d(LOG_TAG, "MalformedURL in Center " + id);
                }

                String name = center.getString("name");
                String email = center.getString("email");
                String phoneNumber = center.getString("phone");
                Location location = new Location("");
                location.setLongitude(center.getDouble("longitude"));
                location.setLatitude(center.getDouble("latitude"));

                Center currentCenter = new Center(street, cityId, cityName, gender, ignoreRegion,
                        name, description, about, website, id, email, phoneNumber, location);
                centers.add(currentCenter);

            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Malformed JSON received. Aborting:  " + e.getMessage());
            e.printStackTrace();
        }

        return;
    }

}
