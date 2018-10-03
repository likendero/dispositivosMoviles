package org.iesalandalus.linuxdiurno.calculadora_gonzalez_rives_javier;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText txtNumero1,txtNumero2;
    private TextView txtRes;
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
    }

    /**
     * metodo que realizara la funcion de sumar
     * @param view
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
     * @param view
     */
    public void restar(View view){

        int num1,num2;
        String valor1,valor2;
        // guardado de la informacion
        valor1 = txtNumero1.getText().toString();
        valor2 = txtNumero2.getText().toString();

    }
}
