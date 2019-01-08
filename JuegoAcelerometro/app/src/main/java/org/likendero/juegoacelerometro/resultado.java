package org.likendero.juegoacelerometro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class resultado extends AppCompatActivity {

    private int resultado;
    private TextView tvResultado;
    private EditText etNombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        etNombre = (EditText) findViewById(R.id.etNombre);
        // captura del resultado
        resultado = getIntent().getIntExtra("resultado",0);
        // mostrar resultado
        tvResultado = (TextView) findViewById(R.id.tvResultado);
        mostrar();
    }

    /**
     * metodo que prepara los textos para mostrar la informacion
     */
    private void mostrar(){
        tvResultado.setText(resultado * 1000 + " pts");
    }

    /**
     * metodo que realiza el cambio de activity
     */
    private void cambioActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    // BOTONES

    /**
     * caso de cancelar
     * @param view
     */
    public void onCancelar(View view){
        cambioActivity();
    }

    /**
     * caso que se guarde la puntuacion
     * @param view
     */
    public void onGuardar(View view){
        String nombre = etNombre.getText().toString().trim();
        if(nombre.isEmpty()){
            Toast.makeText(this, "introduce un nombre valido", Toast.LENGTH_SHORT).show();
        }else {
            Puntuaciones puntuaciones = new Puntuaciones(this);
            if(puntuaciones.guardarPuntuacion(resultado * 1000, nombre)) {
                Toast.makeText(this, "Guardado correcto", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Error en el guardado", Toast.LENGTH_SHORT).show();
            }
            cambioActivity();
        }
    }
}
