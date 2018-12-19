package org.likendero.juegoacelerometro;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sensor ;
    private TextView texto;
    private int resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // instanciacion del los elementos
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        resultado = 0;
        // captura del texto de salida
        texto = (TextView) findViewById(R.id.tvResultado);

    }
    // metodos para capturar los valores
    @Override
    public void onSensorChanged(SensorEvent event) {
        // captura del valor de
        int ejeZ = Math.abs((int)event.values[2]);

        resultado = (ejeZ > 11?ejeZ:resultado);

        texto.setText("resultado " + resultado);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
