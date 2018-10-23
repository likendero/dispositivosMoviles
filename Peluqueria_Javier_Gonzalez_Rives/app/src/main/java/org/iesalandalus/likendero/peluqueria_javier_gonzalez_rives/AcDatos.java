package org.iesalandalus.likendero.peluqueria_javier_gonzalez_rives;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;

public class AcDatos extends AppCompatActivity {
    private RadioButton rdPelo[];
    private ListView lvDias;
    private String diasSemana[] = {"lunes","martes","miercoles","jueves","viernes","sabado","domingo"};
    private EditText edNumero,edComentarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac_datos);
    }
}
