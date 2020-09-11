package ar.com.giancarellieceiza.sendmeal.model;
import android.os.Build;
import android.text.BoringLayout;

import androidx.annotation.RequiresApi;

import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

public class Tarjeta {
    private String numero = "";
    private String ccv = "";
    private Calendar vencimiento;
    private String tipo = "";
    private Boolean valido = false;         //para validacion

    public Tarjeta (){
        checkValidez();
    }

    public Tarjeta(String numero, String ccv, Calendar vencimiento, String tipo, Boolean valido) {
        this.numero = numero;
        this.ccv = ccv;
        this.vencimiento = vencimiento;
        this.tipo = tipo;
        this.valido = valido;
    }

    public String getNumero() {
        return numero;
    }

    public String getCcv() {
        return ccv;
    }

    public Calendar getVencimiento() {
        return vencimiento;
    }

    public String getTipo() { return tipo; }

    public Boolean getValido(){
        return valido;
    }

    public void setNumero(String numero) {
        this.numero = numero;
        checkValidez();
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
        checkValidez();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setVencimiento(int año, int mes) {

        Calendar vencimiento = new Calendar.Builder().setFields(YEAR, año, MONTH,mes).build();
        this.vencimiento = vencimiento;

        checkValidez();
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
        checkValidez();
    }

    public void setValido(Boolean valido) {
        this.valido = valido;
        checkValidez();
    }


    public boolean checkValidez() {
        Calendar vencimientoMinimo = Calendar.getInstance();
        vencimientoMinimo.add(MONTH, 3);
        if (this.ccv.isEmpty()) return this.valido = false;
        if (this.numero.isEmpty()) return this.valido = false;
        if (this.tipo.isEmpty()) return this.valido = false;
        if (this.vencimiento.compareTo(vencimientoMinimo)<0) return this.valido = false;
        return this.valido = true;
    };
}
