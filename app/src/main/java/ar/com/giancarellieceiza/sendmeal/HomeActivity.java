package ar.com.giancarellieceiza.sendmeal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;


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
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getTitle().toString()){
                    case "Registrarme":
                        startActivity(mainActivity);

                    case "Crear Item":
                       // startActivity(platoNuevoActivity);

                    case "Lista de Items": //Lista de Items (actividad a crear en el paso 4)

                    }

                Log.i("item", item.getTitle().toString());

                return false;
            }
        });

    }

}