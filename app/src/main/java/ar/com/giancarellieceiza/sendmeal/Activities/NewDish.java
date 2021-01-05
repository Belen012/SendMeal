package ar.com.giancarellieceiza.sendmeal.Activities;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;

import ar.com.giancarellieceiza.sendmeal.R;
import ar.com.giancarellieceiza.sendmeal.Services.DishServices;
import ar.com.giancarellieceiza.sendmeal.model.Dish;
import ar.com.giancarellieceiza.sendmeal.model.Order;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewDish extends AppCompatActivity {

    //PlatosDao platosDao;

    //UI
    private StorageReference platosImagesRef;
    private byte[] imgData;
    private boolean imgLoaded = false;
    private StorageReference storage;
    EditText titulo;
    EditText descripcion;
    EditText precio;
    EditText calorias;
    Button guardar;

    public void openCamera(View v) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent,2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_dish);
        storage = FirebaseStorage.getInstance().getReference();

        titulo = findViewById(R.id.edit_titulo);
        descripcion = findViewById(R.id.edit_descripcion);
        precio = findViewById(R.id.edit_precio);
        calorias = findViewById(R.id.edit_calorias);
        guardar = findViewById(R.id.button_guardar);
        //platosDao = new PlatosDao();

        guardar.setOnClickListener(new OnSave());


    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    void showToast(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            this.imgData = baos.toByteArray(); // Imagen en arreglo de bytes
            this.imgLoaded = true;
        }
    }

    private class OnSave implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Dish platoNuevo = new Dish();

            if (titulo.getText().toString().isEmpty()) {
                showToast("Ingrese un título");
                return;
            }
            if (precio.getText().toString().isEmpty()) {
                showToast("Ingrese el precio del plato");
                return;
            }
            if (!descripcion.getText().toString().isEmpty()) {
                platoNuevo.setDescripcion(descripcion.getText().toString());
            }
            if (!calorias.getText().toString().isEmpty()) {
                platoNuevo.setCalorias(Integer.valueOf(calorias.getText().toString()));
            }

            platoNuevo.setTitulo(titulo.getText().toString());
            platoNuevo.setPrecio(Double.parseDouble(precio.getText().toString()));
            platosImagesRef = storage.child("images/"+platoNuevo.getTitulo()+".jpg");

            Gson gson = new GsonBuilder().setLenient().create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.43.234:3001/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            if (imgLoaded) {
                UploadTask uploadTask = platosImagesRef.putBytes(imgData);
                // Registramos un listener para saber el resultado de la operación
                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }
                        return platosImagesRef.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Uri downloadUri = task.getResult();
                            platoNuevo.setImgUrl(downloadUri.getLastPathSegment());
                            DishServices dishServices = retrofit.create(DishServices.class);
                            Call<Dish> newDishCall = dishServices.createDish(platoNuevo);

                            newDishCall.enqueue(
                                    new retrofit2.Callback<Dish>() {
                                        @Override
                                        public void onResponse(Call<Dish> call, Response<Dish> response) {
                                            if (response.code() == 200 || response.code() == 201) {
                                                showToast(String.format("Su plato %s se registró correctamente!",response.body().getTitulo()));
                                            } else {
                                                showToast(String.format("Tuvimos un problema %s",response.code()));
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Dish> call, Throwable t) {
                                            showToast(String.format("Tuvimos un problema %s",t.getMessage()));
                                        }
                                    }
                            );
                        } else {
                            showToast("Tuvimos un problema al cargar la imagen del plato nuevo.");
                        }
                    }
                });
            } else {
                DishServices dishServices = retrofit.create(DishServices.class);
                Call<Dish> newDishCall = dishServices.createDish(platoNuevo);
                newDishCall.enqueue(
                        new retrofit2.Callback<Dish>() {
                            @Override
                            public void onResponse(Call<Dish> call, Response<Dish> response) {
                                if (response.code() == 200 || response.code() == 201) {
                                    showToast(String.format("Su plato %s se registró correctamente!",response.body().getTitulo()));
                                } else {
                                    showToast(String.format("Tuvimos un problema %s",response.code()));
                                }
                            }

                            @Override
                            public void onFailure(Call<Dish> call, Throwable t) {
                                showToast(String.format("Tuvimos un problema %s",t.getMessage()));
                            }
                        }
                );
            }
        }
    };
}