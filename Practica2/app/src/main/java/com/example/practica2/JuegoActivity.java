package com.example.practica2;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class JuegoActivity extends BaseActivity implements DialogoNombreNivel.OnDialogNombreNivel, View.OnClickListener {
    private static final String KEY_VELOCIDAD = "velocidad"; // calves bundle
    private static final String KEY_PROGRESO = "progreso";

    private int velocidad, progreso, numFase,contador, botones;
    protected TextView tvNombre, tvProgreso,tvFrase;
    protected Button boton1, boton2, boton3, boton4;
    protected ProgressBar proBar;
    private boolean partidaAcabada;// partida acabada o no
    private ArrayList<Button> buttons;// guardamos los botones
    private Bundle estado;// para guardar el juego en on pause

    private ControlTask controlTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        buttons = new ArrayList<>();
        // pantalla completa
        setModoInmersivo();
        // agarramos todos los elemenstos
        setView();
        // mostramos dialogo de inicio
        mostrarDialogo();
        // pnemos listener a los botones
        ponerlistene();


    }
    // jaja /////////////////////////////////////////////////////
    public void ponerlistene(){
        boton1.setOnClickListener(this);
        boton2.setOnClickListener(this);
        boton3.setOnClickListener(this);
        boton4.setOnClickListener(this);


    }
    public void mostrarDialogo(){
        DialogoNombreNivel miDialogoNombreNivel = DialogoNombreNivel.newInstance();
        miDialogoNombreNivel.setCancelable(false);
        miDialogoNombreNivel.show(getFragmentManager(),"DialogoNombreNivel");
    }

    public void setView(){
        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvFrase = (TextView) findViewById(R.id.tvFase);
        tvProgreso = (TextView) findViewById(R.id.tvProgreso);

        boton1 = (Button) findViewById(R.id.btn1);
        boton2 = (Button) findViewById(R.id.btn2);
        boton3 = (Button) findViewById(R.id.btn3);
        boton4 = (Button) findViewById(R.id.btn4);

        proBar = (ProgressBar) findViewById(R.id.proBar);
        // fijamos su tamaño para que se vea bien alto y ancho
        proBar.setScaleX(5f);
        proBar.setScaleY(8f);

        // metemos botones en el array
        buttons.add(boton1);
        buttons.add(boton2);
        buttons.add(boton3);
        buttons.add(boton4);
        numerarBotones(desordenarBotones());


    }

    // asdkofjaosg
    public void numerarBotones(ArrayList<Integer> arra1y){
        for(int i = 0; i < arra1y.size(); i++){
            buttons.get(i).setEnabled(true);
            buttons.get(i).setText(""+arra1y.get(i));
        }
    }
    public static ArrayList<Integer> desordenarBotones(){
        ArrayList<Integer> array = new ArrayList<>();
        for(int i = 1; i < 5 ; i++){
            array.add(i);
        }
        Collections.shuffle(array);
        return array;
        return array;

    }
    @Override
    public void onAceptarDialogo(String nombre, int velocidad) {
        // estado inicial del juego
        partidaAcabada = false;
        this.velocidad = velocidad;
        progreso = 0;
        contador = 1;
        numFase = 1;
        // modo inmersivo
        setModoInmersivo();
        // si hemos mandado un nombre lo ponemos
        if(!nombre.trim().isEmpty()){
            tvNombre.setText(nombre);
        }
        // indicamos la fase
        tvFrase.setText(getString(R.string.fase, String.valueOf(numFase)));
        // inclinamos no se que
        controlTask = new ControlTask();
        controlTask.execute(this.velocidad,progreso);
    }


    @Override
    public void onClick(View view) {
        Button botonPulsado = (Button) view;

        int valorBotton = Integer.valueOf(botonPulsado.getText().toString());
        if(valorBotton == contador){
            botonPulsado.setEnabled(false);
            contador++;
            if(contador==5)controlTask.cancel(true);
        }
    // cosas a partir de aqui/////////////////////////////////////////////////////////////////////

}
    public class ControlTask extends AsyncTask<Integer,Integer,Integer>{
        // parametros progreso Resultasdo
        @Override
        protected Integer doInBackground(Integer... integers) {// entran paran salen resultado
            while(progreso <= 100){
                SystemClock.sleep(integers[0]);
                controlTask.publishProgress(progreso);
                progreso++;
                if(isCancelled())break;
            }
            return progreso;

        }

        @Override
        protected void onProgressUpdate(Integer... values) {// progreso
            super.onProgressUpdate(values);
            proBar.setProgress(values[0]);
            tvProgreso.setText(values[0]+"/"+proBar.getMax());
        }

        @Override
        protected void onPostExecute(Integer integer) {// resultado
            super.onPostExecute(integer);
            // hemos terminado la barra somos unos lentorros
            partidaAcabada = true;
            new AlertDialog.Builder(JuegoActivity.this)
                    .setTitle(R.string.fin)
                    .setMessage(R.string.perder)
                    .setPositiveButton(R.string.continuar, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            tvNombre.setText(R.string.random_buttons);
                            mostrarDialogo();
                            numerarBotones(desordenarBotones());
                        }
                    })
                    .setNegativeButton(R.string.inicio, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            if(contador==5){
                controlTask=new ControlTask();
                numerarBotones(desordenarBotones());
                tvFrase.setText(getString(R.string.fase,String.valueOf(++numFase)));
                contador = 1;
                progreso = 0;
                proBar.setProgress(0);
                // aumentar la velocidad
                velocidad = velocidad-5 <= 0?1:velocidad-5;
                controlTask.execute(velocidad,progreso);
            }
        }
        }

    @Override
    protected void onPause() {
        super.onPause();
        if(controlTask != null){
            controlTask.cancel(true);
            estado = new Bundle();
            estado.putInt(KEY_VELOCIDAD,velocidad);
            estado.putInt(KEY_PROGRESO, progreso);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(estado != null && !partidaAcabada){
            controlTask = new ControlTask();
            controlTask.execute(estado.getInt(KEY_VELOCIDAD,estado.getInt(KEY_PROGRESO)));
        }
    }
}
