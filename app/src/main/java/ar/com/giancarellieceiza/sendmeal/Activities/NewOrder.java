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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import ar.com.giancarellieceiza.sendmeal.Database.AppRepository;
import ar.com.giancarellieceiza.sendmeal.Helpers.Callback;
import ar.com.giancarellieceiza.sendmeal.R;
import ar.com.giancarellieceiza.sendmeal.Services.OrderServices;
import ar.com.giancarellieceiza.sendmeal.Tasks.SaveOrder;
import ar.com.giancarellieceiza.sendmeal.adapters.DishAdapter;
import ar.com.giancarellieceiza.sendmeal.model.Dish;
import ar.com.giancarellieceiza.sendmeal.model.Order;
import ar.com.giancarellieceiza.sendmeal.notification.MyNotificationPublisher;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewOrder extends AppCompatActivity {

    ar.com.giancarellieceiza.sendmeal.model.Order pedido;
    TextView monto;
    EditText editTextTextPersonName;
    EditText editTextTextPersonName2;
    Spinner spinner;
    RecyclerView listaPlatos;
    private RecyclerView.LayoutManager layoutManager;
    Context self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_order);

        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);
        spinner = findViewById(R.id.spinner);

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
        pedido.setCorreo(editTextTextPersonName.getText().toString());
        pedido.setDireccion(editTextTextPersonName2.getText().toString());
        pedido.setTipoEnvio(spinner.getSelectedItem().toString());

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.106:3001/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        OrderServices orderServices = retrofit.create(OrderServices.class);
        Call<Order> newOrderCall = orderServices.createOrder(pedido);

        newOrderCall.enqueue(
            new retrofit2.Callback<Order>() {
                @Override
                public void onResponse(Call<Order> call, Response<Order> response) {
                    if (response.code() == 200) {
                        Log.i("Info",response.body().getCorreo());
                    } else {
                        Log.i("Info","Something happend" + response.code());
                    }
                }

                @Override
                public void onFailure(Call<Order> call, Throwable t) {
                    Log.d("DEBUG", "Retorno Fallido: " + t.toString());
                }
            }
        );

        repository.addOrder(pedido, new Callback<String>() {
            @Override
            public void onCallback(String a) {
                Intent volverHome = new Intent(self, Home.class);
                self.startActivity(volverHome);

                Intent notificationIntent = new Intent(self, MyNotificationPublisher.class);
                self.sendBroadcast(notificationIntent);
                Log.i("info","Pedido agregado correctamente");
            }
        });
    }
}