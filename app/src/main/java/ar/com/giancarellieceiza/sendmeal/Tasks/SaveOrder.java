package ar.com.giancarellieceiza.sendmeal.Tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import ar.com.giancarellieceiza.sendmeal.Activities.Home;
import ar.com.giancarellieceiza.sendmeal.Daos.OrderDAO;
import ar.com.giancarellieceiza.sendmeal.Helpers.Callback;
import ar.com.giancarellieceiza.sendmeal.model.Order;
import ar.com.giancarellieceiza.sendmeal.notification.MyNotificationPublisher;

public class SaveOrder extends AsyncTask<Void, Void, String> {
    OrderDAO orderDAO;
    Order newOrder;
    Callback<String> callback;

    public SaveOrder(OrderDAO orderDAO, Callback<String> callback, Order newOrder) {
        this.orderDAO = orderDAO;
        this.newOrder = newOrder;
        this.callback = callback;
    };

    @Override
    protected String doInBackground(Void... Void) {
        orderDAO.insertar(newOrder);
        return "Exito";
    };

    @Override
    protected void onPostExecute(String result) {
        callback.onCallback("OK");
    }
}
