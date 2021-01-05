package ar.com.giancarellieceiza.sendmeal.Services;

import java.util.List;

import ar.com.giancarellieceiza.sendmeal.model.Dish;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DishServices {
    @GET("plato/{id}")
    Call<Dish> getDish(@Path("id") String id);

    @GET("platos")
    Call<List<Dish>> getDishList();

    @POST("platos")
    Call<Dish> createDish(@Body Dish plato);
}