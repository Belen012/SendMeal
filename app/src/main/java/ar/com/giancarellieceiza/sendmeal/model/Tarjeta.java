package ar.com.giancarellieceiza.sendmeal.model;
import android.text.BoringLayout;

import java.util.Date;

public class Tarjeta {
    private String numero = "";
    private String ccv = "";
    private Date vencimiento;
    private String tipo = "";
    private Boolean valido = false;         //para validacion

    public Tarjeta (){
        checkValidez();
    }

    public Tarjeta(String numero, String ccv, Date vencimiento, String tipo, Boolean valido) {
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

    public Date getVencimiento() {
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

    public void setVencimiento(int año, int mes) {

        Date vencimiento = new Date((año - 1970)*365);
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
        if (this.ccv.isEmpty()) return this.valido = false;
        if (this.numero.isEmpty()) return this.valido = false;
        if (this.tipo.isEmpty()) return this.valido = false;
        return this.valido = true;
    };
}
