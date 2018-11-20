package org.iesalandalus.likendero.practica4javiergonzalezrives;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private ImageButton ibGrabar;
    private ListView listCanciones;
    private MediaPlayer mediaPlayer;
    private MediaRecorder mediaRecorder;
    private File direcctorio;
    private String[] canciones = {"pista1","pista2","pista3","grabacion"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // permisos
        // comprobarlos
        if(ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED
                &&       ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            // pedirlos
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_EXTERNAL_STORAGE},1000);
        }
        // captura
        elementos();


    }

    /**
     * metodo que captura los elementos
     */
    private void elementos() {
        // capturar elementos
        ibGrabar = (ImageButton) findViewById(R.id.ibGrabar);
        // lista
        listCanciones = (ListView) findViewById(R.id.listPistas);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,R.layout.list_layout,canciones);
        listCanciones.setAdapter(adaptador);
        // Creacion de la pista
        mediaPlayer = new MediaPlayer();
        // grabador
        mediaRecorder = null;
        // creacion del direcciorio de guardado
        dirGuardad();
        // accion
        accionLista();

    }

    /**
     * direcctorio de guardado
     */
    private void dirGuardad(){
        try{
            direcctorio =new File(getFilesDir(),"grabacion.mp3");
        }catch(Exception ex){
            System.out.println("excepcion");
        }
    }
    /**
     * metodo que realiza la accion de la lista
     */
    private void accionLista(){
        listCanciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i){
                    case 0:
                        reproducir(R.raw.race);
                        break;
                    case 1:
                        reproducir(R.raw.sound);
                        break;
                    case 2:
                        reproducir(R.raw.tea);
                        break;
                    case 3:
                        reproducir(0);
                        break;
                }
            }
        });

    }

    /**
     *
     */
    private void reproducir(int i){
        // comprobar si se esta grabando
        if(mediaRecorder == null) {
            try {
                // comprobacion del estado del reproductor
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                // creacion del media player dependiendo del directorio
                if (i != 0) {
                    mediaPlayer = MediaPlayer.create(this, i);
                    mediaPlayer.start();
                } else {
                    if(direcctorio.exists()) {
                        // caso que sea la grabacion en el telefono
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setDataSource(direcctorio.getAbsolutePath());
                    }
                }

            } catch (IOException io) {
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
            }
            // en el caso que este grabando
        }else{
            Toast.makeText(this, "no se puede reproducir mientras se graba", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     *  metodo que realiza la grabacion
     */
    public void grabar(View view){
        // comprobacion del reproductor
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
        // comprobacion del estado del grabador
        if(mediaRecorder == null){
            // instanciacion del grabador
            mediaRecorder = new MediaRecorder();


            // definicion de la salida
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            // seteo de la salida
            mediaRecorder.setOutputFile(direcctorio.getAbsolutePath());

            try{
                // preparacion para la grabacion
                mediaRecorder.prepare();
                // inicio de la grabacion
                mediaRecorder.start();
            }catch(IOException io){
                Toast.makeText(this, "Ha habido un herror", Toast.LENGTH_SHORT).show();
            }
            ibGrabar.setBackgroundResource(R.drawable.rec);
        }else{
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
            ibGrabar.setBackgroundResource(R.drawable.stop_rec);
        }

    }
}
