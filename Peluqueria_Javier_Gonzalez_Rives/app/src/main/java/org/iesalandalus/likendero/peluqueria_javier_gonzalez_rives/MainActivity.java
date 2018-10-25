package org.iesalandalus.likendero.peluqueria_javier_gonzalez_rives;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edNombre,edPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // captura de cuadros de texto
        edNombre = (EditText) findViewById(R.id.edNombre);
        edPass = (EditText) findViewById(R.id.edContrasenna);
    }

    /**
     * metodo que realiza el cambio en tre las activitys
     * @param nombre nombre del usuario a ver
     */
    private void cambioActivity(String nombre,boolean existe){
        // creacion del intent para el cambio
        Intent cambio = new Intent(this,AcDatos.class);
        // traspaso de la cadena entre activitys
        cambio.putExtra("nombre",nombre);
        // traspaso si el usuario es nuevo
        cambio.putExtra("existe",existe);
        // cambio de activity
        startActivity(cambio);
    }
    /**
     * metodo que comprueba el acceso a la informacion,
     * si este es correcto se pasa a la pantalla de la información
     * @param view
     */
    public void acceso(View view){
        SharedPreferences guardado = getSharedPreferences("contrasennas",Context.MODE_PRIVATE);
        String nombre = edNombre.getText().toString();
        String contrasenna = edPass.getText().toString();
        // comprobacion que el usuario existe
        if(guardado.contains(nombre)){
            // comprobacion de la contrasenna
            if(guardado.getString(nombre,"").equals(contrasenna)){
                // si existe se cambia de activity von el nombre del usuario
                cambioActivity(nombre,true);

            }else // caso de contrasenna incorrecta
                Toast.makeText(this, "contrasenna incorrecta", Toast.LENGTH_SHORT).show();
        }else// caso de usuario inexistente
            Toast.makeText(this, "el usuario no existe", Toast.LENGTH_SHORT).show();
    }

    /**
     * metodo que crea un registro y cambia la actividad
     * @param view
     */
    public void registro(View view){
        SharedPreferences guardado = getSharedPreferences("contrasennas",Context.MODE_PRIVATE);
        String nombre = edNombre.getText().toString().trim();
        String contrasenna = edPass.getText().toString().trim();
        // comprobacion de campos
        if(nombre.length() > 0 && contrasenna.length() > 0) {
            // comprobacion si el usuario existe
            if (!guardado.contains(nombre)) {
                // creacion del editor
                SharedPreferences.Editor editor = guardado.edit();
                // guardado de la informacion
                editor.putString(nombre,contrasenna);
                // commit
                editor.commit();
                // cambio de acticity
                cambioActivity(nombre,false);
            }else
                Toast.makeText(this, "el usuario ya existe", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
    }

}
