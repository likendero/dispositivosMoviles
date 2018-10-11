package org.iesalandalus.likendero.aplicacion2_gonzalez_rives_javier;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvInfo;
    private ListView lsView;
    private String nombres[] = {"angel","julian","javier","alicia","angel","sergio","raul"};
    private String edades[] = {"22","20","20","33","19","18","20"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // captura del elemento info
        tvInfo = (TextView) findViewById(R.id.titulo);
        // captura del listView
        lsView = (ListView) findViewById(R.id.lsNombres);
        // adaptar el array
        ArrayAdapter<String> nombresAdap = new ArrayAdapter<String>(this,R.layout.list_item_nombres,nombres);
        // se establece en la vista
        lsView.setAdapter(nombresAdap);
        // hacer con el spinner
        lsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // programacion el evento click de los "items"
                // position posicion que ocupa el elemento seleccionado
                tvInfo.setText("la edad de " + lsView.getItemAtPosition(position) + " es la edad " + edades[position]);
            }
        });
    }
}
