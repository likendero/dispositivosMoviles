package org.iesalandalus.likendero.peluqueria_javier_gonzalez_rives;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;

public class AcDatos extends AppCompatActivity {
    private EditText edCometarios,edTelefono;
    private ListView lvDias;
    private RadioButton rdPelo[] = new RadioButton[5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac_datos);

    }
    private void registro(){

    }
}
