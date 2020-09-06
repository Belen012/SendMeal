package ar.com.giancarellieceiza.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    //defino los objetos que voy a usar para comunicarme con la parte grafica
    private TextView productos; //campo de texto
    private ListView lista_productos; //lista
    private String nombreProductos[] ={"Xiaomi", "Samsung", "Motorola","Iphone"}; //vector que va a tener los items


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productos = (TextView) findViewById(R.id.productos);
        lista_productos = (ListView) findViewById(R.id.listaProductos);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.lista_items_productos, nombreProductos);
        lista_productos.setAdapter(adapter);

    }
    //metodo que va en el boton "+" para agregar productos
    

}






/*

FloatingActionButton fab = findViewById(R.id.botonAgregarProducto);
        fab.setOnClickListener();
                fab.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        items.add("perlas");
        }
        });*/