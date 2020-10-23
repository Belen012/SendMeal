package ar.com.giancarellieceiza.sendmeal.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ar.com.giancarellieceiza.sendmeal.R;
import ar.com.giancarellieceiza.sendmeal.UI.DishUI;
import ar.com.giancarellieceiza.sendmeal.model.Dish;

public class DishAdapter extends RecyclerView.Adapter<DishUI> {
    List<Dish> dishes;

    public DishAdapter(List<Dish> dishes){
        this.dishes = dishes;
    }

    @NonNull
    @Override
    public DishUI onCreateViewHolder(@NonNull ViewGroup parent, int tipo) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dish, parent,false);
        DishUI dish = new DishUI(v);
        return dish;
    }

    @Override
    public void onBindViewHolder(@NonNull DishUI holder, int position) {
        holder.setDish(dishes.get(position));
    }

    @Override
    public int getItemCount() {
        return this.dishes.size();
    }

}
