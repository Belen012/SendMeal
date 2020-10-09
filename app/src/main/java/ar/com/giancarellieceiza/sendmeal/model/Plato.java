package ar.com.giancarellieceiza.sendmeal.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

public class Plato implements Parcelable {
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

    public Integer getCalorias() {
        return calorias;
    }

    public boolean checkValidez() {
        if(this.titulo.isEmpty()) return this.valido = false;
        if(this.precio == 0.0) return this.valido = false;
        //if(this.calorias == 0) return this.valido = false;       consideramos que no son campos obligatorios
        //if(this.descripcion.isEmpty()) return this.valido = false;
        return this.valido = true;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(titulo);
        dest.writeString(descripcion);
        dest.writeDouble(precio);
        if (calorias == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(calorias);
        }
        dest.writeByte((byte) (valido == null ? 0 : valido ? 1 : 2));
    }

    protected Plato(Parcel in) {
        titulo = in.readString();
        descripcion = in.readString();
        precio = in.readDouble();
        if (in.readByte() == 0) {
            calorias = null;
        } else {
            calorias = in.readInt();
        }
        byte tmpValido = in.readByte();
        valido = tmpValido == 0 ? null : tmpValido == 1;
    }

    public static final Creator<Plato> CREATOR = new Creator<Plato>() {
        @Override
        public Plato createFromParcel(Parcel in) {
            return new Plato(in);
        }

        @Override
        public Plato[] newArray(int size) {
            return new Plato[size];
        }
    };
}


