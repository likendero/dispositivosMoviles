package org.iesalandalus.likendero.p10ficherostexto_javier_gonzalez_rives;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText edTexto,edNombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // vincular elementos
        edTexto = (EditText) findViewById(R.id.edTexto);
        edNombre = (EditText) findViewById(R.id.edNombre);
    }

    /**
     * metodo que realiza el guardado
     */
    public void guardar(View view){
        String nombreFichero = edNombre.getText().toString();
        String contenido = edTexto.getText().toString();
        // control para la escritura
        try{
            // recuperamos la ruta de la tarjeta sd con la clase Enviroment
            // para ello creamos un fichero auxiliar
            File sd = Environment.getExternalStorageDirectory();
            Toast.makeText(this, "Ruta Valida" + sd.getAbsolutePath(), Toast.LENGTH_SHORT).show();
            // creamos el fichero con el nombre que el usuario ha elegido
            //File usuario = new File(sd,nombreFichero);
            // fichero para escribir
            OutputStreamWriter escritor = new OutputStreamWriter(openFileOutput(nombreFichero,Context.MODE_PRIVATE));
            // pasar el contenido del fichero
            escritor.write(contenido);
            // limpiamos el buffer
            escritor.flush();
            escritor.close();

            // borrar campos
            edNombre.setText("");
            edTexto.setText("");
        }catch(FileNotFoundException fi){
            Toast.makeText(this, "directorio no encontrado", Toast.LENGTH_SHORT).show();
        }catch(IOException io){
            Toast.makeText(this, "Error en la escritura", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * metodo que sirve para cargar la informacion
     */
    public void cargarClick(View view){

        String nombre = edNombre.getText().toString();
        String salida = null;
        String salidaCompleta = "";
        try {
            // para ello creamos un fichero auxiliar
            File sd = Environment.getExternalStorageDirectory();
            Toast.makeText(this, "Ruta Valida" + sd.getAbsolutePath(), Toast.LENGTH_SHORT).show();
            // creamos el fichero con el nombre que el usuario ha elegido
            //File usuario = new File(sd,nombre);
            // apertura de lectura
            InputStreamReader lector = new InputStreamReader(openFileInput(nombre));
            BufferedReader contenedor = new BufferedReader(lector);
            // recorrido de las lineas del documento
            salida = contenedor.readLine();
            while(salida != null){
                salidaCompleta += salida + '\n';
                salida = contenedor.readLine();
            }
            edTexto.setText(salidaCompleta);
        }catch(Exception ex){
            Toast.makeText(this, "Error en la lectura", Toast.LENGTH_SHORT).show();
        }

    }
}
