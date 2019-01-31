package org.iesalandalus.likendero.juego1_hlc_coloresynumeros_javiergonzalezrives;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
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
    MatrizJuego miMatriz;
    LinearLayout layoutTablero; // el layout que dejamos sin rellenar
    int filas, columnas, sonar,vibrar, esnumero,numClicks, numeroElementos, altura;
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

        //  datos de juego
        cogerDatosJuego();
        if(vibrar == 1){
            // damos permiso en el manifest
            viService = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        }
        if(sonar == 1){
            mp = MediaPlayer.create(this,R.raw.touch);
        }
        // CREAR MATRIZ
        miMatriz = new MatrizJuego(filas,columnas,numeroElementos);
        miMatriz.rellenarMatriz();
        idCeldas = new int[filas][columnas];
        //idCeldas = miMatriz.();
        // numoeros o colores

        if(esnumero == 1){
            tablero = numeros;
        }else{
            tablero = colores;
        }
        valoresCeladas = miMatriz.getMatriz();
        // ponemos la pantalla
        // Grosor de las filas automatico segun el numero que halla
        // quitamos 180 por arriba para los marcadores :·3
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        altura = (dm.heightPixels-180)/filas;

        numClicks = 0; // :3
        iniciarJuego();
    }
    //  METODOS ///////////////////////////////////////////////////////////////////////////

    /**
     * metodo que rescata los datos del juego desde el main activity
     */
    public void cogerDatosJuego(){
        // Recogemos los parametros del juego
        Bundle datos = getIntent().getExtras();
        filas = datos.getInt(MainActivity.FILAS);
        columnas = datos.getInt(MainActivity.COLUMNAS);
        numeroElementos = datos.getInt(MainActivity.ELEMENTOS);
        esnumero = datos.getInt(MainActivity.NUMEROS);
        vibrar = datos.getInt(MainActivity.VIBRAR);
        sonar = datos.getInt(MainActivity.SONAR);
    }

    /**
     * metodo que incia una partida nueva en el juego
     */
    public void iniciarJuego(){
        int indiceBoton = 0;
        int valor = 0;
        for(int i = 0; i < filas; i++){
            // creamos un layout por fila
            LinearLayout lyFila = new LinearLayout(this);
            lyFila.setOrientation(LinearLayout.HORIZONTAL);
            for(int j = 0; j < columnas ; j++){
                valor = valoresCeladas[i][j];
                // creo cada uno de los botones le paso el listener de id
                Celdas celda = new Celdas(this,++indiceBoton,numeroElementos,valor,tablero[valor],i,j);
                celda.setId(indiceBoton);
                idCeldas[i][j] = indiceBoton;
                celda.setLayoutParams(new LinearLayout.LayoutParams(0,altura,1.0f));
                // Listener a los botones
                celda.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Esto se hace porque hay que hacerlo cuando se trabaja con vistas juas juas
                        pulsarCelda(((Celdas)view).fila,((Celdas)view).columna);
                    }
                });
                lyFila.addView(celda);
            }
            layoutTablero.addView(lyFila);
        }
    }
    // --------------------------------------
    public void pulsarCelda(int a, int b){
        crono1.start();
        if(sonar == 1){
            mp.start();
        }
        if(vibrar == 1){
            viService.vibrate(80);
        }
        numClicks++;
        tvClicks.setText(""+numClicks);
        // cambio de filas
        for(int i = maximo(0,-1); i <= minimo(-1,filas+1);i++){
            cambiar(i,columnas-1);
        }
        for(int j = maximo(0,columnas-1); j <= minimo(columnas+1,columnas-1); j++){
            if(j == columnas){
                continue;
            }else{
                cambiar(filas-1,j);
            }
        }
        checkGanar();
    }

    private void checkGanar() {
        int target = valoresCeladas[0][0];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (valoresCeladas[i][j] != target) {
                    return;
                }
            }
        }
        // FIN DEL JUEGO
        Intent i = new Intent();
        i.putExtra(MainActivity.NUMCLICKS, numClicks);
        setResult(RESULT_OK,i);
        finish();
    }

    // METODOS LOGICA JUEGO ////////////////////////////////////////////////////////////////////
    public void cambiar(int f, int c){
        // captura el id de la celda que quiero cambiaar
        int idCelda = idCeldas[f][c];
        // recupera la celda
        Celdas celda = (Celdas) findViewById(idCelda);
        // capturo el nuevo fondo de la celda
        int nuevoValor = celda.getNuevoFondo();
        // actualiza el valor en el array de valores
        celda.setBackgroundResource(tablero[nuevoValor]);
        // pintar sin problemas
        celda.invalidate();
    }
    public int maximo(int a, int b){
        if( a > b ){
            return a;
        }return b;
    }
    public int minimo(int a, int b){
        if(a < b){
            return a;
        }return b;
    }
}
