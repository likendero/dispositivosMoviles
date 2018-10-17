package org.iesalandalus.likendero.tareaactivitis_javier_gonzalez_rives;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText etNombre,etApellido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // captura de los campos de texto
        etNombre = (EditText) findViewById(R.id.etNombre);
        etApellido = (EditText) findViewById(R.id.etApellido);

    }

    /**
     * metodo que al introducir el nombre y la contraseña
     * pasa a la segunda actividad
     * @param view
     */
    public void intro(View view){
        // captura de los valores de los campos
        String nombre = etNombre.getText().toString();
        String contra = etApellido.getText().toString();
        // se compruevan los valores
        if(nombre.equals("maria") && contra.equals("maria2018") ){
            // se crea el Intent para el cambio
            Intent cambio = new Intent(this,Actividad2.class);
            // cambio
            startActivity(cambio);
        }
    }
}
