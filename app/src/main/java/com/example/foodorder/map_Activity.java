package com.example.foodorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.jetbrains.annotations.NotNull;

import java.util.Timer;
import java.util.TimerTask;

public class map_Activity extends AppCompatActivity implements OnMapReadyCallback {
    private String MAPVIEW_BUNDLE_KEY = "MAPVIEW_BUNDLE_KEY";
    private MapView mMapView;
    int value=0;
    int avance_barra=0;
    EditText inputMensaje;
    Timer tiempo = new Timer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Bundle b = getIntent().getExtras();
    //Variable pasada
        if (b != null)
            value = b.getInt("key");
        ImageView back = findViewById(R.id.return_w);
        ImageView llamar= findViewById(R.id.imageView13);
        ImageView Mensaje= findViewById(R.id.imageView11);
        SeekBar barra= findViewById(R.id.seekBar);

        //Tiempo
        TimerTask AbreSplash = new TimerTask() {
            @Override
            public void run() {
                avance_barra=avance_barra+1;
                barra.setProgress(avance_barra);

            }
        };
        TimerTask AbreSplash2 = new TimerTask() {
            @Override
            public void run() {
                avance_barra=avance_barra+1;
                barra.setProgress(avance_barra);

            }
        };
        TimerTask AbreSplash3 = new TimerTask() {
            @Override
            public void run() {
                avance_barra=avance_barra+1;
                barra.setProgress(avance_barra);

            }
        };
        TimerTask AbreSplash4 = new TimerTask() {
            @Override
            public void run() {
                avance_barra=avance_barra+1;
                barra.setProgress(avance_barra);

            }
        };
        TimerTask AbreSplash5 = new TimerTask() {
            @Override
            public void run() {
                avance_barra=avance_barra+1;
                barra.setProgress(avance_barra);

            }
        };

        tiempo.schedule(AbreSplash, 5000);
        tiempo.schedule(AbreSplash2, 10000);
        tiempo.schedule(AbreSplash3, 15000);
        tiempo.schedule(AbreSplash4, 20000);
        tiempo.schedule(AbreSplash5, 25000);



        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(map_Activity.this, activity_order.class);
                Bundle b = new Bundle();
                b.putInt("key",value);
                intent.putExtras(b);
                startActivity(intent);
                finish();
            }

        });
        llamar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new AlertDialog.Builder(map_Activity.this).setTitle("ATENCION\n").setMessage("Lola te llamara enseguida!! .").show();

            }

        });
        Mensaje.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                 AlertDialog.Builder builder= new AlertDialog.Builder(map_Activity.this);
                 builder.setTitle("Mensaje");
                 builder.setMessage("Escribe tu mensaje...");
                inputMensaje=new EditText(map_Activity.this);
                 builder.setView(inputMensaje);

                 builder.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         new AlertDialog.Builder(map_Activity.this).setTitle("ATENCION\n").setMessage("Mensaje Enviado.").show();

                     }
                 });
                 builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                     }
                 });
                 builder.show();
            }

        });



    }


    @Override
    public void onMapReady(  GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(40.38871844352889, -3.651397473138828))
                .title("Calle Rio Grande 23"));
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(40.3978888223848, -3.7159486019751435))
                .title("").icon(BitmapDescriptorFactory.fromResource(R.drawable.rider)));
        GoogleMap map = null;
        Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(40.3978888223848, -3.7159486019751435),
                        new LatLng(40.399208117945534, -3.7154517335760073),
                        new LatLng(40.39950097048055, -3.7155929099395584),
                        new LatLng(40.39781783052211, -3.712642479969084),
                        new LatLng(40.39554634192195, -3.7070956717353005),
                        new LatLng(40.39234324936306, -3.701623965325159),
                        new LatLng(40.38809401394119, -3.697428990475444),
                        new LatLng(40.38854346497279, -3.696956921620872),
                        new LatLng(40.38779982616445, -3.6953475962039626),
                        new LatLng(40.38515207847835, -3.6921182165673665),
                        new LatLng(40.384825188836935, -3.6905625353889984),
                        new LatLng(40.38484970561508, -3.6903908740175924),
                        new LatLng(40.38617359838295, -3.6893609057891554),
                        new LatLng(40.387775310435686, -3.6876764783971017),
                        new LatLng(40.386124565814676, -3.6857667456402092),
                        new LatLng(40.390096090910106, -3.6801341067401356),
                        new LatLng(40.39249850479834, -3.6771622190951256),
                        new LatLng(40.392261535815166, -3.672141123879917),
                        new LatLng(40.39177125252916, -3.670381594728358),
                        new LatLng(40.39381407606215, -3.669576932049892),
                        new LatLng(40.39311135176466, -3.66672306166498),
                        new LatLng(40.393666994843635, -3.6662402640579006),
                        new LatLng(40.39334014653931, -3.6661437044248517),
                        new LatLng(40.39379773373568, -3.6657360086677624),
                        new LatLng(40.392531190093194, -3.662581730948191),
                        new LatLng(40.38865787024774, -3.654363442100272),
                        new LatLng(40.38845766095192, -3.654004026073633),
                        new LatLng(40.38838411451888, -3.6531671769590655),
                        new LatLng(40.388326911691166, -3.6524698026377282),
                        new LatLng(40.38833099760904, -3.6516329534521237),
                        new LatLng(40.38871844352889, -3.651397473138828)
                     ));
        // Store a data object with the polyline, used here to indicate an arbitrary type.
        polyline1.setTag("A");
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(40.38871844352889, -3.651397473138828), 12.0f));

    }

}