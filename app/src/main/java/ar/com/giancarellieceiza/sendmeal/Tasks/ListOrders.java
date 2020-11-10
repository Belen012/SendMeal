package ar.com.giancarellieceiza.sendmeal.Tasks;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import ar.com.giancarellieceiza.sendmeal.Daos.OrderDAO;
import ar.com.giancarellieceiza.sendmeal.Helpers.Callback;
import ar.com.giancarellieceiza.sendmeal.model.Dish;
import ar.com.giancarellieceiza.sendmeal.model.Order;

public class ListOrders extends AsyncTask<Void, Void, String> {
    OrderDAO orderDAO;
    List<Order> orders;
    Callback<List<Order>> callback;

    public ListOrders (OrderDAO orderDAO, Callback<List<Order>> callback) {
        this.orderDAO = orderDAO;
        this.callback = callback;
    };

    @Override
    protected String doInBackground(Void... Void) {
        this.orders = orderDAO.selectAll();
        return "Exito";
    };

    @Override
    protected void onPostExecute(String result) {
        callback.onCallback(orders);
    }
}
