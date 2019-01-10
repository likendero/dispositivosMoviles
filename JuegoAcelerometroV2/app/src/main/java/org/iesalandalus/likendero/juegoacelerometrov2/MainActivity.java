package org.iesalandalus.likendero.juegoacelerometrov2;

import android.app.Service;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager manager;
    private Sensor acSensor;
    private TextView tvResultado;
    private Button btJugar;
    private static final float PUNTUACION = 1.231f;
    private SoundPool sp;
    private int rep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // captura de elementos
        tvResultado = (TextView) findViewById(R.id.tvResultado);
        btJugar = (Button) findViewById(R.id.btJugar);

        // sonido
        sp = new SoundPool(1,AudioManager.STREAM_MUSIC,1);
        rep = sp.load(this,R.raw.grito,1);

        // sensores
        manager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        acSensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        // comporbacion del sensor
        if(acSensor == null){
            Toast.makeText(this, "No dispones del sensor, no se puede jugar", Toast.LENGTH_SHORT).show();
            btJugar.setEnabled(false);
        }

    }

    /**
     * que realiza las acciones del boton para jugar
     */
    public void jugar(View view){
        tvResultado.setText("Dale un buen meneo SIN MIEDO!!!!");
        btJugar.setEnabled(false);
        manager.registerListener(this,acSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    // ACCIONES SENSORES /////////////////////////////////////////////////////////////////////
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int valor = Math.round(sensorEvent.values[2]);
        // comprovacion del valor arrojado
        if(valor >= 15 || valor <= -15 ){
            // formateo
            String resultado = "";
            int puntuacion = (int)(valor * PUNTUACION);
            resultado += "la aceleracion ha sido de: " + valor + '\n';
            resultado += "tu puntuacion es de " + puntuacion;
            tvResultado.setText(resultado);
            // otras acciones
            sp.play(rep,1,1,1,0,0);
            manager.unregisterListener(this);
            btJugar.setEnabled(true);

        }else if(valor >= 13 && valor < 15 || valor <= -13 && valor > -15){
            tvResultado.setText("dale un meneo mas fuerte!!!!");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


}
