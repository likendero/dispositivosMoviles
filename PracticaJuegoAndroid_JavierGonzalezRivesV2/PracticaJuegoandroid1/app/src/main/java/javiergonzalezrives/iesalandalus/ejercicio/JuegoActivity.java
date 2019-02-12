package javiergonzalezrives.iesalandalus.ejercicio;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Toast;

public class JuegoActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout lyJuego;
    private int nivel;
    private Tablero tablero;
    ProgressBar bar;
    Barra barra;
    private int progreso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        // capturar layout
        lyJuego = (LinearLayout) findViewById(R.id.lyJuego);



        // captura valor de nivel
        nivel = getIntent().getIntExtra(MainActivity.NIVEL,1);
        crearTablero();
        // dialogo explicativo inicial
        AlertInfo info = new AlertInfo();
        info.setBarra(barra);
        info.show(getSupportFragmentManager(),"");

    }

    /**
     * metodo que sirve para generar un tablero segun el nivel elegido,
     *
      */
    private void crearTablero(){
        tablero = new Tablero(this,nivel);
        lyJuego.addView(tablero.getLyContenedor());

        Button botonRendirse = new Button(this);
        botonRendirse.setText("RENDIRSE");
        // annadir barra de porgreso
        barraProgrso();
        lyJuego.addView(botonRendirse);
        botonRendirse.setOnClickListener(this);
    }
    // accion en caso de rendirse
    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Manta que se rinde", Toast.LENGTH_SHORT).show();
        barra.cancel(false);
        rendirsePerder();
    }

    /**
     * metodo que realiza las acciones en caso de rendirse o perder
     */
    private void rendirsePerder(){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    /**
     * metodo que prepara y crea una barra de progrso para annadirla a la interfaz
     */
    private void barraProgrso(){
        bar = new ContentLoadingProgressBar(this);
        //bar = new ProgressBar(this);
        bar.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        bar.setIndeterminate(true);
        bar.setVisibility(View.INVISIBLE);
        bar.setMax(100);
        lyJuego.addView(bar);
        barra = new Barra();

        progreso = 0;
    }

    public class Barra extends AsyncTask<Integer,Integer,Integer>{

        @Override
        protected void onPreExecute() {
            // al iniciar el proceso se pasa visible la barra
            bar.setVisibility(View.VISIBLE);

            super.onPreExecute();
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            // bucle que comprueba el progreso
            while(progreso < 100){

                SystemClock.sleep(100);
                progreso++;
                barra.publishProgress(progreso);
                if(this.isCancelled())break;
            }

            return 0;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            bar.setProgress(values[0]);
            Toast.makeText(JuegoActivity.this, ""+values[0], Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Integer integer) {

            super.onPostExecute(integer);
            Toast.makeText(JuegoActivity.this, "Se te ha acabado el tiempo D:", Toast.LENGTH_SHORT).show();
            rendirsePerder();
        }
    }
}
