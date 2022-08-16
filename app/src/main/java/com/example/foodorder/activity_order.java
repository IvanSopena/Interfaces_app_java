package com.example.foodorder;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;


public class activity_order extends AppCompatActivity {

    int value = 0;


    Acciones cargas = new Acciones();
    Files_action read = new Files_action("menus.json");
    Utiles fc = new Utiles();
    int cesta = 0;
    int total = 0;
   // String precio_ini = "";
    int precio_origen = 0;
    int pos = 0;
    ArrayList<Integer> total_platos = new ArrayList<Integer>();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        LinearLayout container = findViewById(R.id.linearScroll);
        LayoutInflater inflanter = LayoutInflater.from(this);
        ImageView back = findViewById(R.id.return_window);
        TextView precio = findViewById(R.id.precio);
        TextView cantidad = findViewById(R.id.cantidad);
        TextView Restaurant_name = findViewById(R.id.restaurant_name);
        ImageButton down = findViewById(R.id.imbdown);
        ImageButton up = findViewById(R.id.imbup);
        HorizontalScrollView scrollView = findViewById(R.id.scrollPrincipal);
        ImageButton btn_order = findViewById(R.id.button_order);



        cantidad.setText("0");


        Bundle b = getIntent().getExtras();

        if (b != null)
            value = b.getInt("key");

        if (read.leer_json(getApplicationContext())) {
            cargas.carga_menu(value, inflanter, container, read.getJsonArray(), getApplicationContext(), Restaurant_name);
            total_platos = cargas.getPrecios();
            precio_origen = total_platos.get(pos);//Integer.parseInt(fc.GetItemList(precio.getText().toString(),1," "));
            //disponibles.setMax(total_platos.size());
            precio.setText("0");

        }

        //Boton Ir a Orden de Pedido
        btn_order.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String cant=cantidad.getText().toString();

                if(cant.equals("0")){
                    new AlertDialog.Builder(activity_order.this).setTitle("Error\n").setMessage("Debes seleccionar una cantidad minima.").show();

                }else{

                Intent intent = new Intent(activity_order.this,map_Activity.class);
                Bundle b = new Bundle();
                b.putInt("key",value);
                intent.putExtras(b);

                startActivity(intent);
                finish();}
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity_order.this, MainWindow.class);
                startActivity(intent);
                finish();
            }

        });

        up.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                total = total + total_platos.get(pos);
                precio.setText(String.valueOf(total) + " €");
                cesta = cesta + 1;
                cantidad.setText(String.valueOf(cesta));
            }

        });

        down.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (cesta > 0) {
                    total = total - total_platos.get(pos);
                    precio.setText(String.valueOf(total) + " €");
                    cesta = cesta - 1;
                    cantidad.setText(String.valueOf(cesta));
                    if (cesta == 0) {
                        precio.setText("0");
                    }
                }
            }

        });




    }

}



// +