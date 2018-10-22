package org.iesalandalus.likendero.p09agenda_javier_gonzalez_rives;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edNomnbre, edDatos;
    @Override 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // captura
        edNomnbre = (EditText) findViewById(R.id.editText);
        edDatos = (EditText) findViewById(R.id.edDatos);

    }
    public void guardar(View view){
        SharedPreferences sha = getSharedPreferences("agenda", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sha.edit();
        String nombre = edNomnbre.getText().toString();
        String datos = edDatos.getText().toString();

        editor.putString(nombre,datos);

        if(editor.commit())
            Toast.makeText(this,"guardado",Toast.LENGTH_LONG).show();

    }
    public void buscar(View view){
        SharedPreferences sha = getSharedPreferences("agenda", Context.MODE_PRIVATE);
        String datos = sha.getString(edNomnbre.getText().toString(),"");
        // si no hubiese contenido muestra un mensaje
        if(datos.equals("")) {
            Toast.makeText(this, "no encontrado", Toast.LENGTH_SHORT).show();
            // si lo hay lo muestra en el bloque de texto
        }else{
            edDatos.setText(datos);
        }
    }
}
