package ar.com.giancarellieceiza.sendmeal.Tasks;

import android.os.AsyncTask;

import java.util.List;

import ar.com.giancarellieceiza.sendmeal.Daos.DishDAO;
import ar.com.giancarellieceiza.sendmeal.Helpers.Callback;
import ar.com.giancarellieceiza.sendmeal.model.Dish;

public class BuscarPlatos extends AsyncTask<String, Void, List<Dish>> {
    private DishDAO dao;
    private Callback callback;

    public BuscarPlatos(DishDAO dao, Callback callback) {
        this.dao = dao;
        this.callback = callback;
    }

    protected List<Dish> doInBackground(String... strings) {
        List<Dish> platos = dao.buscarTodos();
        return platos;
    }

    protected void onPostExecute(List<Dish> platos) {
        super.onPostExecute(platos);
        callback.onCallback();
    }
}
