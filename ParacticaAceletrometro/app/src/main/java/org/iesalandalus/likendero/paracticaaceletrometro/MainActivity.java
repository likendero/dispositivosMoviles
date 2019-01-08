package org.iesalandalus.likendero.paracticaaceletrometro;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sensor;
    private TextView txx,txy,txz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // captura de etiquetas
        txx = (TextView) findViewById(R.id.txx);
        txy = (TextView) findViewById(R.id.txy);
        txz = (TextView) findViewById(R.id.txz);
        // creacion de los sensores
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        double x = sensorEvent.values[0];
        double y = sensorEvent.values[1];
        double z = sensorEvent.values[2];
        // paso a los cuadros de texto
        txx.setText("x: " + x);
        txy.setText("y: " + y);
        txz.setText("z: " + z);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
