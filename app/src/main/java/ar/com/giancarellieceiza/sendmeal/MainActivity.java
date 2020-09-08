package ar.com.giancarellieceiza.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.lang.reflect.Method;

import ar.com.giancarellieceiza.sendmeal.model.CuentaBancaria;
import ar.com.giancarellieceiza.sendmeal.model.Tarjeta;
import ar.com.giancarellieceiza.sendmeal.model.Usuario;

interface EditCallback {
    void onEdit (String text);
}

public class MainActivity extends AppCompatActivity {

    //Instanciamos las entidades
    Tarjeta tarjeta = new Tarjeta();
    CuentaBancaria cuentaBancaria = new CuentaBancaria();
    Usuario usuario = new Usuario();
    String passwordRepeat = "";
    Boolean aceptaTerminos = false;

    //UI
    CheckBox terminos;
    Button registrar;
    EditText nombreUsuario;
    EditText passwordUsuario;
    EditText passwordCheck;
    EditText emailUsuario;
    EditText aliasTarjeta;
    EditText cbuTarjeta;
    EditText ccvTarjeta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        terminos = findViewById(R.id.terminosCondiciones);
        registrar = findViewById(R.id.button_registrar);
        nombreUsuario = findViewById(R.id.txt_nombre);
        passwordUsuario = findViewById(R.id.txt_password);
        passwordCheck = findViewById(R.id.txt_passwordCheck);
        emailUsuario = findViewById(R.id.txt_email);
        aliasTarjeta = findViewById(R.id.txt_aliasCBU);
        cbuTarjeta = findViewById(R.id.num_CBU);
        ccvTarjeta = findViewById(R.id.num_ccv);

        setListMonth();
        setListYear();
        setRealizarCarga();

        onTextChange(nombreUsuario, new EditCallback() {
            @Override
            public void onEdit(String nombre) {
                usuario.setNombre(nombre);
            }
        });

        onTextChange(passwordUsuario, new EditCallback() {
            @Override
            public void onEdit(String clave) {
                usuario.setClave(clave);
            };
        });

        onTextChange(emailUsuario, new EditCallback() {
            @Override
            public void onEdit(String email) {
                usuario.setEmail(email);
            };
        });

        onTextChange(passwordCheck, new EditCallback() {
            @Override
            public void onEdit(String password) {
                passwordRepeat = password;
            };
        });

        setOnSelectTipoTarjeta();

        onTextChange(ccvTarjeta, new EditCallback() {
            @Override
            public void onEdit(String ccv) {
                tarjeta.setCcv(ccv);
            };
        });

        onTextChange(cbuTarjeta, new EditCallback() {
            @Override
            public void onEdit(String cbu) {
                tarjeta.setNumero(cbu);
            };
        });

        terminos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                aceptaTerminos = isChecked;
                checkValidez();
            };
        });

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!usuario.getValido()) {
                    showToast("Los datos del usuario están incompletos");
                    return;
                };

                if (!tarjeta.getValido()) {
                    showToast("Los datos de la tarjeta están incompletos");
                    return;
                };

                if (passwordRepeat.compareTo(usuario.getClave()) != 0) {
                    showToast("Las contraseñas no coinciden");
                    return;
                };

                showToast("Ya estás registrado!");
            }
        });

    };

    void onTextChange (EditText editText, final EditCallback callback) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                callback.onEdit(s.toString());
                checkValidez();
            }
        });
    };

    void setOnSelectTipoTarjeta(){
        RadioGroup tipoTarjeta = findViewById(R.id.radiogroup_tipoTarjeta);
        tipoTarjeta.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.button_Credito:
                        tarjeta.setTipo("Credio");
                        break;
                    case R.id.button_Debito:
                        tarjeta.setTipo("Debito");
                        break;
                }
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
        this.registrar.setEnabled(true);
        //if (!this.usuario.getValido()) registrar.setEnabled(false);
        //if (!this.tarjeta.getValido()) registrar.setEnabled(false);
        if (!this.aceptaTerminos) registrar.setEnabled(false);
        //if (this.passwordRepeat.compareTo(usuario.getClave()) != 0) registrar.setEnabled(false);
    };

};