package ar.com.giancarellieceiza.sendmeal.Activities;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ar.com.giancarellieceiza.sendmeal.R;
import ar.com.giancarellieceiza.sendmeal.model.Dish;

public class NewDish extends AppCompatActivity {

    //PlatosDao platosDao;

    //UI
    EditText titulo;
    EditText descripcion;
    EditText precio;
    EditText calorias;
    Button guardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_dish);


        titulo = findViewById(R.id.edit_titulo);
        descripcion = findViewById(R.id.edit_descripcion);
        precio = findViewById(R.id.edit_precio);
        calorias = findViewById(R.id.edit_calorias);
        guardar = findViewById(R.id.button_guardar);
        //platosDao = new PlatosDao();

        guardar.setOnClickListener(new View.OnClickListener() {
           Dish platoNuevo = new Dish();
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

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    void showToast(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    };

}