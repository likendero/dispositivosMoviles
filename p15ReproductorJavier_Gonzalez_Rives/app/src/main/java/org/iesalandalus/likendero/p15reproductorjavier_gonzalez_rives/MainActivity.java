package org.iesalandalus.likendero.p15reproductorjavier_gonzalez_rives;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageButton ibPlay;
    private ImageButton ibRepete;
    private MediaPlayer media;
    private ImageView portada;
    private int repetir = 2, posicion = 0;
    // array para contener las canciones
    MediaPlayer canciones[] = new MediaPlayer[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // captura de los elementos
        ibPlay = (ImageButton) findViewById(R.id.ibPlay);
        ibRepete = (ImageButton) findViewById(R.id.ibRepete);
        // imagen
        portada = (ImageView) findViewById(R.id.imPortada);
        // captura canciones
        canciones[0] = MediaPlayer.create(this,R.raw.race);
        canciones[1] = MediaPlayer.create(this,R.raw.sound);
        canciones[2] = MediaPlayer.create(this,R.raw.tea);
    }

    //metodo del boton play pause
    public void PlayPauseOnClick(View view){
        // metodo para saber que se esta reproduciendo .isplaying
        if(canciones[posicion].isPlaying()){
            canciones[posicion].pause();
            //recordar usar el backgroundResource
            ibPlay.setBackgroundResource(R.drawable.reproducir);
            Toast.makeText(this, "Pausa", Toast.LENGTH_SHORT).show();
        }
        // ci estaba en pauza
        else{
            canciones[posicion].start();
            //recordar usar el backgroundResource
            ibPlay.setBackgroundResource(R.drawable.pausa);
            Toast.makeText(this, "Pausa", Toast.LENGTH_SHORT).show();
        }
    }

    /************************
     * metodo para parar la cancion
     * @param view
     */
    public void stop(View view){
        canciones[posicion].pause();
        canciones[posicion].seekTo(0);
        ibPlay.setBackgroundResource(R.drawable.reproducir);
        posicion = 0;
    }

    /**
     * meto para pasar de cancion
     * @param view
     */
    public void siguiente(View view){
        int posAux = posicion;
        // control de la posicion de la cancion
        if(posicion < (canciones.length - 1)){
            posAux++;
        }
        else{
            posAux = 0;
        }

        //en el caso que se este preproducciendo
        if(canciones[posicion].isPlaying()){
            // parada
            canciones[posicion].pause();
            // puesta en marcha
            canciones[posAux].start();
        }else{
            // solo parada
            canciones[posicion].pause();
        }
        posicion = posAux;
        Toast.makeText(this, "pista numero " + posicion, Toast.LENGTH_SHORT).show();
        cambioPortada();
    }

    /**
     * metodo para ir a la pista anterior
     * @param view
     */
    public void anterior(View view){
        int posAux = posicion;
        // control de la posicion de la cancion
        if(posicion > 0){
            posAux++;
        }
        else{
            posAux = canciones.length - 1;
        }

        //en el caso que se este preproducciendo
        if(canciones[posicion].isPlaying()){
            // parada
            canciones[posicion].pause();
            // puesta en marcha
            canciones[posAux].start();
        }else{
            // solo parada
            canciones[posicion].pause();
        }
        posicion = posAux;
        Toast.makeText(this, "pista numero " + posicion, Toast.LENGTH_SHORT).show();
        cambioPortada();
    }
    /**
     * clase que sirve para cambiar la
     * portada de la pista
     */
    private void cambioPortada(){
        switch (posicion){
            case 0:
                portada.setImageResource(R.drawable.portada1);
                break;
            case 1:
                portada.setImageResource(R.drawable.portada2);
                break;
            case 2:
                portada.setImageResource(R.drawable.portada3);
                break;

        }
    }

    /**
     *
     */
    public void repetir(View view){
        if(repetir == 1){ // no repetir
            ibRepete.setBackgroundResource(R.drawable.no_repetir);
            canciones[posicion].setLooping(false);
            repetir = 2;
            Toast.makeText(this, "No repetir", Toast.LENGTH_SHORT).show();
        }else{
            ibRepete.setBackgroundResource(R.drawable.repetir);
            canciones[posicion].setLooping(true);
            repetir = 1;
            Toast.makeText(this, "se repite cual ajo", Toast.LENGTH_SHORT).show();
        }


    }

}
