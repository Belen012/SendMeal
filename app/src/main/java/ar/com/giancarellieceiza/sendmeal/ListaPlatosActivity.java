package ar.com.giancarellieceiza.sendmeal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ar.com.giancarellieceiza.sendmeal.adapters.PlatoRecyclerAdapter;
import ar.com.giancarellieceiza.sendmeal.dao.PlatosDao;
import ar.com.giancarellieceiza.sendmeal.model.Plato;

public class ListaPlatosActivity extends AppCompatActivity {

    private RecyclerView.LayoutManager layoutManager;
    PlatosDao platosDao;
    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_platos);

        //Barra con boton hacia atras
        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        ActionBar atras = getSupportActionBar();
        atras.setDisplayHomeAsUpEnabled(true);


        platosDao = new PlatosDao();
        PlatoRecyclerAdapter platoRecyclerAdapter = new PlatoRecyclerAdapter(platosDao.list());
        recyclerView = findViewById(R.id.recyclerView_listaPlatos);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(platoRecyclerAdapter);


    }

}