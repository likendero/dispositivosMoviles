package org.iesalandalus.likendero.juego1_hlc_coloresynumeros_javiergonzalezrives;

import java.util.Random;

public class MatrizJuego {
    private int filas,columnas,elementos;
    private int[][] matriz;
    public MatrizJuego(int f, int c, int e){
        super();
        matriz = new int[f][c];
        filas = f;
        columnas = c;
        elementos = e;
        rellenarMatriz();
    }

    // METODOS
    public void rellenarMatriz(){
        Random r =  new Random(System.currentTimeMillis());
        for(int i = 0; i < filas; i++){
            for (int j = 0;j < columnas;j++){
                matriz[i][j] = r.nextInt(elementos);

            }
        }

    }
    // GETTERS SETTERS ////////////////////////////////////////////////////////////////////////////
    public int[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }
}
