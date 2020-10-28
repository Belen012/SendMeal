package ar.com.giancarellieceiza.sendmeal.UI;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import ar.com.giancarellieceiza.sendmeal.Activities.NewOrder;
import ar.com.giancarellieceiza.sendmeal.R;
import ar.com.giancarellieceiza.sendmeal.model.Dish;

public class DishUI extends RecyclerView.ViewHolder {

    Dish dish;
    TextView price;
    TextView title;
    ImageView image;
    LinearLayout container;

    public DishUI(View v) {
        super(v);
        image = v.findViewById(R.id.image);
        price = v.findViewById(R.id.price);
        title = v.findViewById(R.id.title);
        container = v.findViewById(R.id.container);
        container.setOnClickListener(new onSelect());
    }

    public class onSelect implements View.OnClickListener {
        public void onClick (View v) {
            ar.com.giancarellieceiza.sendmeal.model.Order order;
            Intent intent = ((Activity) v.getContext()).getIntent();
            order = intent.getParcelableExtra("pedido");
            if (order == null) order = new ar.com.giancarellieceiza.sendmeal.model.Order();
            order.add(dish);
            Intent pedidoActivity = new Intent(v.getContext(), NewOrder.class);
            pedidoActivity.putExtra("pedido", order);
            v.getContext().startActivity(pedidoActivity);
        }
    }

    public void setDish(Dish dish) {
        this.dish = dish;
        price.setText(String.valueOf(dish.getPrecio()));
        title.setText(dish.getTitulo());
    }
}
