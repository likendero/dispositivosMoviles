package org.iesalandalus.likendero.p11sqlite_javier_gonzalez_rives;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edId, edDesc,edPrecio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // captura

        edId = (EditText) findViewById(R.id.edId);
        edDesc = (EditText) findViewById(R.id.edDesc);
        edPrecio = (EditText) findViewById(R.id.edPrecio);

    }

    /**
     * metodo que realiza la accion del boton click
     */
    public void RegistrarClick(View view){
        // crear la clase para poder administrar SQLite
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase dataBase = admin.getWritableDatabase();
        // guardado de los valores
        String id = edId.getText().toString().trim();
        String desc = edDesc.getText().toString().trim();
        String precio = edPrecio.getText().toString().trim();
        int idi;
        if(id.length() <= 0 || desc.length() <= 0 || precio.length() <= 0) {
            Toast.makeText(this, "alguno de los campos no es correcto", Toast.LENGTH_SHORT).show();

        }else{
            // guradado
            idi = Integer.parseInt(id);
            // creacion en un objeto que guarda la informacion del registro
            ContentValues registro = new ContentValues();
            // el put pone el valor en el campo indicado
            registro.put("codigo",idi);
            registro.put("descripcion", desc);
            registro.put("precio",precio);
            // una vez cogidos todos los valores hace un insert
            dataBase.insert("articulos",null,registro);
            Toast.makeText(this, "guardado", Toast.LENGTH_SHORT).show();
        }
        // cerrado de la conexion
        dataBase.close();
        // reseteo del texto en lso campos
        edId.setText("");
        edDesc.setText("");
        edPrecio.setText("");
        //dataBase.insert("articulos",null,);
    }
    /**
     * metodo que realiza la accion del boton click
     */
    public void ModificarClick(View view){
        // crear la clase para poder administrar SQLite
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase dataBase = admin.getWritableDatabase();
        // guardado de los valores
        String id = edId.getText().toString().trim();
        String desc = edDesc.getText().toString().trim();
        String precio = edPrecio.getText().toString().trim();
        int idi;
        if(id.length() <= 0 || desc.length() <= 0 || precio.length() <= 0) {
            Toast.makeText(this, "alguno de los campos no es correcto", Toast.LENGTH_SHORT).show();

        }else{
            // guradado
            idi = Integer.parseInt(id);
            // creacion en un objeto que guarda la informacion del registro
            ContentValues registro = new ContentValues();
            // el put pone el valor en el campo indicado
            //registro.put("codigo",idi);
            registro.put("descripcion", desc);
            registro.put("precio",precio);
            // una vez cogidos todos los valores hace un insert
            int numeroTuplas = dataBase.update("articulos",registro,"codigo = " + id,null);
            if(numeroTuplas == 1)
                Toast.makeText(this, "actualizado nº tuplas " + numeroTuplas, Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "no actualizado, no encontrado", Toast.LENGTH_SHORT).show();
        }
        // cerrado de la conexion
        dataBase.close();
        // reseteo del texto en lso campos
        edId.setText("");
        edDesc.setText("");
        edPrecio.setText("");
        //dataBase.insert("articulos",null,);
    }
    public void mostrarOnClick(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase dataBase = admin.getWritableDatabase();
        // caputura del valor
        String id = edId.getText().toString();
        // en el caso que halla un id valido
        if(id.length() <= 0){
            Toast.makeText(this, "id no valido para la busqueda", Toast.LENGTH_SHORT).show();
        }
        else {

            Cursor fila = dataBase.rawQuery("select descripcion,precio from articulos where codigo = " + id,null);
            if (fila.moveToFirst()) {
                edDesc.setText(fila.getString(0));
                edPrecio.setText(fila.getString(1));
            }else{
                Toast.makeText(this, "no existe el articulo", Toast.LENGTH_SHORT).show();
            }

        }
        admin.close();
    }
    public void eliminarOnClick(View view){
        // crear la clase para poder administrar SQLite
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase dataBase = admin.getWritableDatabase();

        String codigo = edId.getText().toString();

        if(codigo.isEmpty()){
            Toast.makeText(this, "codigo vacio", Toast.LENGTH_SHORT).show();
        }
        else{
            int num = dataBase.delete("articulos","codigo = " + codigo,null);
            if(num == 1){
                Toast.makeText(this, "Borrado", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "no borrado nada", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
