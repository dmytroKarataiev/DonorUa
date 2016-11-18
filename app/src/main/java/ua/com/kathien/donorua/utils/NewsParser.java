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

import ua.com.kathien.donorua.models.News;

/**
 * Created by kathien on 8/18/16.
 */
public class NewsParser {

    private static final String REQUEST = "news";
    private static final String LOG_TAG = NewsParser.class.getSimpleName();

    public void parseNews(ArrayList<News> newsList) {
        DonorJSONReader newsReader = new DonorJSONReader();
        String newsJSON = newsReader.readJSONfromURL(REQUEST);

        //FIXME: null check json
        if(newsJSON == null || newsJSON.trim() == "") {
            Log.e(LOG_TAG, "JSON is null");
            return;
        }

        try {
            JSONObject newsListJson = new JSONObject(newsJSON);
            JSONArray newsJsonArray = newsListJson.getJSONArray("value");
            int newsArrayLength = newsJsonArray.length();
            for (int i = 0; i < newsArrayLength; i++) {
                JSONObject newsObject = newsJsonArray.getJSONObject(i);

                int id = newsObject.getInt("id");

                Date datePublished = null;
                try {
                    datePublished = DataUtils.stringToDate(JSONVerifier.checkStringFromJson(newsObject.getString("datePublished")));
                } catch (ParseException e) {
                    Log.i(LOG_TAG, "Parse exception in datePublished in news " + id);
                }

                //FIXME: app crashes if date is null
                Date lastUpdated = null;
                try {
                    String lastUpdatedDateString = JSONVerifier.checkStringFromJson(newsObject.getString("lastUpdatedDate"));
                    if(lastUpdatedDateString != null) {
                        lastUpdated = DataUtils.stringToDate(lastUpdatedDateString);
                    }
                } catch (ParseException e) {
                    Log.i(LOG_TAG, "Parse exception in lastUpdated in news " + id);
                }

                boolean isPublished = newsObject.getBoolean("isPublished");
                int contentTypeId = newsObject.getInt("contentTypeId");

                URL image = null;
                try {
                    image = new URL(JSONVerifier.checkStringFromJson(newsObject.getString("image")));
                } catch (MalformedURLException e) {
                    Log.i(LOG_TAG, "Malformed image URL in news " + id);
                }

                URL bigImage = null;
                try {
                    bigImage = new URL(JSONVerifier.checkStringFromJson(newsObject.getString("bigImage")));
                } catch (MalformedURLException e) {
                    Log.i(LOG_TAG, "Malformed bigImage URL in news " + id);
                }

                int userProfileId = newsObject.getInt("userProfileId");
                String shortDescription = JSONVerifier.checkStringFromJson(newsObject.getString("shortDescription"));
                String title = JSONVerifier.checkStringFromJson(newsObject.getString("name"));
                String description = JSONVerifier.checkStringFromJson(newsObject.getString("description"));

                News newNews = new News(id, datePublished, lastUpdated, isPublished, contentTypeId,
                        image, bigImage, userProfileId, shortDescription, title, description);

                newsList.add(newNews);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Malformed JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
