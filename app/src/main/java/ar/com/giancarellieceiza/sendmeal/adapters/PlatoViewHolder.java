package ar.com.giancarellieceiza.sendmeal.adapters;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ar.com.giancarellieceiza.sendmeal.PedidoActivity;
import ar.com.giancarellieceiza.sendmeal.R;
import ar.com.giancarellieceiza.sendmeal.model.Pedido;
import ar.com.giancarellieceiza.sendmeal.model.Plato;

public class PlatoViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView precio;
    TextView titulo;
    LinearLayout filaPlato;
    Pedido pedido;
    Plato plato;

    public PlatoViewHolder(View v) {
        super(v);
        imageView = v.findViewById(R.id.imageView);
        precio = v.findViewById(R.id.textView_precio);
        titulo = v.findViewById(R.id.textView_titulo);
        filaPlato = v.findViewById(R.id.fila_plato);
        pedido = new Pedido();

        filaPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pedido pedido;
                Intent intent = ((Activity) v.getContext()).getIntent();
                pedido = intent.getParcelableExtra("pedido");
                if (pedido == null) {
                    pedido = new Pedido();
                }
                pedido.add(plato);
                Intent pedidoActivity = new Intent(v.getContext(), PedidoActivity.class);
                pedidoActivity.putExtra("pedido", pedido);
                v.getContext().startActivity(pedidoActivity);
            }
        });


    }

    public void setPlato(Plato plato) {
        this.plato = plato;
        precio.setText(String.valueOf(plato.getPrecio()));
        titulo.setText(plato.getTitulo());
    }


}
