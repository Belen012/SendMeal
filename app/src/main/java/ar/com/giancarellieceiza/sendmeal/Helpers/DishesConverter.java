package ar.com.giancarellieceiza.sendmeal.Helpers;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ar.com.giancarellieceiza.sendmeal.model.Dish;

public class DishesConverter {
    private static Gson gson = new Gson();

    @TypeConverter
    public static List<Dish> stringToDishList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Dish>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(List<Dish> someObjects) {
        return gson.toJson(someObjects);
    }
}
