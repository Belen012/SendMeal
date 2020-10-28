package ar.com.giancarellieceiza.sendmeal.dao;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import ar.com.giancarellieceiza.sendmeal.model.Plato;

@Dao
public class PlatosDao {

    List<Plato> listaPlatos = new ArrayList<Plato>();

    private void iniciar(){
        if(listaPlatos.isEmpty()){
            listaPlatos.add(new Plato("Fideos","Fideos con salsa bella", 200, 10));
            listaPlatos.add(new Plato("Hamburguesa", "Tomate, huevo frito, lechuga, jamon y queso", 300,522));
            listaPlatos.add(new Plato("Ñoquis","Ñoquis con salsa 4 quesos",200 , 300 ));
            listaPlatos.add(new Plato("Ravioles","Ravioles de ricota y acelga",200 ,130 ));
            listaPlatos.add(new Plato("Lomito simple","Lomo simple con lechucha y tomate",190 ,160 ));
            listaPlatos.add(new Plato("Lomito super","Lomo completo con jamon, queso, tomate, huevo",230 ,190 ));
            listaPlatos.add(new Plato("Pizza muzzarella","Muzzarella y aceitunas", 320 ,240 ));
            listaPlatos.add(new Plato("Pizza napolitana","Muzzarella, rodajas de tomate, queso rallado y oregano", 380,250 ));
            listaPlatos.add(new Plato("Papas fritas con cheddar","Porcion para 1 persona",95 ,200 ));
            listaPlatos.add(new Plato("Papas fritas simples","Porcion para 1 persona",50 ,140 ));
            listaPlatos.add(new Plato("Milanesa a la pizza con papas fritas","Milanesa con salsa, oregano, aceitunas, y papas fritas",290 ,400 ));
        }
    }


    public List<Plato> list(){
        iniciar();
        System.out.print(this.listaPlatos.isEmpty());
        return  this.listaPlatos;
    }

    public void add(String titulo, String descripcion , Double precio, Integer calorias){
        listaPlatos.add(new Plato(titulo, descripcion, precio, calorias));
    }

    public void add(Plato plato){
        listaPlatos.add(new Plato(plato.getTitulo(),plato.getDescripcion(),plato.getPrecio(),plato.getCalorias()));
    }
}
