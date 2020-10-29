package ar.com.giancarellieceiza.sendmeal.Database;

import android.app.Application;
import android.telecom.Call;
import android.util.Log;

import java.util.List;

import ar.com.giancarellieceiza.sendmeal.Daos.DishDAO;
import ar.com.giancarellieceiza.sendmeal.Daos.OrderDAO;
import ar.com.giancarellieceiza.sendmeal.Helpers.Callback;
import ar.com.giancarellieceiza.sendmeal.Tasks.BuscarPlatos;
import ar.com.giancarellieceiza.sendmeal.Tasks.SaveOrder;
import ar.com.giancarellieceiza.sendmeal.model.Dish;
import ar.com.giancarellieceiza.sendmeal.model.Order;

public class AppRepository {
    private DishDAO dishDAO;
    private OrderDAO orderDAO;
    private Callback callback;

    public AppRepository(Application application, Callback callback){
        AppDatabase db = AppDatabase.getInstance(application);
        dishDAO = db.dishDAO();
        orderDAO = db.orderDAO();
        this.callback = callback;
    }

    public AppRepository(Application application){
        AppDatabase db = AppDatabase.getInstance(application);
        dishDAO = db.dishDAO();
        orderDAO = db.orderDAO();
    }

    public void insertar(final Dish dish){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dishDAO.insertar(dish);
            }
        });
    }

    public void buscarTodos() {
        new BuscarPlatos(this.dishDAO, this.callback).execute();
    }

    public void addOrder(Order newOrder, Callback callback) {
        (new SaveOrder(this.orderDAO, callback, newOrder)).execute();
    }
}
