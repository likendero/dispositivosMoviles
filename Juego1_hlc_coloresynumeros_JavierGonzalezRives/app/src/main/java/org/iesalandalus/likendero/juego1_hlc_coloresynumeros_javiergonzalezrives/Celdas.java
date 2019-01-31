package org.iesalandalus.likendero.juego1_hlc_coloresynumeros_javiergonzalezrives;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class Celdas extends android.support.v7.widget.AppCompatButton {
    int idBoton ,numeroElementos, contenidoCelda, fondo, fila, columna;
    // CONSTRUCTORES /////////////////////////////////////////////////////
    public Celdas(Context context) {
        super(context);
    }

    public Celdas(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Celdas(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Celdas(Context contexto, int id, int ne, int cc, int f, int fil,int col){
        super(contexto);
        this.idBoton = id;
        this.numeroElementos = ne;
        this.contenidoCelda = cc;
        this.fondo = f;
        this.fila = fil;
        this.columna = col;
        this.setBackgroundResource(fondo);
    }
    // METODOS /////////////////////////////////////////////////////////////////
    public int getNuevoFondo(){
        contenidoCelda++;
        // si llega al  maximo de elementos se iguala a 1 ?¿?
        //                                               |_̣___|
        if(contenidoCelda == numeroElementos){
            contenidoCelda = 0;
        }
        return contenidoCelda;
    }
}
