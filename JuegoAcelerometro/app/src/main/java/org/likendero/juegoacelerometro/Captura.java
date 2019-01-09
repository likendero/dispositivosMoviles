package org.likendero.juegoacelerometro;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Captura extends AppCompatActivity implements SensorEventListener {
    private int resultado;
    private SensorManager sensorManager;
    private Sensor sensor ;
    private SoundPool sp;
    int rep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captura);

        // captura de los sensores
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // captura del acelerometro
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        // vinculacion con el "listener"
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);

        resultado = 0;

        // sonido
        sp = new SoundPool(1,AudioManager.STREAM_MUSIC,1);
        rep = sp.load(this,R.raw.grito,1);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int aux = Math.round(event.values[2]);
        // en el caso de mayor a 11 se manda el resultado
        if(aux >= 15){
            resultado = aux;
            sp.play(rep,1,1,1,0,0);
            mandarResultado();
            // caso que sea muy flojo
        }else if(aux < 15 && aux >= 11){
            TextView texto = (TextView) findViewById(R.id.tvAgitar);
            texto.setText(R.string.resFlojo);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    // MIS METODOS /////////////////////////////////////////////////

    /**
     * metodo que manda el resultado a la siguiente pantalla
     *
     */
    private void mandarResultado(){
        Intent intent = new Intent(this,resultado.class);
        intent.putExtra("resultado",resultado);
        startActivity(intent);
    }
}
