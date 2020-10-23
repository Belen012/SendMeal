package ar.com.giancarellieceiza.sendmeal.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import ar.com.giancarellieceiza.sendmeal.R;
import ar.com.giancarellieceiza.sendmeal.adapters.DishAdapter;
import ar.com.giancarellieceiza.sendmeal.model.Dish;

public class Dishes extends AppCompatActivity {

    RecyclerView recycler;
    ArrayList<Dish> dishes = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dishes);

        dishes.add(new Dish("Fideos","Fideos con salsa bella", 200, 10));
        dishes.add(new Dish("Hamburguesa", "Tomate, huevo frito, lechuga, jamon y queso", 300,522));
        dishes.add(new Dish("Ñoquis","Ñoquis con salsa 4 quesos",200 , 300 ));
        dishes.add(new Dish("Ravioles","Ravioles de ricota y acelga",200 ,130 ));
        dishes.add(new Dish("Lomito simple","Lomo simple con lechucha y tomate",190 ,160 ));
        dishes.add(new Dish("Lomito super","Lomo completo con jamon, queso, tomate, huevo",230 ,190 ));
        dishes.add(new Dish("Pizza muzzarella","Muzzarella y aceitunas", 320 ,240 ));
        dishes.add(new Dish("Pizza napolitana","Muzzarella, rodajas de tomate, queso rallado y oregano", 380,250 ));
        dishes.add(new Dish("Papas fritas con cheddar","Porcion para 1 persona",95 ,200 ));
        dishes.add(new Dish("Papas fritas simples","Porcion para 1 persona",50 ,140 ));
        dishes.add(new Dish("Milanesa a la pizza con papas fritas","Milanesa con salsa, oregano, aceitunas, y papas fritas",290 ,400 ));

        DishAdapter dishAdapter = new DishAdapter(dishes);
        recycler = findViewById(R.id.list);
        layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(dishAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}