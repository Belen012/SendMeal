package ar.com.giancarellieceiza.sendmeal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import ar.com.giancarellieceiza.sendmeal.adapters.PlatoRecyclerAdapter;
import ar.com.giancarellieceiza.sendmeal.dao.PedidoDao;
import ar.com.giancarellieceiza.sendmeal.model.Pedido;
import ar.com.giancarellieceiza.sendmeal.model.Plato;

public class PedidoActivity extends AppCompatActivity {

    Pedido pedido;
    TextView monto;
    RecyclerView listaPlatos;
    private RecyclerView.LayoutManager layoutManager;
    PedidoDao pedidoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        Intent intent = getIntent();
        pedido = (Pedido) intent.getParcelableExtra("pedido");

        monto = findViewById(R.id.textView_monto);
        float precio = 0;
        for (Plato plato : pedido.getPlatos()) {
            precio += plato.getPrecio();
        }

        monto.setText("Total: " + String.valueOf(precio));

        //Barra con boton hacia atras
        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        ActionBar atras = getSupportActionBar();
        atras.setDisplayHomeAsUpEnabled(true);

        listaPlatos = findViewById(R.id.recyclerView_listaPlatos);
        PlatoRecyclerAdapter platoRecyclerAdapter = new PlatoRecyclerAdapter(pedido.getPlatos());
        layoutManager = new LinearLayoutManager(this);
        listaPlatos.setLayoutManager(layoutManager);
        listaPlatos.setAdapter(platoRecyclerAdapter);
    }

    public void onAgregarPlato(View v) {
        Intent intent = new Intent(this,ListaPlatosActivity.class);
        intent.putExtra("pedido",pedido);
        startActivity(intent);
    }

    public void onConfirmarPedido(View v ){
        pedidoDao = new PedidoDao(this);
        pedidoDao.Confirmar();

    }
}