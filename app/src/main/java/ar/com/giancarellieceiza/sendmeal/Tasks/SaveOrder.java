package ar.com.giancarellieceiza.sendmeal.Tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import ar.com.giancarellieceiza.sendmeal.Activities.Home;
import ar.com.giancarellieceiza.sendmeal.Daos.OrderDAO;
import ar.com.giancarellieceiza.sendmeal.Helpers.Callback;
import ar.com.giancarellieceiza.sendmeal.model.Order;
import ar.com.giancarellieceiza.sendmeal.notification.MyNotificationPublisher;

public class SaveOrder extends AsyncTask<Void, Void, String> {
    Context context;
    OrderDAO orderDAO;
    Order newOrder;
    Callback callback;

    public SaveOrder(Context context) {
        this.context = context;
    };

    public SaveOrder(OrderDAO orderDAO, Callback callback, Order newOrder) {
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
        Intent volverHome = new Intent(context, Home.class);
        context.startActivity(volverHome);

        Intent notificationIntent = new Intent(context, MyNotificationPublisher.class);
        context.sendBroadcast(notificationIntent);
    }
}
