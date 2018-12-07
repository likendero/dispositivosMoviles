package org.iesalandalus.likendero.p19botonesaccionjaviergonzalezrives;

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
    // mostrar y guardar menu contextual
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_acciones,menu);
        return true;
    }
    // agregar acciones a los botones
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.itCompartir:
                Toast.makeText(this, "COMPÂRTIR WEBON", Toast.LENGTH_SHORT).show();
                break;
            case R.id.itBuscar:
                Toast.makeText(this, "BUSCANDO WEBON", Toast.LENGTH_SHORT).show();
                break;
            case R.id.itOpcion1:
                Toast.makeText(this, "OPCION1!!!!!!!!!!!!!!!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.itSalir:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
