package ar.com.giancarellieceiza.sendmeal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;


public class HomeActivity extends AppCompatActivity {
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.main_menu);

        final Intent mainActivity = new Intent(this, MainActivity.class);
        final Intent platoNuevoActivity = new Intent(this, PlatoNuevoActivity.class);
        final Intent listaPlatosActivity = new Intent(this, ListaPlatosActivity.class);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getTitle().toString().compareTo("Registrarme")==0){
                    startActivity(mainActivity); //no entra aca, habilita igual platoNuevoActivity
                   // showToast(""+ item.getItemId() +item.getTitle().toString());
                }
                if(item.getTitle().toString().compareTo("Crear Item")==0){
                    startActivity(platoNuevoActivity);
                    //showToast(""+ item.getItemId() +item.getTitle().toString());
                }
                if(item.getTitle().toString().compareTo("Lista de Items")==0){
                    startActivity(listaPlatosActivity);
                    //showToast(""+ item.getItemId() +item.getTitle().toString());
                }

                Log.i("item", item.getTitle().toString());

                return false;
            }
        });

    }
    void showToast(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    };

}