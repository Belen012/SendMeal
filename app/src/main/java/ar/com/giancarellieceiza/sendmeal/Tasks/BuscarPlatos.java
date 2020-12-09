package ar.com.giancarellieceiza.sendmeal.Tasks;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import ar.com.giancarellieceiza.sendmeal.Daos.DishDAO;
import ar.com.giancarellieceiza.sendmeal.Helpers.Callback;
import ar.com.giancarellieceiza.sendmeal.model.Dish;

public class BuscarPlatos extends AsyncTask<String, Void, List<Dish>> {
    private DishDAO dao;
    private Callback<String> callback;

    public BuscarPlatos(DishDAO dao, Callback<String> callback) {
        this.dao = dao;
        this.callback = callback;
    }

    protected List<Dish> doInBackground(String... strings) {
        List<Dish> platos = dao.buscarTodos();
        Log.i("Info","Platos encontrados" + platos.toString());
        return platos;
    }

    protected void onPostExecute(List<Dish> platos) {
//        super.onPostExecute(platos);
        callback.onCallback(platos.toString());
    }
}
