package ar.com.giancarellieceiza.sendmeal.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class Order implements Parcelable {
    private String correo = "";
    private List<Dish> platosSeleccionados = new ArrayList<Dish>();
    private String tipoEnvio = "";
    private String direccion = "";


    public List<Dish> getPlatosSeleccionados() {
        return platosSeleccionados;
    }

    public void add(Dish plato){
        this.platosSeleccionados.add(new Dish(plato.getTitulo(),plato.getDescripcion(),plato.getPrecio(),plato.getCalorias()));
    }

    public static final Parcelable.Creator<Order> CREATOR = new Parcelable.Creator<Order>() {

        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    protected Order(Parcel in) {
        //mData = in.readInt ();
        this.correo = in.readString();
        this.tipoEnvio = in.readString();
        this.direccion = in.readString();
        this.platosSeleccionados = in.readArrayList(Dish.class.getClassLoader());
    }

    public Order(){
    }

    public int describeContents() {
        return hashCode();
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void writeToParcel(Parcel out, int flags) {
       // out.writeInt(mData);
        out.writeString(correo);
        out.writeString(tipoEnvio);
        out.writeString(direccion);
        out.writeList(platosSeleccionados);
//        out.writeParcelableList(platosSeleccionados, );
    }


    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setPlatosSeleccionados(List<Dish> platosSeleccionados) {
        this.platosSeleccionados = platosSeleccionados;
    }

    public String getTipoEnvio() {
        return tipoEnvio;
    }

    public void setTipoEnvio(String tipoEnvio) {
        this.tipoEnvio = tipoEnvio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Dish> getPlatos() {
        return this.platosSeleccionados;
    }
}
