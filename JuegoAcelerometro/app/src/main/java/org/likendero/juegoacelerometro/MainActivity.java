package org.likendero.juegoacelerometro;

import android.content.Intent;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    private ListView lvVista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepararLista();
    }
    private void prepararLista(){
        lvVista = (ListView) findViewById(R.id.lvPunt);
        Puntuaciones puntuaciones = new Puntuaciones(this);
        // rescate de los valores
        ArrayList<String> nombres = puntuaciones.rescatarNombres();
        ArrayList<Integer> resultado = puntuaciones.rescatarPuntuaciones();
        ArrayList<String> conjunto = new ArrayList<>();
        // formado de la lista
        for(int i = 0; i < nombres.size();i++){
            conjunto.add( nombres.get(i) + " " + resultado.get(i).toString());
        }
        // creacion del adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.formatolista,conjunto);
        lvVista.setAdapter(adapter);
    }
    /**
     * metodo que realiza las acciones para jugar
     *
     */
    public void onJugar(View view){
        Intent intent = new Intent(this,Captura.class);
        startActivity(intent);
    }
}
