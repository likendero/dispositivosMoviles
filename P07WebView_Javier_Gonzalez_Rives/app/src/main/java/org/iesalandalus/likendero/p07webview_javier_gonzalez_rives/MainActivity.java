package org.iesalandalus.likendero.p07webview_javier_gonzalez_rives;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText edBuscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // captura
        edBuscar = (EditText) findViewById(R.id.edUrl);

    }
    public void buscar(View view){
        // creacion del intent para el cambio
        Intent i = new Intent(this,ActivityWeb.class);
        // enviar la info
        i.putExtra("url",edBuscar.getText().toString());
        // inicio de la activity
        startActivity(i);
    }
}
