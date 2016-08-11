package ua.com.kathien.donorua.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Various util methods for parsing and pretty printing date/time values.
 */

public class DataUtils {
    // Date/Time format as received from the server
    public static String dateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    // Readable Date/Time format
    public static String prettyDateFormat = "MMM dd, yyyy HH:mm:ss";
    // Readable Date format for birthdays
    public static String prettyBirthdayFormat = "MMM dd, yyyy";


    public static String dateToPrettyString(Date date) {

        Locale currentLocale = Locale.getDefault();
        SimpleDateFormat sdf = new SimpleDateFormat(prettyDateFormat, currentLocale);

        return sdf.format(date);
    }

    public static String birthDateToPrettyString(Date date) {

        Locale currentLocale = Locale.getDefault();
        SimpleDateFormat sdf = new SimpleDateFormat(prettyBirthdayFormat, currentLocale);

        return sdf.format(date);
    }

    public static Date stringToDate(String string) throws ParseException {
        Locale currentLocale = Locale.getDefault();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, currentLocale);

        return sdf.parse(string);
    }

}
