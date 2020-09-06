package ar.com.giancarellieceiza.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Le seteo la lista de meses para el spinner de meses de vencimiento de tarjetas
        Spinner mesVencimientoSpinner = (Spinner) findViewById(R.id.mesVencimiento);
        ArrayAdapter<CharSequence> mesVencimientoAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.months,
                android.R.layout.simple_spinner_item
        );
        mesVencimientoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mesVencimientoSpinner.setAdapter(mesVencimientoAdapter);

        //Le seteo la lista de años para el spinner de años de vencimiento de tarjetas
        Spinner añoVencimientoSpinner = (Spinner) findViewById(R.id.añoVencimiento);
        ArrayAdapter<CharSequence> añoVencimientoAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.years,
                android.R.layout.simple_spinner_item
        );
        añoVencimientoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        añoVencimientoSpinner.setAdapter(añoVencimientoAdapter);

        Switch realizarCarga = findViewById(R.id.realizarCarga);
        realizarCarga.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                SeekBar creditoInicial = findViewById(R.id.creditoInicial);

                if (isChecked) {
                    creditoInicial.setVisibility(View.VISIBLE);
                } else {
                    creditoInicial.setVisibility(View.GONE);
                }
            }
        });
    };

    void showToast(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    };

};