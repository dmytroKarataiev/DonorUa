package ua.com.kathien.donorua.utils;

/**
 * Created by kathien on 11/10/16.
 */
public class JSONVerifier {

    protected static String checkStringFromJson(String s) {
        if (s.equals("null") || s.trim().equals("")) s = null;
        return s;
    }
}
