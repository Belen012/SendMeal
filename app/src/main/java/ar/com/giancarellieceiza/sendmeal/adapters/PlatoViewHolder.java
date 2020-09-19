package ar.com.giancarellieceiza.sendmeal.adapters;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import ar.com.giancarellieceiza.sendmeal.R;

public class PlatoViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView precio;
    TextView titulo;

    public PlatoViewHolder(View v) {
        super(v);
        imageView = v.findViewById(R.id.imageView);
        precio = v.findViewById(R.id.textView_precio);
        titulo = v.findViewById(R.id.textView_titulo);
    }
}
