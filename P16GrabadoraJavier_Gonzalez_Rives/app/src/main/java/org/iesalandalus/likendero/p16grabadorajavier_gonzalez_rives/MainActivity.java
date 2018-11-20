 package org.iesalandalus.likendero.p16grabadorajavier_gonzalez_rives;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

 public class MainActivity extends AppCompatActivity {
    private ImageButton ibGrabar, ibRepro;
    private MediaRecorder mr;
    private MediaPlayer mp;
    private String nombreGrabacion = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // pedir permisos necesarios
        // captura elementos
        ibGrabar = (ImageButton) findViewById(R.id.ibGrabar);
        ibRepro = (ImageButton) findViewById(R.id.ibRepro);
        // COSAS NAZIS CON LOS PERMISOS
        if(ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED
                &&       ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO},1000);
        }
    }

     /**
      *
      * @param view
      */
    public void grabar(View view){
        if(mr == null){
            // componemos el nombre completo con su ruta
            nombreGrabacion = Environment.getDataDirectory().getAbsolutePath()+"/grabacion.mp3";
            mr = new MediaRecorder();
            // conectar con el micro
            mr.setAudioSource(MediaRecorder.AudioSource.MIC);
            // formato de salida
            mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            // Codificador de salida
            mr.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            //
            mr.setOutputFile(nombreGrabacion);
            // empezar a grabar audio
            try{
                mr.prepare();
                // inicio de la grabacion
                mr.start();
            }catch (IOException io){}
            ibGrabar.setBackgroundResource(R.drawable.rec);
            Toast.makeText(this, "GRABANDO", Toast.LENGTH_SHORT).show();
        }else{
            // EXISTE Y ESTA CONFIGURADO SE QUIERE PARAAAAARRRR METAAAAALLLLL
            mr.stop();
            mr.release();

            mr = null;
            ibGrabar.setBackgroundResource(R.drawable.stop_rec);
            Toast.makeText(this, "Grabacion Finalizada pene", Toast.LENGTH_SHORT).show();
        }
    }

     /**
      *
      */
    public void play(View view){
        if(mr == null) {
            if(mp == null)
                mp = new MediaPlayer();
            // control del fichero
            if(nombreGrabacion != null){
                try{
                    if(!mp.isPlaying()) {
                        mp.setDataSource(nombreGrabacion);
                        mp.prepare();
                        mp.start();
                    }else{
                        mp.stop();
                    }
                }catch(IOException io){}
            }
        }
    }
}
