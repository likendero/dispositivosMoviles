package org.likendero.finalhlc_javiergonzalezrives;

import android.graphics.Color;
import android.icu.lang.UCharacter;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements DialogoNumeroBotones.ComenzarListener , View.OnClickListener {
    private Button[] botones;
    private LinearLayout layout;
    private TextView txTiempo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // captura de eleemntos
        layout = (LinearLayout) findViewById(R.id.lyBotones);
        crearDialogo();
    }

    /**
     * metodo que crea y muestra el dialogo
     */
    private void crearDialogo(){
        DialogoNumeroBotones dialogo = new DialogoNumeroBotones();
        dialogo.setComenzar(this);
        dialogo.setCancelable(false);
        dialogo.show(getSupportFragmentManager(),"Dialogo");


    }
    // acciones ///////////////////////////////////////////////////
    @Override
    public void onComenzar(int numero, boolean color) {
        if(numero < 3 || numero > 7){
            crearDialogo();
        }
        // en el caso que el numero de botones sea correcto se comienza
        else{
            botones = new Button[numero];
            layout.removeAllViewsInLayout();
            for(int i = 0; i < numero; i++){
                botones[i] = new Button(this);
                botones[i].setText("boton " + i);
                botones[i].setBackgroundColor(color? Color.RED:Color.BLUE);
                botones[i].setOnClickListener(this);
                layout.addView(botones[i]);
                }
            txTiempo = (TextView) findViewById(R.id.txTIempo);
            txTiempo.setText("tiempo: 0");
            Juego juego = new Juego();
            juego.execute();
            }

        }

    @Override
    public void onClick(View v) {
        // se desabilitan los botones pulsados
        for(Button bt: botones){
            if(bt == v){

                bt.setEnabled(false);
            }

        }
    }

    public class Juego extends AsyncTask<Void,Integer,Integer>{


        @Override
        protected Integer doInBackground(Void... voids) {
            boolean condicion = true;
            int contador = 0;
            while(condicion){
                condicion = false;
                // recorrido de los botones
                for(Button bt: botones){
                    // en ecaso que queden activos se sigue
                    if(bt.isEnabled()){
                        condicion = true;
                    }

                }
                contador++;
                publishProgress(contador);
                try {
                    Thread.sleep(500);
                }catch(InterruptedException in){

                }
            }
            return contador;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            Toast.makeText(MainActivity.this, "tiempo final " + integer, Toast.LENGTH_SHORT).show();
            crearDialogo();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            txTiempo.setText("tiempo " + values[0]);
        }
    }

}


