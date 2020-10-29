package ar.com.giancarellieceiza.sendmeal.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.Executor;

import ar.com.giancarellieceiza.sendmeal.Daos.DishDAO;
import ar.com.giancarellieceiza.sendmeal.Daos.OrderDAO;
import ar.com.giancarellieceiza.sendmeal.model.Dish;

@Database(entities = {Dish.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static Executor databaseWriteExecutor;

    public abstract DishDAO dishDAO();
    public abstract OrderDAO orderDAO();

    static AppDatabase getInstance(final Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "database-name").build();
    }
}