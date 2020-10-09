package ar.com.giancarellieceiza.sendmeal.adapters;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import ar.com.giancarellieceiza.sendmeal.PedidoActivity;
import ar.com.giancarellieceiza.sendmeal.R;
import ar.com.giancarellieceiza.sendmeal.model.Pedido;

public class PlatoViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView precio;
    TextView titulo;
    LinearLayout filaPlato;
    Pedido pedido;

    public PlatoViewHolder(View v) {
        super(v);
        imageView = v.findViewById(R.id.imageView);
        precio = v.findViewById(R.id.textView_precio);
        titulo = v.findViewById(R.id.textView_titulo);
        filaPlato = v.findViewById(R.id.fila_plato);

        final Intent pedidoActivity = new Intent(v.getContext(), PedidoActivity.class);
        pedido = new Pedido();
        pedido.setCorreo("belen@outlook.com");

        //pedido.getPlatosSeleccionados().add(plato); no se obtener el plato que seleccionó el usuario

        filaPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pedidoActivity.putExtra("plato seleccionado", pedido);
                v.getContext().startActivity(pedidoActivity);
            }
        });


    }


}
