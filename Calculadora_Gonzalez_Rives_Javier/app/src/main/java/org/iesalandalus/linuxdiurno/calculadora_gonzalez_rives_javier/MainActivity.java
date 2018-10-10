package org.iesalandalus.linuxdiurno.calculadora_gonzalez_rives_javier;

import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText txtNumero1,txtNumero2;
    private TextView txtRes;
    private RadioButton radioEsp,radioGer,radioJap,radioKur;
    private Spinner spOpciones;
    //private Button btnSumar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // captura de textos editables
        txtNumero1 = (EditText) findViewById(R.id.txtEumero1);
        txtNumero2 = (EditText) findViewById(R.id.txtNumero2);
        // captura de texto no editable
        txtRes = (TextView) findViewById(R.id.txtRes);

        //captura de los radios
        radioEsp = (RadioButton) findViewById(R.id.rgEsp);
        radioGer = (RadioButton) findViewById(R.id.rgGer);
        radioJap = (RadioButton) findViewById(R.id.rdJapones);
        radioKur = (RadioButton) findViewById(R.id.rdKurdo);

        // captura de espinner
        spOpciones = (Spinner) findViewById(R.id.spOpciones);
        // array con las opciones
        String[] opciones = {"sumar","restar","multiplicar","dividir"};
        // adapter que transforma el array a objeto
        ArrayAdapter<String> adap = new ArrayAdapter<String>(this,R.layout.spinner_opciones,opciones);
        spOpciones.setAdapter(adap);

    }

    /**
     * metodo que realizara la funcion de sumar
     *
     */
    public void sumar(View view){
        int num1,num2;
        String valor1,valor2;
        // captura del valor del elemento
        valor1 = txtNumero1.getText().toString();
        valor2 = txtNumero2.getText().toString();
        // paso de cadena a enteros

        num1 = Integer.parseInt(valor1);
        num2 = Integer.parseInt(valor2);

        txtRes.setText("Resultado: " + (num1+num2));
    }

    /**
     * metodo que resta dos numeros
     *
     */
    public void restar(View view){

        int num1,num2;
        String valor1,valor2;
        // guardado de la informacion
        valor1 = txtNumero1.getText().toString();
        valor2 = txtNumero2.getText().toString();
        // paso de cadena a enteros
        num1 = Integer.parseInt(valor1);
        num2 = Integer.parseInt(valor2);

        txtRes.setText( R.string.resutadoEsp + (num1-num2));
    }
    public void dividir(View view){
        int num1,num2;
        String valor1,valor2;
        // guardado de la informacion
        valor1 = txtNumero1.getText().toString();
        valor2 = txtNumero2.getText().toString();
        // paso de cadena a enteros
        num1 = Integer.parseInt(valor1);
        num2 = Integer.parseInt(valor2);
        // seteo resultado
        txtRes.setText(R.string.resutadoEsp + " " +(num1/num2));
    }
    /**
     * metodo que define el idioma de la aplicacion en castellano
     * @param view
     */
    public void idioma(View view){
        TextView titulo = (TextView) findViewById(R.id.titulo);
        Button btnSumar = (Button) findViewById(R.id.suma);
        Button btnRestar = (Button) findViewById(R.id.resta);
        // comprobacion que radio esta pulsado
        // caso que se seleccione el castellano
        if(radioEsp.isChecked()) {

            titulo.setText(R.string.tituloEsp);
            txtNumero1.setHint(R.string.numero1Esp);
            txtNumero2.setHint(R.string.numero2Esp);
            btnSumar.setText(R.string.sumarES);
            btnRestar.setText(R.string.restarEsp);
        }
        // caso que se seleccione el aleman
        if(radioGer.isChecked()){

            titulo.setText(R.string.tituloGer);
            txtNumero1.setHint(R.string.numero1Ger);
            txtNumero2.setHint(R.string.numero2Ger);
            btnSumar.setText(R.string.sumarGer);
            btnRestar.setText(R.string.restarGer);
        }
        // caso japones
        if(radioJap.isChecked()){
            titulo.setText(R.string.tituloJap);
            txtNumero1.setHint(R.string.numero1Jap);
            txtNumero2.setHint(R.string.numero2Jap);
            btnSumar.setText(R.string.sumarJap);
            btnRestar.setText(R.string.restarJap);
        }
        // caso kurdo
        if(radioKur.isChecked()){
            titulo.setText(R.string.tituloKur);
            txtNumero1.setHint(R.string.numero1Kur);
            txtNumero2.setHint(R.string.numero2Kur);
            btnSumar.setText(R.string.sumarKur);
            btnRestar.setText(R.string.restarKur);
        }
    }
    public void operacion(View view){
        String seleccion =  spOpciones.getSelectedItem().toString();
        String num1,num2;
        num1 = txtNumero1.getText().toString().trim();
        num2 = txtNumero2.getText().toString().trim();
        // control para que halla numeros
        if(num1.length() > 0 && num2.length() > 0) {
            switch (seleccion) {
                case "sumar":
                    sumar(view);
                    break;
                case "restar":
                    restar(view);
                    break;

            }
        }

    }
   /* Spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener){
        public onContextItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {

        }

    }
    */
}
