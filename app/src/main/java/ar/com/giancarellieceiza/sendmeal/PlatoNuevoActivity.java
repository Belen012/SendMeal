package ar.com.giancarellieceiza.sendmeal;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import ar.com.giancarellieceiza.sendmeal.model.Plato;

public class PlatoNuevoActivity extends AppCompatActivity {

    //intanciamos entidades
    Plato platoNuevo = new Plato();

    //UI
    Toolbar toolbar;
    EditText titulo;
    EditText descripcion;
    EditText precio;
    EditText calorias;
    Button guardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plato_nuevo);


        titulo = findViewById(R.id.edit_titulo);
        descripcion = findViewById(R.id.edit_descripcion);
        precio = findViewById(R.id.edit_precio);
        calorias = findViewById(R.id.edit_calorias);
        guardar = findViewById(R.id.button_guardar);


        //Barra con boton hacia atras
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar atras = getSupportActionBar();
        atras.setDisplayHomeAsUpEnabled(true);



    }
}