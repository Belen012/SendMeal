package ar.com.giancarellieceiza.sendmeal;
import android.os.AsyncTask;
import java.util.List;
import ar.com.giancarellieceiza.sendmeal.dao.DatoDAO;
import ar.com.giancarellieceiza.sendmeal.model.Dato;

class BuscarDato extends AsyncTask<String, Void, List<Dato>> {

    private DatoDAO dao;
    private AppRepository callback;

    public BuscarDato(DatoDAO dao, AppRepository context) {
        this.dao = dao;
        this.callback = context;
    };

    @Override
    protected List<Dato> doInBackground(String... strings) {
        List<Dato> datos = dao.buscarTodos();
        return datos;
    };

    @Override
    protected void onPostExecute(List<Dato> datos) {
        super.onPostExecute(datos);
        callback.onResult(datos);
    };
};
