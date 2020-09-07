package ar.com.giancarellieceiza.sendmeal.model;
import android.text.BoringLayout;

import java.util.Date;

public class Tarjeta {
    private String numero = "";
    private String ccv = "";
    private Date vencimiento;
    private boolean esCredito;
    private Boolean valido = false;         //para validacion

    public Tarjeta (){
    }

    public Tarjeta(String numero, String ccv, Date vencimiento, boolean esCredito, Boolean valido) {
        this.numero = numero;
        this.ccv = ccv;
        this.vencimiento = vencimiento;
        this.esCredito = esCredito;
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

    public boolean isEsCredito() {
        return esCredito;
    }

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

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
        checkValidez();
    }

    public void setEsCredito(boolean esCredito) {
        this.esCredito = esCredito;
        checkValidez();
    }

    public void setValido(Boolean valido) {
        this.valido = valido;
        checkValidez();
    }


    public void checkValidez(){
        if(!this.ccv.isEmpty() && !this.numero.isEmpty() ){
           // (tarjeta.vencimiento.) hay que validar la fecha tmb
            this.valido = true;
        }
        else this.valido = false;
    }
}
