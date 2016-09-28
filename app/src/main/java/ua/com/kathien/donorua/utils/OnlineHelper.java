package ua.com.kathien.donorua.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by kathien on 9/26/16.
 */
public class OnlineHelper {

    public static boolean isOnline(Context context) {

        boolean isOnline;
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        isOnline = networkInfo != null && networkInfo.isConnected();
        return isOnline;
    }
}
