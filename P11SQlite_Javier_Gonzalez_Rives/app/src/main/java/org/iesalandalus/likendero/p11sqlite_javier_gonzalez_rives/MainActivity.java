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


        }else{
            idi = Integer.parseInt(id);
            ContentValues registro = new ContentValues();
            registro.put("codido",idi);
            registro.put("descripcion", desc);
            registro.put("precio",precio);
            dataBase.insert("articulos",null,registro);
        }
        dataBase.close();
        edId.setText("");
        edDesc.setText("");
        edPrecio.setText("");
        //dataBase.insert("articulos",null,);
    }
    public void mostrarOnClick(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase dataBase = admin.getWritableDatabase();
        Cursor fila = dataBase.rawQuery("select * from articulos where id = " + );
        if(fila.moveToFirst()){
            edDesc.setText(fila.get);
        }
    }
}
