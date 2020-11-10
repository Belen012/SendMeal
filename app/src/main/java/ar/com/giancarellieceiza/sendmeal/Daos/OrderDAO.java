package ar.com.giancarellieceiza.sendmeal.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ar.com.giancarellieceiza.sendmeal.model.Order;

@Dao
public interface OrderDAO {
    @Insert
    void insertar(Order order);

    @Delete
    void borrar(Order order);

    @Update
    void actualizar(Order order);

    @Query("SELECT * FROM `order` WHERE id = :id LIMIT 1")
    Order buscar(String id);

    @Query("SELECT * FROM `order`;")
    List<Order> selectAll();
};
