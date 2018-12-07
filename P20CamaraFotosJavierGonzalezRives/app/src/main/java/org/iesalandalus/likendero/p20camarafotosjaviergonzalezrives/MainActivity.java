package org.iesalandalus.likendero.p20camarafotosjaviergonzalezrives;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.security.Permission;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ImageView ivImagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // captura
        ivImagen = (ImageView) findViewById(R.id.imFoto);
        // preguntar por los permisos necesarios
        if(
                ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                &&
                ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                ){
            // creacion de la cadena con permisos
            String[] cadenaPermisos =
                    {
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA
                    };
            ActivityCompat.requestPermissions(MainActivity.this,cadenaPermisos,1000);
        }
    }


    /**
     * metodo que inicia la aplicacion de camara
     */
    static final int RESPUESTA_HACER_FOTO = 1;
    public void hacerFotClick(View view){
        /*
        * se cierra de forma momentanea el actual activity
        * para mostrar para mostrar lo que se ve ne la cam
        * */
        Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(fotoIntent.resolveActivity(getPackageManager()) != null ){
            // crear fichero foto
            File ficheroFoto = null;
            // recuperar el nombre y la ruta del fichero
            try {
                // creacion del objeto fichero
                ficheroFoto = createImageFile();
            }catch(IOException io){
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
            }
            // se vuelve a comprobar si el fichero es nulo
            if(ficheroFoto != null){
                Uri fotoUri = FileProvider.getUriForFile(this,"org.iesalandalus.likendero.p20camarafotosjaviergonzalezrives.fileprovider",ficheroFoto);
                fotoIntent.putExtra(MediaStore.EXTRA_OUTPUT,fotoUri);
                // llamada camara de fotos
                startActivityForResult(fotoIntent,RESPUESTA_HACER_FOTO);
            }
        }
    }




    // CODIGO DE ANDROID DEVELOPERS
    String mCurrentPhotoPath;

    /**
     * metodo que crea el nombre de las fotos que se toman desde la camara
     * @return
     * @throws IOException
     */
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "otof02p_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESPUESTA_HACER_FOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ivImagen.setImageBitmap(imageBitmap);
        }
    }

}
