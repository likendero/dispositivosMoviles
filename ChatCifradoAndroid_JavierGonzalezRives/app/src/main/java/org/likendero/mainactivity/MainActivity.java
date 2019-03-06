package org.likendero.mainactivity;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements DilogoConexion.SucedeDialogListener {
    private BufferedReader reader;
    private PrintWriter writer;
    private EditText etMensaje;
    private EditText etCuadro;
    private CifrarDescifrar cifrarDescifrar;
    private String CLAVE = "chat1998";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // capturar elementos
        etMensaje = (EditText) findViewById(R.id.etMensaje);
        etCuadro = (EditText) findViewById(R.id.etCuadro);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DilogoConexion dialogo = new DilogoConexion();
        dialogo.setSucede(this);
        dialogo.show(getSupportFragmentManager(),"conexion");
        cifrarDescifrar = new CifrarDescifrar();
    }

    @Override
    public void onDialogClick(String ip, int puerto) {
        try {
            Socket socket = new Socket(ip, puerto);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            hiloReceptor hilo = new hiloReceptor();
            hilo.execute();
        }catch(Exception ex){
            Log.d("error",ex.getMessage());
        }
    }

    /**
     * metodo para enviar cuando se pulse el boton
     */
    public void enviar(View view){
        try{
            String enviar = etMensaje.getText().toString().trim();
            writer.println(cifrarDescifrar.cifrarMensajes(enviar,CLAVE));
            writer.flush();
            etCuadro.append( enviar + "\n");
        }catch(Exception ex){
            Log.d("error",ex.getMessage());

        }

    }
    public class hiloReceptor extends AsyncTask<Void,String,Void> {


        @Override
        protected Void doInBackground(Void... voids) {
            while(true){
                try{
                    String entrada = cifrarDescifrar.descifrarMensajes(reader.readLine(),CLAVE);
                    this.publishProgress(entrada.trim());
                }catch(Exception ex){
                    Log.d("error",ex.getMessage());

                }
            }

        }

        @Override
        protected void onProgressUpdate(String... values) {
            if (values[0] != null) {
                etCuadro.append(values[0] + '\n');
            }

        }
    }
}
