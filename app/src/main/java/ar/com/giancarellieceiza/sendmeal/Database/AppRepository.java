package ar.com.giancarellieceiza.sendmeal.Database;
import ar.com.giancarellieceiza.sendmeal.Daos.DishDAO;
import ar.com.giancarellieceiza.sendmeal.Daos.OrderDAO;
import ar.com.giancarellieceiza.sendmeal.Helpers.Callback;
import ar.com.giancarellieceiza.sendmeal.Tasks.BuscarPlatos;
import ar.com.giancarellieceiza.sendmeal.Tasks.ListOrders;
import ar.com.giancarellieceiza.sendmeal.Tasks.SaveOrder;
import ar.com.giancarellieceiza.sendmeal.model.Dish;
import ar.com.giancarellieceiza.sendmeal.model.Order;
import android.app.Application;
import android.util.Log;
import java.util.List;

public class AppRepository {
    private DishDAO dishDAO;
    private OrderDAO orderDAO;

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
    };

    public void buscarTodos(Callback<String> callback) {
        new BuscarPlatos(this.dishDAO, callback).execute();
    };

    public void addOrder(Order newOrder, Callback<String> callback) {
        (new SaveOrder(this.orderDAO, callback, newOrder)).execute();
    };

    public void listOrders (Callback<List<Order>> callback) {
        new ListOrders(this.orderDAO, callback).execute();
    };
}
