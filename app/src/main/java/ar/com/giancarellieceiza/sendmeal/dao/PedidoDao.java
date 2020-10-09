package ar.com.giancarellieceiza.sendmeal.dao;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import ar.com.giancarellieceiza.sendmeal.HomeActivity;
import ar.com.giancarellieceiza.sendmeal.PedidoActivity;
import ar.com.giancarellieceiza.sendmeal.notification.MyNotificationPublisher;

public class PedidoDao {
    int time = 2000;
    Context contexto;

    private class ConfirmarPedido extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... Void) {
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            Intent volverHome = new Intent(contexto, HomeActivity.class);
            contexto.startActivity(volverHome);


            Intent notificationIntent = new Intent(contexto, MyNotificationPublisher.class);
            contexto.sendBroadcast(notificationIntent);
            // ...
            //alarmManager.set( ... );
        }

    }

    public void Confirmar(){
        new ConfirmarPedido().execute();
    }

    public PedidoDao(Context contexto){
        this.contexto = contexto;
    }


}
