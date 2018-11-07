package org.iesalandalus.likendero.p11sqlite_javier_gonzalez_rives;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class listado extends AppCompatActivity {
    private ListView lsRegistros;
    private ArrayList<String> filas;
    private Button btVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        // captura de la lista
        lsRegistros = (ListView) findViewById(R.id.lvRegistros);
        // instanciacion filas
        filas = new ArrayList<String>();
        definicionLista();
    }

    /**
     * metodo que introduce todos los elementos
     * a la lista
     */
    private void definicionLista(){
        // captura de los elementos
        capturarElementos();
        // creacion del adapter
        ArrayAdapter<String> adap = new ArrayAdapter<String>(this,R.layout.list_registros,filas);
        // set adapter
        lsRegistros.setAdapter(adap);
    }

    /**
     * metodo que introduce los elementos en la lista
     * de registros
     */
    private void capturarElementos(){
        // creacion del objeto data base
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,2);
        SQLiteDatabase data = admin.getWritableDatabase();

        // ejecucion del querry
        Cursor cur = data.rawQuery("select * from articulos",null);
        // cadena auxiliar para guardar los campos
        String aux = new String();
        // recorrido del cursor
        while(cur.moveToNext()){
            aux += "id: " + cur.getString(0);
            aux += " descripcion: " + cur.getString(1);
            aux += " precio: " + cur.getString(2);
            aux += " color: " + cur.getString(3);
            filas.add(aux);
            aux = "";
        }

    }
    /**
     * metodo para volver a la actividad
     * anterior
     */
    public void volverOnClick(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
