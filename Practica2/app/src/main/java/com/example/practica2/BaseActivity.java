package com.example.practica2;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class BaseActivity extends AppCompatActivity {
    //para poner aplicacion en pantalla completa
    protected void setModoInmersivo(){
      getWindow().getDecorView().setSystemUiVisibility(
              View.SYSTEM_UI_FLAG_LAYOUT_STABLE
              | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
              | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
              | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
              | View.SYSTEM_UI_FLAG_FULLSCREEN
      );
      //Si es una api mayor que 18 KITKAT añadimos mas
        // funcionalidades a las anteriores
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            getWindow().getDecorView().setSystemUiVisibility(
                    getWindow().getDecorView().getSystemUiVisibility()
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }
    }
}
