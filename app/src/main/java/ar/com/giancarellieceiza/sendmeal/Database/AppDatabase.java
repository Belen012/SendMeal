package ar.com.giancarellieceiza.sendmeal.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ar.com.giancarellieceiza.sendmeal.Daos.DishDAO;
import ar.com.giancarellieceiza.sendmeal.model.Dish;

@Database(entities = {Dish.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DishDAO dishDAO();

    static AppDatabase getInstance(final Context context) {

    }
}