package org.iesalandalus.likendero.p18actionbarmenuoverflowjaviergonzalezrives;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    // crear un metodo para mostrar y ocultar el menu overflow
    // Tiene un nombre en especifico y devolver verdadero
    /*
    en el metodo onCreateOptionsMenu creamos un objeto de la clase
    menuInflator mediante el metodoo inflate vinculamos el identificador del archivo de
    recursos R.menu.overflow y eel objeto de la clase meni que llega como parametro
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.overflow,menu);
        return true;
    }
    /*
    Metodo para asignar la fincionalidad a cada una de las
    opciones
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // primero capturamos el identificador del item que ha sido pulsado
        int id = item.getItemId();
        // caso selecionar opcion 1
        if(id == R.id.itOpcion1){
            Toast.makeText(this, "opcion 1 pulsada", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.itOpcion2){
            Toast.makeText(this, "opcion 2 pulsada", Toast.LENGTH_SHORT).show();
        }else{
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
