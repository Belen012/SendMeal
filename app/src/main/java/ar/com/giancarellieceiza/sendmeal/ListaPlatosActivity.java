package ar.com.giancarellieceiza.sendmeal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import ar.com.giancarellieceiza.sendmeal.adapters.PlatoRecyclerAdapter;
import ar.com.giancarellieceiza.sendmeal.model.Plato;

public class ListaPlatosActivity extends AppCompatActivity {
    //public Plato(String titulo, String descripcion, double precio, Integer calorias)
    Plato plato = new Plato("fideos","fideos con salsa bella", 200, 10);
    List<Plato> listaPlatos;

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_platos);

        listaPlatos.add(plato);
        PlatoRecyclerAdapter platoRecyclerAdapter = new PlatoRecyclerAdapter(listaPlatos);

        recyclerView = findViewById(R.id.recyclerView_listaPlatos);



    }
}