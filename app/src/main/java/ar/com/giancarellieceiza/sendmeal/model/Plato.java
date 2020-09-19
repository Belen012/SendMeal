package ar.com.giancarellieceiza.sendmeal.model;

import android.widget.ImageView;

public class Plato {
    private String titulo = "";
    private String descripcion = "";
    private double precio = 0.0;
    private Integer calorias = 0;
    private Boolean valido = false;


    public Plato(){
    }

    public Plato(String titulo, String descripcion, double precio, Integer calorias){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.calorias = calorias;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
        checkValidez();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        checkValidez();
    }

    public void setPrecio(double precio) {
        this.precio = precio;
        checkValidez();
    }

    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
        checkValidez();
    }


    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public double getCalorias() {
        return calorias;
    }

    public boolean checkValidez() {
        if(this.titulo.isEmpty()) return this.valido = false;
        if(this.precio == 0.0) return this.valido = false;
        //if(this.calorias == 0) return this.valido = false;       consideramos que no son campos obligatorios
        //if(this.descripcion.isEmpty()) return this.valido = false;
        return this.valido = true;
    }
}


