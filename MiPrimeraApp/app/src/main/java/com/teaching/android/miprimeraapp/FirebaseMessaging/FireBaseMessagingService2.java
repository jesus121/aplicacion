package com.teaching.android.miprimeraapp.FirebaseMessaging;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.teaching.android.miprimeraapp.R;

import java.util.Map;

public class FireBaseMessagingService2 extends FirebaseMessagingService {

public void onMessageReceived (RemoteMessage remoteMessage){
    super.onMessageReceived(remoteMessage);
    Map<String,String> data= remoteMessage.getData();

    Log.d("Mensaje","mensaje de firebase"+remoteMessage.getData().toString());
    if (data.containsKey("show_notification")){
        //Mostrar Notificación
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Hola que ase")
                .setContentText("que pasa");

        NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1,builder.build());
    }

}
}
