package org.iesalandalus.likendero.p14reproductoraudiojavier_gonzalez_rives;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button play;
    private SoundPool sp;
    private SoundPool.Builder spb;
    int sonidoRep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // captura de elementos
        play = (Button) findViewById(R.id.btSoundPool);
        // padfjasdg
        createStringMusic();
    }

    /**
     * metodo que crea la linea de sonido
     */
    private void createStringMusic(){
        //spb = new SoundPool.Builder();
        sp = new SoundPool(1,AudioManager.STREAM_MUSIC,1);
        sonidoRep = sp.load(this,R.raw.sonidocorto,1);
    }

    /**
     *
     */
    public void AudioPoolonClick(View view){
        sp.play(sonidoRep,1,1,1,0,0);

    }

    /**
     * crear un objeto media player
     * (contexto,sonido R.raw,evento start)
     */
    public void mediaOnclick(View view){
        MediaPlayer mp = MediaPlayer.create(this,R.raw.sonidolargo);

        if(!mp.isPlaying()){
            mp.start();
        }else{
            mp.stop();
        }
    }
}
