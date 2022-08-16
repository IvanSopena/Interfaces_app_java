package com.example.foodorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class MyProfileActivity extends AppCompatActivity {

    BottomNavigationView menu;
    private EditText nombre;
    private EditText apellidos;
    private EditText email;
    private EditText direccion;
    private EditText password;
    private EditText tarjeta;

    String campo_nombre=null;
    String campo_apellido=null;
    String campo_email=null;
    String campo_direccion=null;
    String campo_password=null;
    String campo_tarjeta=null;
    String posicion=null;
    int pos=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        menu = findViewById(R.id.menu_cat);
        menu.setSelectedItemId(R.id.proflie);

        ImageButton BtnGuardar = findViewById(R.id.imageButton5);
        ImageButton buttonCerrarSesion = (ImageButton) findViewById(R.id.imageButton8);
        ImageButton buttonEliminar = (ImageButton) findViewById(R.id.imageButton4);
        nombre = findViewById(R.id.name);
        apellidos = findViewById(R.id.surname);
        email = findViewById(R.id.email);
        direccion = findViewById(R.id.direccion);
        password = findViewById(R.id.editTextTextPassword2);
        tarjeta= findViewById(R.id.tarjeta);

        //Carga de la posicion del usuario
        try {
            FileInputStream fileInputStream = getApplicationContext().openFileInput("Posicion_usuario");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            BufferedReader bufferedReader= new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while((line= bufferedReader.readLine())!= null){
                stringBuilder.append(line);
            }
            //Ya tenemos la posicion pasada
            posicion=stringBuilder.toString();
            //Cargamos el objeto
            Files_action read = new Files_action("Users.json");
            read.leer_json(getApplicationContext());

            JSONObject jsonObj = read.getJsonArray().getJSONObject(Integer.parseInt(posicion));
            //Colocamos los datos del Json en su sitio y ponemos su valor en variables globales
            nombre.setText(jsonObj.getString("Nombre"));
            apellidos.setText(jsonObj.getString("Apellido"));
            direccion.setText(jsonObj.getString("Direccion"));
            email.setText(jsonObj.getString("Email"));
            password.setText(jsonObj.getString("Pass"));
            tarjeta.setText(jsonObj.getString("n_tarjeta"));

            campo_nombre=jsonObj.getString("Nombre");
            campo_apellido=jsonObj.getString("Apellido");
            campo_direccion=jsonObj.getString("Direccion");
            campo_email=jsonObj.getString("Email");
            campo_password=jsonObj.getString("Pass");
            campo_tarjeta=jsonObj.getString("n_tarjeta");

        } catch (Exception e) {
            e.printStackTrace();
        }
//Boton guardar
        BtnGuardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mostrarCambio();
                Files_action read = new Files_action("Users.json");
                read.leer_json(getApplicationContext());
                try {
                    JSONObject obj = new JSONObject();
                    obj.put("nombre", "Fernando");
                    obj.put("edad", new Integer(32));



                    try{
                        FileWriter file = new FileWriter("./Users.json");
                        file.write(obj.toString());
                        file.flush();
                        file.close();


                    }catch(Exception ex){
                        System.out.println("Error: "+ex.toString());
                    }
                    finally{
                        System.out.print(obj);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        //Boton Cerrar Sesion
        buttonCerrarSesion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MyProfileActivity.this,LoginClass.class);
                startActivity(intent);
                finish();
            }
        });
//Boton Eliminar
        buttonEliminar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mostrarEliminar();
            }
        });

        menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override

            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.categorias:
                        Intent intent = new Intent(MyProfileActivity.this,MainWindow.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.restaurante :
                        Intent intent1 = new Intent(MyProfileActivity.this,restaurant_window.class);
                        startActivity(intent1);
                        finish();
                        break;
                    case R.id.fav :
                        Intent intent2 = new Intent(MyProfileActivity.this,fav_activity.class);
                        startActivity(intent2);
                        finish();
                        break;
                }

                return true;
            }
        });
    }
    private void mostrarCambio(){
        new AlertDialog.Builder(this).setTitle("Datos Guardados\n").setMessage("Los datos han sido cambiados").show();

    }
    private void mostrarEliminar(){
        new AlertDialog.Builder(this).setTitle("Eliminar Usuario\n").setMessage("¿Estas seguro?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MyProfileActivity.this,LoginClass.class);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();

    }
}