package ar.com.giancarellieceiza.sendmeal;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ar.com.giancarellieceiza.sendmeal.dao.PlatosDao;
import ar.com.giancarellieceiza.sendmeal.model.Plato;

public class PlatoNuevoActivity extends AppCompatActivity {

    //PlatosDao platosDao;

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
        //platosDao = new PlatosDao();

        //Barra con boton hacia atras
        toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        ActionBar atras = getSupportActionBar();
        atras.setDisplayHomeAsUpEnabled(true);

        guardar.setOnClickListener(new View.OnClickListener() {
           Plato platoNuevo = new Plato();
            @Override
            public void onClick(View v) {
                if (titulo.getText().toString().isEmpty()) {
                    showToast("Ingrese un título");
                    return;
                }
                if (precio.getText().toString().isEmpty()) {
                    showToast("Ingrese el precio del plato");
                    return;
                }
                if (!descripcion.getText().toString().isEmpty()) {
                    platoNuevo.setDescripcion(descripcion.getText().toString());
                }
                if (!calorias.getText().toString().isEmpty()) {
                    platoNuevo.setCalorias(Integer.valueOf(calorias.getText().toString()));
                }

                platoNuevo.setTitulo(titulo.getText().toString());
                platoNuevo.setPrecio(Double.parseDouble(precio.getText().toString()));

                //platosDao.add(platoNuevo);
                showToast("¡Su plato ya se encuentra registrado!");
            }
        });


    }
    void showToast(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    };

}