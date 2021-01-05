package ar.com.giancarellieceiza.sendmeal.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import ar.com.giancarellieceiza.sendmeal.R;
import ar.com.giancarellieceiza.sendmeal.Services.DishServices;
import ar.com.giancarellieceiza.sendmeal.adapters.DishAdapter;
import ar.com.giancarellieceiza.sendmeal.model.Dish;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Dishes extends AppCompatActivity {

    RecyclerView recycler;
    ArrayList<Dish> dishes = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;
    AppCompatActivity self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dishes);

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.234:3001/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        DishServices platoService = retrofit.create(DishServices.class);

        Call<List<Dish>> callDishs = platoService.getDishList();

        callDishs.enqueue(
                new Callback<List<Dish>>() {
                    @Override
                    public void onResponse(Call<List<Dish>> call, Response<List<Dish>> response) {
                        if (response.code() == 200) {
                            for (Dish dish : response.body()) {
                                dishes.add(dish);
                            }
                            DishAdapter dishAdapter = new DishAdapter(dishes);
                            recycler = findViewById(R.id.list);
                            layoutManager = new LinearLayoutManager(self);
                            recycler.setLayoutManager(layoutManager);
                            recycler.setAdapter(dishAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Dish>> call, Throwable t) {
                        Log.d("DEBUG", "Retorno Fallido: " + t.toString());
                    }
                }
        );
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}