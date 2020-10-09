package ar.com.giancarellieceiza.sendmeal.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import ar.com.giancarellieceiza.sendmeal.R;

public class MyNotificationPublisher extends BroadcastReceiver {
    private static final String CHANNEL_ID = "Se confirmó todo ok";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("info", "Se recibio algo");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "send meal";
            String description = "chanel send meal";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.comida)
                .setContentTitle("¡Confirmado!")
                .setContentText("Su pedido ha sido confirmado")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(2, builder.build());

    }

}
