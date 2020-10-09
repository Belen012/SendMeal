package ar.com.giancarellieceiza.sendmeal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import ar.com.giancarellieceiza.sendmeal.model.Pedido;

public class PedidoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        //Barra con boton hacia atras
        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        ActionBar atras = getSupportActionBar();
        atras.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Pedido pedido = (Pedido) intent.getParcelableExtra("plato seleccionado");

        Log.i("info", pedido.getCorreo());




    }
}