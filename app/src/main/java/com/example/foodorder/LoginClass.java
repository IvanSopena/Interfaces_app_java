package com.example.foodorder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;


public class LoginClass extends Activity {

    private TextView msg;
    private TextView verificacion;
    private EditText user;
    private EditText pass;
    private Button button;
    Utiles utl = new Utiles();
    Files_action read = new Files_action("Users.json");
    Errores er = new Errores();
    int posicion_i=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        msg = findViewById(R.id.textView);
        //verificacion = findViewById(R.id.verificacionEmail);
        msg.setVisibility(View.INVISIBLE);
       // verificacion.setVisibility(View.INVISIBLE);
        pass = findViewById(R.id.editTextTextPassword2);
        user = findViewById(R.id.editTextTextEmailAddress2);
        button = (Button) findViewById(R.id.delete_fav);
        //user.setText("1@1.es");
        //pass.setText("1");

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean usuario_encontrado = false;
                boolean password_validada = false;
                String usuario = user.getText().toString();
                String password = pass.getText().toString();
                if(utl.getMail_validado()){
                    if(read.leer_json(getApplicationContext()))
                    {
                        try{
                            for (int i=0;i<read.getJsonArray().length();i++) {
                                JSONObject jsonObj = read.getJsonArray().getJSONObject(i);
                                if (usuario.equals(jsonObj.getString("Email"))) {
                                    usuario_encontrado = true;
                                    if(password.equals(jsonObj.getString("Pass"))){
                                        password_validada = true;
                                        posicion_i=i;
                                    }
                                }
                            }

                            if(usuario_encontrado){
                                if(password_validada){
                                    //Crear fichero con los datos del usuario
                                    JSONObject jsonObj = read.getJsonArray().getJSONObject(posicion_i);
                                    FileOutputStream fileOutputStream=null;

                                    try{
                                        fileOutputStream= openFileOutput("Posicion_usuario", Context.MODE_PRIVATE);
                                        String posicion=String.valueOf(posicion_i);
                                        fileOutputStream.write(posicion.getBytes());
                                        fileOutputStream.close();


                                    }catch(Exception e){
                                        e.printStackTrace();
                                    }
                                    Intent intent = new Intent(LoginClass.this,MainWindow.class);
                                    startActivity(intent);
                                    finish();
                                }else{
                                    msg.setText("La contraseña del usuario no es correcta.");
                                    msg.setVisibility(View.VISIBLE);
                                }
                            }else{
                                msg.setText("Usuario no encontrado en nuestra base de datos.");
                                msg.setVisibility(View.VISIBLE);
                            }
                        }
                        catch (JSONException e){
                            msg.setText(e.getMessage());
                            msg.setVisibility(View.VISIBLE);
                        }
                    }
                    else{
                        msg.setText(er.getMensaje());
                        msg.setVisibility(View.VISIBLE);
                    }
                }else{
                    msg.setText("Usuario no validado");
                    msg.setVisibility(View.VISIBLE);
                }

            }
        });

        pass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    pass.setText("");
                }
            }
        });

        user.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // Al perder el foco, verificiamos el email.

                    if(!utl.ValidarMail(user.getText().toString()))
                    {
                        msg.setText("El email introducido no es correcto.");
                        msg.setVisibility(View.VISIBLE);
                        utl.setMail_validado(false);
                    }
                    else{
                        utl.setMail_validado(true);
                    }
                }
            }
        });

    }




}


