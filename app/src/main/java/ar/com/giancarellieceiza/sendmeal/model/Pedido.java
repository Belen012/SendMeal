package ar.com.giancarellieceiza.sendmeal.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class Pedido implements Parcelable {
    private String correo = "";
    private List<Plato> platosSeleccionados = new ArrayList<Plato>();
    private String tipoEnvio = "";
    private String direccion = "";


    public List<Plato> getPlatosSeleccionados() {
        return platosSeleccionados;
    }

    public void add(Plato plato){
        this.platosSeleccionados.add(new Plato(plato.getTitulo(),plato.getDescripcion(),plato.getPrecio(),plato.getCalorias()));
    }

    public static final Parcelable.Creator<Pedido> CREATOR = new Parcelable.Creator<Pedido>() {

        public Pedido createFromParcel(Parcel in) {
            return new Pedido(in);
        }

        public Pedido[] newArray(int size) {
            return new Pedido[size];
        }
    };

    protected Pedido(Parcel in) {
        //mData = in.readInt ();
        this.correo = in.readString();
        this.tipoEnvio = in.readString();
        this.direccion = in.readString();

    }

    public Pedido(){
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
        //out.writeParcelableList(platosSeleccionados, );
    }


    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setPlatosSeleccionados(List<Plato> platosSeleccionados) {
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
}
