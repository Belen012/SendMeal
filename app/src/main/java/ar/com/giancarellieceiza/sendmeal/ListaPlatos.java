package ar.com.giancarellieceiza.sendmeal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ListaPlatos extends AppCompatActivity {

    RecyclerView listaPlatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_platos);

        listaPlatos = findViewById(R.id.listaPlatos);



    }
}