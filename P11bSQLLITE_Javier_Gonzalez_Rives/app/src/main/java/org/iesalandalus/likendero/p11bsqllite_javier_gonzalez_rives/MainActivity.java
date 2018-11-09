package org.iesalandalus.likendero.p11bsqllite_javier_gonzalez_rives;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // creacion de las variables
    private EditText edId;
    private EditText edVariedad;
    private EditText edKilos;
    private EditText edComentarios;
    private static final int VERSION = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // captura
        capturaComponentes();
    }

    /**
     * metodo para capturar los componentes
     */
    private void capturaComponentes(){
        // textos
        edId = (EditText) findViewById(R.id.edId);
        edKilos = (EditText) findViewById(R.id.edKilos);
        edVariedad = (EditText) findViewById(R.id.edVariedad);
        edComentarios = (EditText) findViewById(R.id.edComentarios);
    }

    /**
     * metodo que al pulsar el boton guarda la informacion de los
     * campos
     * @param view
     */
    public void registrar(View view){
        // creacion del helper
        AdminHelper admin = new AdminHelper(this,"hortalizas",null, VERSION);
        // creacion del objeto para base de datos
        SQLiteDatabase data = admin.getWritableDatabase();

        // Guardado de la informacion
        String id = edId.getText().toString();
        String variedad = edVariedad.getText().toString();
        String kilos = edKilos.getText().toString();
        String comentarios = edComentarios.getText().toString();

        // creacion del registro
        ContentValues cont;
        // comprobacion de los campos
        if(!id.isEmpty() && !variedad.isEmpty()
                && !kilos.isEmpty() && !comentarios.isEmpty()){
            cont = new ContentValues();
            // introduccion de valores
            cont.put("id",id);
            cont.put("variedad",variedad);
            cont.put("kilos",kilos);
            cont.put("comentarios",comentarios);

            // introduccion del registro
            data.insert("patatas",null,cont);
        }
        data.close();
    }

    /**
     * metodo que sirve para buscar un registro
     * segun el id
     * @param view
     */
    public void buscar(View view){
        // creacion del helper
        AdminHelper admin = new AdminHelper(this,"hortalizas",null, VERSION);
        // creacion del objeto para base de datos
        SQLiteDatabase data = admin.getWritableDatabase();
        // captura del valor del id
        String id = edId.getText().toString();
        // variables para almacenar los valores
        String variedad = "";
        String kilos = "";
        String comentarios = "";
        // comprobacion del id
        if(!id.isEmpty()){
            Cursor cur = data.rawQuery("select variedad,kilos,comentarios from patatas where id=" +id,null );
            if(cur.moveToFirst()){
                variedad =  cur.getString(1);
                kilos = cur.getString(2);
                comentarios = cur.getString(3);
            }else{
                Toast.makeText(this, "no hay ningun registro con ese id", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "introduce un id", Toast.LENGTH_SHORT).show();
        }
        edKilos.setText(kilos);
        edVariedad.setText(variedad);
        edComentarios.setText(comentarios);
        // cerrado
        data.close();
    }

    /**
     * metodo que elimina un registro
     */
    public void eliminar(View view){
        // creacion del helper
        AdminHelper admin = new AdminHelper(this,"hortalizas",null, VERSION);
        // creacion del objeto para base de datos
        SQLiteDatabase data = admin.getWritableDatabase();
        String id = edId.getText().toString();
        // comprobacion del campo id
        if(!id.isEmpty()){
            // borrado
            if(data.delete("patatas","where id=" + id,null) > 0){
                // si el id existia
                Toast.makeText(this, "borrado satisfactorio", Toast.LENGTH_SHORT).show();
            }else{
                // si el id no existia
                Toast.makeText(this, "no se ha encontrado ningun registro con id " + id, Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "introduce un id valido", Toast.LENGTH_SHORT).show();
        }
        data.close();
    }

    /**
     * metodo que actualiza los campos de un registro
     * @param view
     */
    public void update(View view){
        AdminHelper admin = new AdminHelper(this,"hortalizas",null,VERSION);
        SQLiteDatabase data = admin.getWritableDatabase();
        // captura de los valores
        String id = edId.getText().toString();
        String variedad = edId.getText().toString();
        String kilos = edKilos.getText().toString();
        String comentarios = edComentarios.getText().toString();

        if(
                !id.isEmpty() &&
                !variedad.isEmpty() &&
                !kilos.isEmpty() &&
                !comentarios.isEmpty()
                ){

        }
    }
}
