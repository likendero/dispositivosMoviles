package org.likendero.juegoacelerometro;

import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * clase que realiza el guardado y lectura de los ficheros de puntuacion
 * @author Javier Gonzalez Rives
 */
public class Puntuaciones {
    private static final String NOMBRES = "nombres.dat";
    private static final String PUNTUACIONES = "puntuaciones.dat";
    private AppCompatActivity activity;

    /**
     * constructor principal
     * @param activity
     */
    public Puntuaciones(AppCompatActivity activity){
        super();
        this.activity = activity;
    }

    /**
     * metodo que realiza el guardado de la puntuacion con el nombre (ficheros separados)
     * @param puntuacion puntuacion conseguida
     * @param nombre introducido por el jugador
     * @return false si ocurre un error, true si es todo correcto
     */
    public boolean guardarPuntuacion(int puntuacion,String nombre){
        if(nombre.trim().isEmpty())
            return false;
        try{
            File salida = new File(activity.getFilesDir(),NOMBRES);
            /// nombre
            FileOutputStream flujoSalida = new FileOutputStream(salida,true);

            DataOutputStream formato = new DataOutputStream(flujoSalida);
            formato.writeUTF(nombre);
            // cierres
            formato.close();
            flujoSalida.close();

            /// puntuacion
            salida = new File(activity.getFilesDir(),PUNTUACIONES);
            flujoSalida = new FileOutputStream(salida,true);
            formato = new DataOutputStream(flujoSalida);
            formato.writeInt(puntuacion);
            // cierres
            formato.close();
            flujoSalida.close();
        }catch(Exception ex){
            return false;
        }
        return true;
    }

    /**
     * metodo que rescata la lista de nombres de los jugadores
     * @return
     */
    public ArrayList<String> rescatarNombres(){
        ArrayList<String> lista = new ArrayList<>();
        try{
            FileInputStream flujo;
            flujo = activity.openFileInput(NOMBRES);
            DataInputStream lector = new DataInputStream(flujo);

            // lectura hasta el final
            while(true){
                String cadena = lector.readUTF();
                lista.add(cadena);
            }
        }catch(Exception ex){
            return lista;
        }

    }

    /**
     * metodo que rescata las puntuaciones de los jugadores
     * @return
     */
    public ArrayList<Integer> rescatarPuntuaciones(){
        ArrayList<Integer> lista = new ArrayList<>();
        try{
            FileInputStream flujo;
            flujo = activity.openFileInput(PUNTUACIONES);
            DataInputStream lector = new DataInputStream(flujo);

            // lectura hasta el final
            while(true){
                int punt = lector.readInt();
                lista.add(punt);
            }
        }catch(Exception ex){
            return lista;
        }

    }
}
