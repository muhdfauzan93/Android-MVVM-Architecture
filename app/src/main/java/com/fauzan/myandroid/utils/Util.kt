package my.com.fauzan.androidarchitecture.utils

import android.content.Context
import android.net.ConnectivityManager

class Util {
    @Suppress("DEPRECATION")
    companion object {
        /**
         * Checking connectivity
         * @param context activity context
         * @return connection status
         */
        fun isConnectedToNetwork(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var isConnected: Boolean
            val activeNetwork = connectivityManager.activeNetworkInfo
            isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting
            return isConnected
        }
    }
}