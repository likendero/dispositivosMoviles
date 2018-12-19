package org.likendero.ejerciciorealjaviergonzalezrives;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    private final int GRABAR_VIDEO = 1;
    private final int ALTA_CALIDAD = 1;
    private VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // video view
        videoView = (VideoView) findViewById(R.id.vvVideo);
        // pedir permisos
        pedirPermisos();
    }
    private void pedirPermisos(){

        if(
                ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED

                ){
            String[] permisos = {Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA};
            ActivityCompat.requestPermissions(MainActivity.this,permisos,1000);

        }

    }
    //// BOTTONES /////
    /**
     * accion para grabar la aplicacion
     */
    public void grabarOnClick(View view){
        dispatchTakeVideoIntent();
    }

    /**
     * metodo que inicia la reproduccion del video view
     * @param view
     */
    public void playOnClick(View view){
        // comprovacion del estado del video
        if(videoView.isPlaying()){
            // en el caso que este reproducciendo se para y se vuelve al princio
            videoView.resume();

        }else{
            videoView.start();
        }

    }
    //----------------------------------------------------------
    static final int REQUEST_VIDEO_CAPTURE = 1;

    /**
     * metdo que incia la actividad para usar la camara
     */
    private void dispatchTakeVideoIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       // comprovacion de que activity se trata
        if(requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK){
            // captura de la ruta del video
            Uri ruta = data.getData();
            // captura del id en el video view
            videoView.setVideoURI(ruta);

        }
    }
}
