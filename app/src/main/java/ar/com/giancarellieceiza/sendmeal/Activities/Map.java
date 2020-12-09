package ar.com.giancarellieceiza.sendmeal.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import ar.com.giancarellieceiza.sendmeal.R;

public class Map extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap map;
    private Map self = this;
    private Button okButton;
    private LatLng destiny;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String permissions[] = {Manifest.permission.ACCESS_FINE_LOCATION};
            requestPermissions(permissions, 22);
        } else {
            map.setMyLocationEnabled(true);
        }

        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                self.destiny = latLng;
                PolylineOptions polylineOptions = new PolylineOptions();
                polylineOptions.color(0x66FF0000);
                polylineOptions.add(new LatLng(-31.645550, -60.706899),latLng);
                map.addPolyline(polylineOptions);
            }
        });
    };

    public void onAceptar(View v) {
        Intent intent = new Intent();
        intent.putExtra("Coordenadas", this.destiny);
        self.setResult(20, intent);
        finish();
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 22) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {}
            else {
                map.setMyLocationEnabled(true);
            }
        };
    };


}