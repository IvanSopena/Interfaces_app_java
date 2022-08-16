package com.example.foodorder;

//Clase principal del proyecto. Esta clase se va a encargar de
//mostrar el splash de la app con un timer.

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.TimerTask;
import java.util.Timer;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        //Vamos a crear un timer para que la ventana sea visible
        //el tiempo que queremos

        TimerTask AbreSplash = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,LoginClass.class);
                startActivity(intent);
                finish();
            }
        };

        Timer tiempo = new Timer();
        tiempo.schedule(AbreSplash,5000);
    }
}