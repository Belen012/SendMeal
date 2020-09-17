package ar.com.giancarellieceiza.sendmeal.model;

public class Plato {
    private String titulo = "";
    private String descripcion = "";
    private double precio = 0.0;
    private Integer calorias = 0;

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
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
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
}


