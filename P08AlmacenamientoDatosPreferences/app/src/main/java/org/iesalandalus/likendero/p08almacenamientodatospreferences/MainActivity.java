package org.iesalandalus.likendero.p08almacenamientodatospreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText edText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // init
        init();
    }

    /**
     * metodo para capturar los elementos de la
     * aplicacion
     */
    private void init(){
        edText = (EditText) findViewById(R.id.edTexto);
        // recuperacion de la clase preferens el valor del EMAIL
        // primero creamos un objeto SharedPreferences
        SharedPreferences preferencias = getSharedPreferences("dato",MODE_PRIVATE);

    }
}
