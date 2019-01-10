package org.iesalandalus.likendero.giroscopio;

import android.app.Service;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
    private SensorManager manager;
    private Sensor giroSensor;
    private SensorEventListener escucharGiros;
    private ImageView robot;
    private TextView grados;
    private Switch swInterruptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // CAPTURA
        robot = (ImageView) findViewById(R.id.imageView);
        grados = (TextView) findViewById(R.id.tvGrados);
        swInterruptor = (Switch) findViewById(R.id.swInterruptor);

        // SENSORES
        manager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        giroSensor = manager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        // comprobacion del sensor
        if(giroSensor == null){
            Toast.makeText(this, "No existe el sensor", Toast.LENGTH_SHORT).show();
            swInterruptor.setEnabled(false);
        }

        escucharGiros = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                // combersion de los datos en grados
                /*
                obtenemos la incliancion del dispositivo en radianes
                proporcionados direcctamente por el giroscopio
                 */
                float[] rotationMatrix = new float[16];
                SensorManager.getRotationMatrixFromVector(rotationMatrix,event.values);
                float[] remapped = new float[16];
                SensorManager.remapCoordinateSystem(rotationMatrix, SensorManager.AXIS_X, SensorManager.AXIS_Z,remapped);
                float[] orientations = new float[3];
                SensorManager.getOrientation(remapped,orientations);
                // cambiamos la inclinacion de radianes a grados
                for (int i = 0 ; i < 3 ; i++){
                    orientations[i] = (float)(Math.toDegrees(orientations[i]));
                }
                // control de si la rotavion esta activada o no

                    robot.setRotation(-orientations[2]);
                    grados.setText((int)orientations[2] + "º");


                    robot.setRotation(0);
                    grados.setText("0º");



            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

    }

    /**
     *
     */
    public void activar(View view){
        if(swInterruptor.isEnabled()){
            manager.registerListener(escucharGiros,giroSensor,SensorManager.SENSOR_DELAY_GAME);
        }
        else{
            manager.unregisterListener(escucharGiros);
            robot.setRotation(0);
            grados.setText("0º");

        }

    }
}
