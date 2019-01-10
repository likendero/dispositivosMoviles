package org.iesalandalus.likendero.juego1_hlc_coloresynumeros_javiergonzalezrives;

import android.app.Service;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;

public class juegoActivity extends AppCompatActivity {
    int [] numeros = new int[]{
            R.drawable.ic_1n,
            R.drawable.ic_2n,
            R.drawable.ic_3n,
            R.drawable.ic_4n,
            R.drawable.ic_5n,
            R.drawable.ic_6n
    };
    int [] colores = new int[]{
            R.drawable.ic_1c,
            R.drawable.ic_2c,
            R.drawable.ic_3c,
            R.drawable.ic_4c,
            R.drawable.ic_5c,
            R.drawable.ic_6c
    };
    protected int[] tablero; // variable con las que se juega, se iguala a numeros o colores >:D
    protected int[][] valoresCeladas;// valores que valen los botones
    int[][] idCeldas; // para guardar un id
    LinearLayout layoutTablero; // el layout que dejamos sin rellenar
    int filas, columnas, sonar, esnumero,numClicks;
    Chronometer crono1; // cronometro que lleva el tiempo de juego
    TextView tvClicks;
    Vibrator viService;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);


        // captura de los elementos

        crono1 = (Chronometer) findViewById(R.id.crono1);
        tvClicks = (TextView) findViewById(R.id.tv_clicks);
        layoutTablero = (LinearLayout) findViewById(R.id.layout_tablero);
        viService = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        

    }
}
