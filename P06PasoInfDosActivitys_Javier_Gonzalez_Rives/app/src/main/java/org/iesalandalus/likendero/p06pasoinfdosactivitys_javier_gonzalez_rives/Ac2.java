package org.iesalandalus.likendero.p06pasoinfdosactivitys_javier_gonzalez_rives;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Ac2 extends AppCompatActivity {
    private TextView tvSalida;
    private String dato;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac2);

        tvSalida = (TextView) findViewById(R.id.textView);
        // se captura el valor segun su identificador
        dato = getIntent().getStringExtra("nombre");
        // se establece el texto
        tvSalida.setText(dato);
    }

    /**
     * metodo para volver a la pantalla anterior
     * @param view
     */
    public void volver(View view){
        Intent in = new Intent(this,MainActivity.class);
        startActivity(in);
    }
}
