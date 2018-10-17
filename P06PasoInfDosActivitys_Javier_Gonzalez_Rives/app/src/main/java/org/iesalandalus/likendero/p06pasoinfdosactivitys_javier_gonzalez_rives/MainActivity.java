package org.iesalandalus.likendero.p06pasoinfdosactivitys_javier_gonzalez_rives;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText etNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // captura
        etNombre = (EditText) findViewById(R.id.edNombre);
    }

    public void enviar(View view){
        Intent i = new Intent(this,Ac2.class);
        i.putExtra("nombre",etNombre.getText().toString());
        startActivity(i);
    }
}
