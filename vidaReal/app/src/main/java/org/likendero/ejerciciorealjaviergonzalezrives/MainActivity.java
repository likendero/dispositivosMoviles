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

import java.security.Permission;

public class MainActivity extends AppCompatActivity {
    private final int GRABAR_VIDEO = 1;
    private final int ALTA_CALIDAD = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // pedir permisos
        pedirPermisos();
    }
    private void pedirPermisos(){

        if(
                ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED

                ){
            String[] permisos = {Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO};
            ActivityCompat.requestPermissions(MainActivity.this,permisos,1000);

        }

    }
    /**
     * accion para grabar la aplicacion
     */
    public void grabarOnClick(View view){

        guardarVideo(null);
    }

    private void guardarVideo(Uri uri){
        // creacion del intent para inciciar la aplicacion de grabacion
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        // inicio de la activity para grabar
        if(uri != null){
            // definicion del directorio de salida
            intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        }
        // definicion de la calidad de grabacion
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,ALTA_CALIDAD);
        // inicio de la activity
        startActivityForResult(intent,GRABAR_VIDEO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GRABAR_VIDEO){
        Uri data = da

        }
    }
}
