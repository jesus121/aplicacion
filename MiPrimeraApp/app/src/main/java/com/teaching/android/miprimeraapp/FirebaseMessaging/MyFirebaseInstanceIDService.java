package com.teaching.android.miprimeraapp.FirebaseMessaging;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService  {

    public void onTokenRefresh (){
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("InstaciaId","Refrescar token :"+refreshedToken);
        FirebaseDatabase baseDEdatos = FirebaseDatabase.getInstance();
        DatabaseReference referencia = baseDEdatos.getReference("device_token");
        referencia.setValue(refreshedToken);
    }
}
