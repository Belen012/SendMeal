package ar.com.giancarellieceiza.sendmeal.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import ar.com.giancarellieceiza.sendmeal.Database.AppRepository;
import ar.com.giancarellieceiza.sendmeal.Helpers.Callback;
import ar.com.giancarellieceiza.sendmeal.R;
import ar.com.giancarellieceiza.sendmeal.Tasks.SaveOrder;
import ar.com.giancarellieceiza.sendmeal.adapters.DishAdapter;
import ar.com.giancarellieceiza.sendmeal.model.Dish;
import ar.com.giancarellieceiza.sendmeal.model.Order;
import ar.com.giancarellieceiza.sendmeal.notification.MyNotificationPublisher;

public class NewOrder extends AppCompatActivity {

    ar.com.giancarellieceiza.sendmeal.model.Order pedido;
    TextView monto;
    RecyclerView listaPlatos;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_order);

        Intent intent = getIntent();
        pedido = (Order) intent.getParcelableExtra("pedido");

        double totalCost = 0;
        for (Dish dish : pedido.getPlatos()) {
            totalCost += dish.getPrecio();
        };

        ((TextView) findViewById(R.id.monto)).setText(String.valueOf(totalCost));

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
    };

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    };

    public void onConfirmarPedido(android.view.View v) {
        AppRepository repository = new AppRepository(this.getApplication());
        repository.addOrder(pedido, new Callback() {
            @Override
            public void onCallback() {
                Log.i("info","Pedido agregado correctamente");
            }
        });
    }
}