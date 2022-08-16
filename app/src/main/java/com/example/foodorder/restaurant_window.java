package com.example.foodorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static androidx.core.content.ContextCompat.startActivity;

public class restaurant_window extends AppCompatActivity {

    Files_action read = new Files_action("restaurants.json");
    Acciones cargas = new Acciones();
    String categoria = "5";
    BottomNavigationView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_window);

        LinearLayout container = findViewById(R.id.linearScroll);
        LayoutInflater inflanter = LayoutInflater.from(this);

        ImageButton pizzaButton = findViewById(R.id.imPizza);
        menu = findViewById(R.id.menu_cat);
        menu.setSelectedItemId(R.id.restaurante);
        ImageButton BurguerButton = findViewById(R.id.imBurguer);

        menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.categorias:
                        Intent intent = new Intent(restaurant_window.this,MainWindow.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.fav :
                        Intent intent1 = new Intent(restaurant_window.this,fav_activity.class);
                        startActivity(intent1);
                        finish();
                        break;
                    case R.id.proflie :
                        Intent intent2 = new Intent(restaurant_window.this,MyProfileActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                }

                return true;
            }
        });


        if(read.leer_json(getApplicationContext())) {
            cargas.carga_resturantes("5",inflanter,container,read.getJsonArray(),getApplicationContext());
        }


        pizzaButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int id_pizza = getResources().getIdentifier("com.example.foodorder:drawable/" + "pizza1" , null, null);
                int id_buguer = getResources().getIdentifier("com.example.foodorder:drawable/" + "buger" , null, null);
                pizzaButton.setImageResource(id_pizza);
                BurguerButton.setImageResource(id_buguer);

                categoria = "6";
                container.removeAllViewsInLayout();
                if(read.leer_json(getApplicationContext())) {
                    cargas.carga_resturantes(categoria,inflanter,container,read.getJsonArray(),getApplicationContext());
                }

            }
        });

        BurguerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int id_buguer = getResources().getIdentifier("com.example.foodorder:drawable/" + "buger1" , null, null);
                int id_pizza = getResources().getIdentifier("com.example.foodorder:drawable/" + "pizza" , null, null);
                pizzaButton.setImageResource(id_pizza);
                BurguerButton.setImageResource(id_buguer);

                categoria = "5";
                container.removeAllViewsInLayout();
                if(read.leer_json(getApplicationContext())) {
                    cargas.carga_resturantes(categoria,inflanter,container,read.getJsonArray(),getApplicationContext());
                }

            }
        });
    }
}