package javiergonzalezrives.iesalandalus.p29podometro_javiergonzalezrives;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener andar;
    private EditText etPasosADar;
    private Button bEmpezar;
    private Button bRendirse;
    private TextView tvPasos;
    private TextView tvObjetivo;
    private ImageView ivPersona;
    private int pasosActuales;
    private int objetivo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Variables
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        etPasosADar = (EditText) findViewById(R.id.etPasosADar);
        bEmpezar = (Button) findViewById(R.id.btAceptar);
        bRendirse= (Button) findViewById(R.id.btRendirse);
        tvPasos = (TextView) findViewById(R.id.tvPasos);
        tvObjetivo = (TextView) findViewById(R.id.tvNumObjetivo);
        ivPersona = (ImageView) findViewById(R.id.ivPersona);
        objetivo = 0;
        pasosActuales = 0;

        if(sensor == null){
            Toast.makeText(this, "No tienes el sensor podometro", Toast.LENGTH_SHORT).show();
        }

        andar = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                //Si hemos llegado nos termina y si no suma y sigue
                if(pasosActuales == objetivo){
                    terminar();
                } else {
                    pasosActuales++;
                    tvPasos.setText(pasosActuales+"");
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {}
        };

    }
    public void empezar(View view){
        //Controlamos la excepción de que si no hay nada no lo pone
        if(etPasosADar.getText().toString().length() != 0){
            //Cogemos los pasos que hemos puesto arriba, los pasamos al objetivo y limpiamos arriba
            objetivo = Integer.parseInt(etPasosADar.getText().toString());
            tvObjetivo.setText(objetivo+"");
            etPasosADar.setText("");

            //Activamos el sensor y limitamos los pasos al objetivo
            //Lo segundo se hace dentro del EventListener
            sensorManager.registerListener(andar, sensor, SensorManager.SENSOR_DELAY_GAME);
        } else {
            Toast.makeText(this, "Introduce un objetivo arriba", Toast.LENGTH_LONG).show();
            bEmpezar.setEnabled(true);
            bRendirse.setEnabled(false);
        }

    }
    public void rendirse(View view){
        //reiniciar
        sensorManager.unregisterListener(andar);
        Toast.makeText(this, "¿Ya te has cansado?", Toast.LENGTH_LONG).show();
        bRendirse.setEnabled(false);
        bEmpezar.setEnabled(true);
        tvObjetivo.setTextColor(Color.RED);

    }
    public void terminar(){

        sensorManager.unregisterListener(andar);
        Toast.makeText(this, "FELICICADES POR LA CAMINATA", Toast.LENGTH_LONG).show();

        bEmpezar.setEnabled(true);
        bRendirse.setEnabled(false);
    }


}
