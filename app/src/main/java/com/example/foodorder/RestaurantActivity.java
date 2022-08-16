package com.example.foodorder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RestaurantActivity extends AppCompatActivity {

    int value = 0;



    Acciones cargas = new Acciones();
    Files_action read = new Files_action("comments.json");
    Files_action res_info = new Files_action("restaurants.json");
    ArrayList<Integer> total_comments = new ArrayList<Integer>();
    Utiles fc = new Utiles();
    EditText inputMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        LinearLayout container = findViewById(R.id.linearScroll);
        LayoutInflater inflanter = LayoutInflater.from(this);
        ImageView back = findViewById(R.id.return_window2);
        ImageButton btn_validar = findViewById(R.id.img_valorar);
        TextView Restaurant_name = findViewById(R.id.restaurant_name2);
        TextView comentarios = findViewById(R.id.count_comment);
        TextView direccion = findViewById(R.id.direccion_res);
        TextView nota = findViewById(R.id.puntuacion);
        ImageView Mensaje= findViewById(R.id.img_valorar);
        ImageView res_photo= findViewById(R.id.img_menu2);



        Bundle b = getIntent().getExtras();

        if (b != null)
            value = b.getInt("key");


        if (read.leer_json(getApplicationContext())) {

                cargas.carga_comments(value, inflanter, container, read.getJsonArray(), getApplicationContext(), Restaurant_name);
                total_comments = cargas.getPrecios();
                String count = String.valueOf(total_comments.size());
                comentarios.setText(count);
            if (res_info.leer_json(getApplicationContext())) {

                JSONArray jsonArray  = res_info.getJsonArray();

                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);

                        if (jsonObj.getString("id").equals(cargas.getId())) {
                            direccion.setText(jsonObj.getString("direction"));
                            nota.setText(jsonObj.getString("rating"));
                            String ruta = "com.example.foodorder:drawable/" + jsonObj.getString("photo");
                            int id = getResources().getIdentifier(ruta, null, null);

                            res_photo.setImageResource(id);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(RestaurantActivity.this, restaurant_window.class);
                startActivity(intent);
                finish();
            }

        });

        Mensaje.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(RestaurantActivity.this);
                builder.setTitle("Valoración");
                builder.setMessage("Escribe una valoración...");
                inputMensaje=new EditText(RestaurantActivity.this);
                builder.setView(inputMensaje);

                builder.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new AlertDialog.Builder(RestaurantActivity.this).setTitle("ATENCION\n").setMessage("Mensaje Enviado.").show();

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
}