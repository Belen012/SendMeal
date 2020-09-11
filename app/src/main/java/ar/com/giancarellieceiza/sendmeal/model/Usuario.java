package ar.com.giancarellieceiza.sendmeal.model;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Usuario {
    private int id;
    private String nombre = "";
    private String clave = "";
    private String email = "";
    private Double credito = null;
    private Tarjeta tarjeta;
    private CuentaBancaria cuentaBancaria;
    private Boolean valido = false;

    public Usuario(){
    }

    public Usuario(int id, String nombre, String clave, String email, Double credito, Tarjeta tarjeta, CuentaBancaria cuentaBancaria, Boolean valido) {
        this.id = id;
        this.nombre = nombre;
        this.clave = clave;
        this.email = email;
        this.credito = credito;
        this.tarjeta = tarjeta;
        this.cuentaBancaria = cuentaBancaria;
        this.valido = valido;                 //para validacion

    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getClave() {
        return clave;
    }

    public String getEmail() {
        return email;
    }

    public Double getCredito() {
        return credito;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }

    public Boolean getValido(){
        return valido;
    }



    public void setNombre(String nombre){
        this.nombre = nombre;
        checkValidez();
    }

    public void setClave(String clave) {
        this.clave = clave;
        checkValidez();
    }

    public void setEmail(String email) {
        this.email = email;
        checkValidez();
    }

    public void setCredito(Double credito) {
        this.credito = credito;
        checkValidez();
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
        checkValidez();
    }

    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
        checkValidez();
    }

    public void setValido(Boolean valido) {
        this.valido = valido;
        checkValidez();
    }

    public boolean checkValidez() {
        if (this.nombre.isEmpty()) return this.valido = false;
        if (this.clave.isEmpty()) return this.valido = false;
        if (this.email.isEmpty()) return this.valido = false;

        Pattern pattern = Pattern.compile("[a-z]+@[a-z]{3}+.[a-z]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(this.email);
        boolean matchFound = matcher.find();
        if (!matchFound) return this.valido = false;

        return this.valido = true;
    }

}
