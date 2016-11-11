package ua.com.kathien.donorua.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ua.com.kathien.donorua.models.City;

public class CityParser {

    private static final String REQUEST = "cities";
    private static final String LOG_TAG = CityParser.class.getSimpleName();

    public ArrayList<City> parseCities(){
        ArrayList<City> cities = new ArrayList<>();
        DonorJSONReader cityReader = new DonorJSONReader();
        String citiesJSON = cityReader.readJSONfromURL(REQUEST);

        //TODO: Ask Paul -- should I ude json verifier here?
        //FIXME: refactor null check
        if(citiesJSON == null || citiesJSON == "") {
            Log.e("CityParser", "citiesJSON is null");
            return null;
        }

        try {
            JSONObject jsonRootObject = new JSONObject(citiesJSON);
            JSONArray citiesArray = jsonRootObject.getJSONArray("value");

            for (int i = 0; i < citiesArray.length(); i++) {
                JSONObject city = citiesArray.getJSONObject(i);

                int id = city.getInt("id");
                String nameUa = JSONVerifier.checkStringFromJson(city.getString("name"));
                String nameRu = JSONVerifier.checkStringFromJson(city.getString("nameRu"));
                String nameEn = JSONVerifier.checkStringFromJson(city.getString("nameEn"));
                int regionId = city.getInt("regionId");

                City currentCity = new City(id, nameUa, nameEn, nameRu, regionId);
                cities.add(currentCity);

            }



        } catch (JSONException e) {
            Log.e("CityParser", "Malformed JSON received. Aborting:  " + e.getMessage());
            e.printStackTrace();
        }


        return cities;
    }
}
