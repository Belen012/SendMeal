package ar.com.giancarellieceiza.sendmeal.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ar.com.giancarellieceiza.sendmeal.R;
import ar.com.giancarellieceiza.sendmeal.model.Plato;

public class PlatoRecyclerAdapter extends RecyclerView.Adapter<PlatoViewHolder> {
    List<Plato> listaPlatos;

    public PlatoRecyclerAdapter (List<Plato> listaPlatos){
        this.listaPlatos = listaPlatos;
    }

    @NonNull
    @Override
    public PlatoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int tipo) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_plato, parent,false);
        PlatoViewHolder platoViewHolder = new PlatoViewHolder(v);
        return platoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlatoViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return this.listaPlatos.size();
    }

}
