package ar.com.giancarellieceiza.sendmeal.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

import ar.com.giancarellieceiza.sendmeal.R;

public class Home extends AppCompatActivity {
    Toolbar toolbar;
    private FirebaseAuth mAuth;
    private AppCompatActivity self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        mAuth = FirebaseAuth.getInstance();
        signInAnonymously();

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            // Error
                            return;
                        }

                        // FCM token
                        String token = task.getResult();

                        // Imprimirlo en un toast y en logs
                        Log.d("[FCM - TOKEN]", token);
                        Toast.makeText(Home.this, token, Toast.LENGTH_SHORT).show();
                    }
                });
    };

    private void signInAnonymously() {
        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Exito
                            Log.d("info", "signInAnonymously:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // Error
                            Log.w("info", "signInAnonymously:failure", task.getException());
                            Toast.makeText(self, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

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

    public void openMap(View v) {
        startActivity(new Intent(this, Map.class));
    };
};