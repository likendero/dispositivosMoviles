package org.iesalandalus.likendero.p08almacenamientodatospreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
        if(preferencias.contains("email")){
            edText.setText(preferencias.getString("email",""));
        }else{
            edText.setHint("introduzca email");
        }
    }

    /**
     * metodo que guarda
     * @param view
     */
    public void guardar(View view){
        // acceso preferences
        SharedPreferences preferencias = getSharedPreferences("dato",MODE_PRIVATE);
        // creacion de un editor
        SharedPreferences.Editor editor = preferencias.edit();
        if(edText.getText().toString().trim().length() > 1){
            // escribir en el campo si hay algo
            editor.putString("email",edText.getText().toString().trim());
            // se muestra si se ha guardado
            if(editor.commit()){
                Toast.makeText(this,"guardado",Toast.LENGTH_LONG).show();
            }

        }
        finish();

    }
}
