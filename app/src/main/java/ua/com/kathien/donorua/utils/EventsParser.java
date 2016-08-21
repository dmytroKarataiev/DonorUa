package ua.com.kathien.donorua.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import ua.com.kathien.donorua.models.City;
import ua.com.kathien.donorua.models.DonorEvent;

public class EventsParser {

    private static final String REQUEST = "events";
    private static final String LOG_TAG = EventsParser.class.getSimpleName();

    public void parseEvents(ArrayList<DonorEvent> events, ArrayList<City> cities) {

        DonorJSONReader eventsReader = new DonorJSONReader();
        String eventsJson = eventsReader.readJSONfromURL(REQUEST);

        if (eventsJson == null) {
            Log.i(LOG_TAG, "JSON Events is null");
            return;
        }

        try {
            JSONObject jsonRootObject = new JSONObject(eventsJson);
            JSONArray eventsArrayJson = jsonRootObject.getJSONArray("value");

            int eventsArrayLength = eventsArrayJson.length();

            for(int i = 0; i < eventsArrayLength; i++) {
                JSONObject event = eventsArrayJson.getJSONObject(i);

                int id = event.getInt("id");
                String title = event.getString("title");
                String description = event.getString("description");
                String venueName = event.getString("venueName");
                String venueAddress = event.getString("venueAddress");

                int cityId = event.getInt("cityId");
                String cityName = null;
                for(City city : cities) {
                    if(city.getId() == cityId) {
                        cityName = city.getNameUA();
                        break;
                    }
                }

                Date startDate = null;
                try {
                    startDate = DataUtils.stringToDate(event.getString("startDate"));
                } catch (ParseException e) {
                    Log.i(LOG_TAG, "Parse exception in startSate in event " + id);
                }
                Date endDate = null;
                try {
                    startDate = DataUtils.stringToDate(event.getString("endDate"));
                } catch (ParseException e) {
                    Log.i(LOG_TAG, "Parse exception in endDate in event " + id);
                }

                String body = event.getString("body");
                String conditions = event.getString("price");

                DonorEvent newEvent = new DonorEvent(id, title, description, venueName, venueAddress,
                        cityId, cityName, startDate, endDate, body, conditions);
                events.add(newEvent);

            }


        } catch (JSONException e) {
            Log.e(LOG_TAG, "Malformed JSON received. Aborting:  " + e.getMessage());
            e.printStackTrace();
        }

    }
}
