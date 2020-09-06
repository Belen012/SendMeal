package ar.com.giancarellieceiza.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AgregarProducto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);
    }

    //metodo atras del boton atras
    public void Atras (View view){
        Intent atras= new Intent(this, MainActivity.class);
        startActivity(atras);
    }

}