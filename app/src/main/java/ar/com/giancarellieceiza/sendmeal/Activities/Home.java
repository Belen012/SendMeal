package ar.com.giancarellieceiza.sendmeal.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import ar.com.giancarellieceiza.sendmeal.Database.AppRepository;
import ar.com.giancarellieceiza.sendmeal.Helpers.Callback;
import ar.com.giancarellieceiza.sendmeal.R;


public class Home extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        AppRepository repository = new AppRepository(this.getApplication(), new Callback() {
            @Override
            public void onCallback() {
                Log.i("info","El callback se ejecutó correctamente");
            }
        });
        repository.buscarTodos();
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getTitle().toString()) {
            case "Registrarme":
                startActivity(new Intent(this, Register.class));
                return true;
            case "Crear Item":
                startActivity(new Intent(this, NewDish.class));
                return true;
            case "Lista de Items":
                startActivity(new Intent(this, Dishes.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    };
};