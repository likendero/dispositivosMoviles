package org.iesalandalus.likendero.juego1_hlc_coloresynumeros_javiergonzalezrives;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String FILAS = "nFilas";
    public static final String COLUMNAS = "nColumnas";
    public static final String ELEMENTOS = "nElementos";
    public static final String VIBRAR = "vibrar";
    public static final String SONAR = "sonar";
    public static final String NUMEROS = "numeros";
    public static final String NUMCLICKS = "clicks";
    public static final int REQUEST_CODE = -1;

    private SeekBar sbFilas, sbColumnas, sbElementos;
    private CheckBox ckSonar, ckVibrar;
    private RadioButton rbNumeros, rbColores;
    private TextView tvFilas, tvColumnas,tvElementos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // inicializacion de los elementos
        sbFilas = (SeekBar) findViewById(R.id.sbFilas);
        sbColumnas = (SeekBar) findViewById(R.id.sbColumnas);
        sbElementos= (SeekBar) findViewById(R.id.sbNumEle);

        ckSonar = (CheckBox) findViewById(R.id.ckSonar);
        ckVibrar = (CheckBox) findViewById(R.id.ckVibrar);

        rbNumeros = (RadioButton) findViewById(R.id.rbNumeros);
        rbNumeros = (RadioButton) findViewById(R.id.rbColores);

        tvColumnas = (TextView) findViewById(R.id.txtColumnas);
        tvElementos = (TextView) findViewById(R.id.txtElementos);
        tvFilas = (TextView) findViewById(R.id.txtFilas);

        // annadir los listener //////////////////////////////////////////////////////////////////////
        sbFilas.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                actualizarFilas(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbColumnas.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                actualizarColumnas(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbElementos.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                actualizarElementos(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    // ACTUALIZAR  ///////////////////////////////////////
    public void actualizarFilas(int p){
        tvFilas.setText("" + (p+3));
    }
    public void actualizarColumnas(int p){
        tvColumnas.setText("" + (p+3));
    }
    public void actualizarElementos(int p){
        tvElementos.setText("" + (p+3));
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////

    // MENU ////////////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_inicio,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_ayuda:
                ayuda();
                break;
            case R.id.item_about:
                about();
                break;
        }
        return true;
    }
    //////////////////////////////////////////////////////////////////////////////////////

    // CAMBIO DE ACTIVITYS ////////////////////
    public void about(){
        Intent intent = new Intent(this, aboutActivity.class);
        startActivity(intent);
    }
    public void ayuda(){
        Intent intent = new Intent(this, ayudaActivity.class);
        startActivity(intent);
    }
    //////////////////////////////////////////////////////////////////////////////////

    // JUGAR ///////////////////
    public void jugar(View view){
        Intent intent = new Intent(this, juegoActivity.class);

        // Bundle
        Bundle datos = new Bundle();
        int filas,colummas,elementos, vibrar, sonar, numeros;
        filas = sbFilas.getProgress() + 3;
        colummas = sbColumnas.getProgress() + 3;
        elementos = sbElementos.getProgress() + 2;
        sonar = ckSonar.isChecked() ? 1 : 0;
        vibrar = ckVibrar.isChecked() ? 1 : 0;
        numeros = rbNumeros.isChecked() ? 1 : 0;

        datos.putInt(FILAS,filas);
        datos.putInt(COLUMNAS,colummas);
        datos.putInt(ELEMENTOS,elementos);
        datos.putInt(VIBRAR,vibrar);
        datos.putInt(SONAR,sonar);
        datos.putInt(NUMEROS,numeros);

        intent.putExtras(datos);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){

        }
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){

        }
        super.onActivityResult(requestCode,resultCode,data);
    }
}











