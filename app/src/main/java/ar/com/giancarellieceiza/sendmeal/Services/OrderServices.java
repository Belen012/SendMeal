package ar.com.giancarellieceiza.sendmeal.Services;

import ar.com.giancarellieceiza.sendmeal.model.Order;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OrderServices {
    @POST("pedidos")
    Call<Order> createOrder(@Body Order order);
}