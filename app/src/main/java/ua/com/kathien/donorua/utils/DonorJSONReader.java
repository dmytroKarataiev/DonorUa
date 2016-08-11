package ua.com.kathien.donorua.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class is used to fetch JSON from provided URL. *
 */

public class DonorJSONReader {
    private static final String API_URL = "https://donor.ua/api/";
    private static final String LOG_TAG = DonorJSONReader.class.getSimpleName();

    protected String readJSONfromURL(String request) {

        HttpURLConnection conn = null;
        try {

            URL urlGet = new URL(API_URL + request);

            conn = (HttpURLConnection) urlGet.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            conn.connect();
            int status = conn.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    return sb.toString();
                case 400:
                case 403:
                case 404:
                case 500:
                case 501:
                case 503:
                    Log.e(LOG_TAG, "Error with code " + status);
                    break;
                default:
                    Log.e(LOG_TAG, "Unexpected server response " + status);
            }

        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Log.e(LOG_TAG, e.getMessage());
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return null;
    }

}
