package ar.com.giancarellieceiza.sendmeal.model;

public class CuentaBancaria {
    private String cbu = "";
    private String alias = "";
    private Boolean valido = false;     //para validacion

    public CuentaBancaria(){

    }

    public CuentaBancaria(String cbu, String alias, Boolean valido) {
        this.cbu = cbu;
        this.alias = alias;
        this.valido = valido;
    }

    public String getCbu() {
        return cbu;
    }

    public String getAlias() {
        return alias;
    }

    public Boolean getValido(){
        return valido;
    }



    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setValido(Boolean valido) {
        this.valido = valido;
    }


}
