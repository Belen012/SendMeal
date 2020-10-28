package ar.com.giancarellieceiza.sendmeal.Tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import ar.com.giancarellieceiza.sendmeal.Activities.Home;
import ar.com.giancarellieceiza.sendmeal.notification.MyNotificationPublisher;

public class SaveOrder extends AsyncTask<Void, Void, String> {
    Context context;

    public SaveOrder(Context context) {
        this.context = context;
    };

    @Override
    protected String doInBackground(Void... Void) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        Intent volverHome = new Intent(context, Home.class);
        context.startActivity(volverHome);

        Intent notificationIntent = new Intent(context, MyNotificationPublisher.class);
        context.sendBroadcast(notificationIntent);
    }
}
