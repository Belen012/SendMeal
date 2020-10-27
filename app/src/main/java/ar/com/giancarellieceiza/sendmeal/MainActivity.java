package ar.com.giancarellieceiza.sendmeal;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
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

import java.util.List;

import ar.com.giancarellieceiza.sendmeal.model.CuentaBancaria;
import ar.com.giancarellieceiza.sendmeal.model.Tarjeta;
import ar.com.giancarellieceiza.sendmeal.model.Usuario;

interface EditCallback {
    void onEdit (String text);
}

public class MainActivity extends AppCompatActivity implements AppRepository.OnResultCallback {

    //Instanciamos las entidades
    Tarjeta tarjeta = new Tarjeta();
    CuentaBancaria cuentaBancaria = new CuentaBancaria();
    Usuario usuario = new Usuario();
    String passwordRepeat = "";
    Boolean aceptaTerminos = false;
    int montoInicial = 0;
    Boolean realizarCargaEstado = false;
    String mesV = "1";
    String añoV = "2000";


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
    Switch realizarCarga;
    SeekBar creditoInicial;
    Spinner mesVencimientoSpinner;
    Spinner añoVencimientoSpinner;
    Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppRepository repository = new AppRepository(this.getApplication(),this);
        repository.buscarTodos();

        terminos = findViewById(R.id.terminosCondiciones);
        registrar = findViewById(R.id.button_registrar);
        nombreUsuario = findViewById(R.id.edit_nombre);
        passwordUsuario = findViewById(R.id.edit_password);
        passwordCheck = findViewById(R.id.edit_passwordCheck);
        emailUsuario = findViewById(R.id.edit_email);
        aliasTarjeta = findViewById(R.id.edit_aliasCBU);
        cbuTarjeta = findViewById(R.id.edit_numCBU);
        ccvTarjeta = findViewById(R.id.edit_numCCV);
        realizarCarga = findViewById(R.id.realizarCarga);
        creditoInicial = findViewById(R.id.creditoInicial);
        mesVencimientoSpinner = findViewById(R.id.mesVencimiento);
        añoVencimientoSpinner = findViewById(R.id.añoVencimiento);

        //barra con boton atras
        toolbar = findViewById(R.id.toolbar0);
        setSupportActionBar(toolbar);
        ActionBar barraSuperior = getSupportActionBar();
        barraSuperior.setDisplayHomeAsUpEnabled(true);

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

        añoVencimientoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                añoV = parent.getItemAtPosition(position).toString();
                tarjeta.setVencimiento(Integer.parseInt(añoV), Integer.parseInt(mesV));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        mesVencimientoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mesV = parent.getItemAtPosition(position).toString();
                tarjeta.setVencimiento(Integer.parseInt(añoV), Integer.parseInt(mesV));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
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
                if(montoInicial == 0 && realizarCargaEstado){
                    showToast("El valor de la carga inicial debe ser distinto de cero");
                    return;
                }
                showToast("¡Ya estás registrado!");
            }
        });

        creditoInicial.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                montoInicial = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    };

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


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
        realizarCarga.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                realizarCargaEstado = isChecked;
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

        ArrayAdapter<CharSequence> añoVencimientoAdapter = ArrayAdapter.createFromResource(
                this, R.array.years, android.R.layout.simple_spinner_item);
        añoVencimientoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        añoVencimientoSpinner.setAdapter(añoVencimientoAdapter);
    }

    void setListMonth(){
        //Le seteo la lista de meses para el spinner de meses de vencimiento de tarjetas

        ArrayAdapter<CharSequence> mesVencimientoAdapter = ArrayAdapter.createFromResource(
                this, R.array.months, android.R.layout.simple_spinner_item);
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

    @Override
    public void onResult(List result) {
        Toast.makeText(MainActivity.this, "Exito!", Toast.LENGTH_LONG).show();
    }
};