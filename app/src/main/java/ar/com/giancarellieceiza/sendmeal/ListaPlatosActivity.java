package ar.com.giancarellieceiza.sendmeal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ar.com.giancarellieceiza.sendmeal.adapters.PlatoRecyclerAdapter;
import ar.com.giancarellieceiza.sendmeal.model.Plato;

public class ListaPlatosActivity extends AppCompatActivity {
    //public Plato(String titulo, String descripcion, double precio, Integer calorias)

    List<Plato> listaPlatos = new ArrayList<Plato>();

    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_platos);

        //Barra con boton hacia atras
        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        ActionBar atras = getSupportActionBar();
        atras.setDisplayHomeAsUpEnabled(true);


        Plato plato = new Plato("fideos","fideos con salsa bella", 200, 10);
        listaPlatos.add(plato);
        PlatoRecyclerAdapter platoRecyclerAdapter = new PlatoRecyclerAdapter(listaPlatos);

        recyclerView = findViewById(R.id.recyclerView_listaPlatos);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(platoRecyclerAdapter);


    }
}