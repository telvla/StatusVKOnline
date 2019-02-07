package at.telvla.statusvkonline;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class isOnline {
    public static boolean getOnline (Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
            return false;
    }
}
