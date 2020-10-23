package ar.com.giancarellieceiza.sendmeal.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import ar.com.giancarellieceiza.sendmeal.R;
import ar.com.giancarellieceiza.sendmeal.adapters.DishAdapter;
import ar.com.giancarellieceiza.sendmeal.model.Order;
import ar.com.giancarellieceiza.sendmeal.model.Dish;

public class PedidoActivity extends AppCompatActivity {

    Order pedido;
    TextView monto;
    RecyclerView listaPlatos;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        Intent intent = getIntent();
        pedido = (Order) intent.getParcelableExtra("pedido");

        monto = findViewById(R.id.monto);
        float precio = 0;
        for (Dish plato : pedido.getPlatos()) {
            precio += plato.getPrecio();
        }

        monto.setText("Total: " + String.valueOf(precio));

        //Barra con boton hacia atras
        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        ActionBar atras = getSupportActionBar();
        atras.setDisplayHomeAsUpEnabled(true);

        listaPlatos = findViewById(R.id.listaPlatos);
        DishAdapter platoRecyclerAdapter = new DishAdapter(pedido.getPlatos());
        layoutManager = new LinearLayoutManager(this);
        listaPlatos.setLayoutManager(layoutManager);
        listaPlatos.setAdapter(platoRecyclerAdapter);
    }

    public void onAgregarPlato(View v) {
        Intent intent = new Intent(this, Dishes.class);
        intent.putExtra("pedido",pedido);
        startActivity(intent);
    }
}