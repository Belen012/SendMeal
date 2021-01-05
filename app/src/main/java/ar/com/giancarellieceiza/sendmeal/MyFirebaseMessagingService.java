package ar.com.giancarellieceiza.sendmeal;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import ar.com.giancarellieceiza.sendmeal.Activities.Home;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String CHANNEL_ID = "FirebaseSendMeal";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...
        Log.d("info", "From: " + remoteMessage.getFrom());

        // Pueden validar si el mensaje trae datos
        if (remoteMessage.getData().size() > 0) {
            Log.d("info", "Payload del mensaje: " + remoteMessage.getData());
            // Realizar alguna acción en consecuencia
        }
        // Pueden validar si el mensaje trae una notificación
        if (remoteMessage.getNotification() != null) {
            Log.d("info", "Cuerpo de la notificación del mensaje: " + remoteMessage.getNotification().getBody());
            // También pueden usar:
            // remoteMessage.getNotification().getTitle()
        }
        // Cualquier otra acción que quieran realizar al recibir un mensaje de firebase, la pueden realizar aca
        // EJ:
        sendNotification(remoteMessage.getNotification().getBody(),remoteMessage.getNotification().getTitle());
    }

    // Función para crear una notificación (completar)
    private void sendNotification(String messageBody, String title) {
        Intent intent = new Intent(this, Home.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        CharSequence name = "send meal";
        String description = "Canal de notificaciones desde firebase";

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.comida)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = this.getSystemService(NotificationManager.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(2, builder.build());
    }

}