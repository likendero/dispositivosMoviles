package org.iesalandalus.likendero.tareaactivitis_javier_gonzalez_rives;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class Actividad2 extends AppCompatActivity {
    private ListView lvDias;
    private Spinner spMeses;
    private TextView tvSalida;
    private String[] dias = {"lunes","martes","miercose","jueves","viernes","sabado","domingo"};
    private String[] meses = {"enero","febrero","marzo","abril","mayo","junio","julio","agosto","septiembre","octubre","noviembre","diciembre"};
    private String diaActual,mesActual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);
        // captura
        // text view
        tvSalida = (TextView) findViewById(R.id.tvSalida);
        // listView
        lvDias = (ListView) findViewById(R.id.lvDias);

        ArrayAdapter<String> adapDias = new ArrayAdapter<String>(this,R.layout.list_dias,dias);
        lvDias.setAdapter(adapDias);
        //spinner
        spMeses = (Spinner) findViewById(R.id.spMeses);

        ArrayAdapter<String> adapMeses = new ArrayAdapter<String>(this,R.layout.list_dias,meses);
        spMeses.setAdapter(adapMeses);
        // valores por defecto: lunes enero
        diaActual = "lunes";
        mesActual = "enero";
        // annadir acciones
        accionListaSpinner();
    }

    /**
     * metodo para definir la accion del List view
     */
    private void accionListaSpinner(){
        // definir accion lvDias
        lvDias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                diaActual = lvDias.getItemAtPosition(position).toString();
                cambio();
            }
        });
        // definir accion spMeses
        spMeses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mesActual = spMeses.getItemAtPosition(position).toString();
                cambio();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        }

    /**
     * metodo que cambia la vision del texto
     */
    private void cambio(){
        tvSalida.setText("dia: " + diaActual + " mes:" + mesActual);
    }
    }


