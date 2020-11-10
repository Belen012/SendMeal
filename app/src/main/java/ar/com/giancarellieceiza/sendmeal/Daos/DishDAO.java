package ar.com.giancarellieceiza.sendmeal.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ar.com.giancarellieceiza.sendmeal.model.Dish;

@Dao
public interface DishDAO {
    @Insert
    void insertar(Dish plato);

    @Delete
    void borrar(Dish plato);

    @Update
    void actualizar(Dish plato);

    @Query("SELECT * FROM dish WHERE id = :id LIMIT 1")
    Dish buscar(String id);

    @Query("SELECT * FROM dish")
    List<Dish> buscarTodos();
}