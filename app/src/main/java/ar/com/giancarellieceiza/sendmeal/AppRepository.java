package ar.com.giancarellieceiza.sendmeal;

import android.app.Application;
import android.util.Log;

import java.util.List;

import ar.com.giancarellieceiza.sendmeal.dao.DatoDAO;
import ar.com.giancarellieceiza.sendmeal.model.Dato;

public class AppRepository {
    private DatoDAO datoDao;
    private OnResultCallback callback;

    public AppRepository(Application application, OnResultCallback context){
        AppDatabase db = AppDatabase.getInstance(application);
        datoDao = db.datoDAO();
        callback = context;
    }

    public void buscarTodos() {
        new BuscarDato(datoDao, this).execute();
    }

    public void onResult(List<Dato> platos) {
        Log.d("DEBUG", "Plato found");
        callback.onResult(platos);
    }

    public interface OnResultCallback<T> {
        void onResult(List<T> result);
    }
}