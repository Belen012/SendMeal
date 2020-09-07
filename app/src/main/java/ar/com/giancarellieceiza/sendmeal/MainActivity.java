package ar.com.giancarellieceiza.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import ar.com.giancarellieceiza.sendmeal.model.CuentaBancaria;
import ar.com.giancarellieceiza.sendmeal.model.Tarjeta;
import ar.com.giancarellieceiza.sendmeal.model.Usuario;

public class MainActivity extends AppCompatActivity {

    //intanciamos las clases para validar datos
    Tarjeta tarjeta = new Tarjeta();
    CuentaBancaria cuentaBancaria = new CuentaBancaria();
    Usuario usuario = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListMonth();
        setListYear();
        setRealizarCarga();
        setOnEditNombre();
    };

    void setOnEditNombre(){
        EditText nombre = findViewById(R.id.txt_nombre);
        nombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                usuario.setNombre(s.toString());
                checkValidez();
            }



        });
    }

    void showToast(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    };

    void setRealizarCarga(){
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
    }

    void setListYear(){
        //Le seteo la lista de años para el spinner de años de vencimiento de tarjetas
        Spinner añoVencimientoSpinner = (Spinner) findViewById(R.id.añoVencimiento);
        ArrayAdapter<CharSequence> añoVencimientoAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.years,
                android.R.layout.simple_spinner_item
        );
        añoVencimientoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        añoVencimientoSpinner.setAdapter(añoVencimientoAdapter);
    }

    void setListMonth(){
        //Le seteo la lista de meses para el spinner de meses de vencimiento de tarjetas
        Spinner mesVencimientoSpinner = (Spinner) findViewById(R.id.mesVencimiento);
        ArrayAdapter<CharSequence> mesVencimientoAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.months,
                android.R.layout.simple_spinner_item
        );
        mesVencimientoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mesVencimientoSpinner.setAdapter(mesVencimientoAdapter);
    }

    void checkValidez(){
        Button registrar = findViewById(R.id.button_registrar);
        if(this.usuario.getValido() && this.tarjeta.getValido()){
           registrar.setEnabled(true);
        }
        else registrar.setEnabled(false);
    }
};