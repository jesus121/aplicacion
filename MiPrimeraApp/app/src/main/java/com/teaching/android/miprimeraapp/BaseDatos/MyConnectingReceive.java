package com.teaching.android.miprimeraapp.BaseDatos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;



public class MyConnectingReceive extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager myConectivity = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = myConectivity.getActiveNetworkInfo();
        boolean miConexion = activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
        Log.d("Cambios","Se ha cambiado de Internet"+miConexion);

    }
}
