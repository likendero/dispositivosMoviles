package com.example.likendero.p27lectorcodigobarras_javiergonzalezrives;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView zXingScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // CODIGO
        // pedir permisos
        if(ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.CAMERA ) !=
                PackageManager.PERMISSION_GRANTED){
            String[] permisos = {Manifest.permission.CAMERA};
            ActivityCompat.requestPermissions(this,permisos,1);
        }
    }
    // TODO asignar al boton
    public void escanear(View view){
        zXingScannerView = new ZXingScannerView(getApplicationContext());
        setContentView(zXingScannerView);
        // inicial el scanner
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }
    // cuando se ejecuta el escanner :0
    @Override
    public void handleResult(Result result) {
        // opcional
        MediaPlayer pl = MediaPlayer.create(this, R.raw.roadrunner);
        pl.start();
        // creamos un mensanje
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("hola muy buenas, pues escaner y eso");
        if(result.getText().equals("8480000725646")){
            builder.setMessage("pasion floran altamente disfrutable");
            pl = MediaPlayer.create(this,R.raw.pasion);
        }else if(result.getText().contains("http")){// caso web
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(result.getText()));
            startActivity(i);
        }else{
            builder.setMessage("el resultado es " + result.getText() + " lo que es asqueroso :0 ¡BULL SHIT!");
        }
        AlertDialog mensaje = builder.create();
        mensaje.show();
        // OBLIGATORIO
        // para el escaner
        zXingScannerView.resumeCameraPreview(this);
        // apagar la camara
        zXingScannerView.stopCamera();
        // cambia el activitym
        setContentView(R.layout.activity_main);
    }
}
