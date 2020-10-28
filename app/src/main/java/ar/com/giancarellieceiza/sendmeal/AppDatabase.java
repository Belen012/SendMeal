package ar.com.giancarellieceiza.sendmeal;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import ar.com.giancarellieceiza.sendmeal.dao.DatoDAO;
import ar.com.giancarellieceiza.sendmeal.model.Dato;

@Database(entities = {Dato.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DatoDAO datoDAO();
    /* .... */
        static AppDatabase getInstance(final Context context) {
            return Room.databaseBuilder(context, AppDatabase.class, "database-name").build();
        }
}