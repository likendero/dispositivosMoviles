package javiergonzalezrives.iesalandalus.ejercicio;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.lang.reflect.Array;

public class Tablero implements View.OnClickListener {
    private AppCompatActivity activity;
    private LinearLayout lyContenedor;
    private LinearLayout[] lyLineas;
    private Button botones[][];
    private int[][] nivel;
    private Jugador jugador;
    /**
     * constructor, se le pasa la activty con la que se va a jugar
     * @param activity
     */
    public Tablero(AppCompatActivity activity, int nivel){
        super();
        this.activity = activity;
        this.lyContenedor = new LinearLayout(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        lyContenedor.setLayoutParams(params);
        lyContenedor.setOrientation(LinearLayout.VERTICAL);
        this.nivel = copia(Niveles.getNivel(nivel));
        // creacion de un jugador si el nivel funciona correctamente

        annadirElementos();
        if(this.nivel != null){
            jugador = new Jugador(this.nivel);
            analizador();
            acciones();
        }
    }

    /**
     * metodo que realiza la copia de un array
     */
    @org.jetbrains.annotations.Contract("null -> null")
    private int[][] copia(int[][] nivel){
        if(nivel  != null) {
            int nuevoNivel[][] = new int[nivel.length][nivel[0].length];
            for (int i = 0; i < nivel.length; i++) {
                for (int j = 0; j < nivel[i].length; j++) {
                    nuevoNivel[i][j] = nivel[i][j];
                }
            }
            return nuevoNivel;
        }
        return null;
    }
    /**
     * metodo que annade los elementos necesarios
     */
    public void annadirElementos(){
        if(nivel != null) {
            this.lyLineas = new LinearLayout[nivel.length];
            this.botones = new Button[nivel.length][];
            // recorrido para formar las lineas
            for(int i = 0; i < lyLineas.length; i++){
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lyLineas[i] = new LinearLayout(activity);
                lyLineas[i].setLayoutParams(params);
                lyLineas[i].setOrientation(LinearLayout.HORIZONTAL);
                lyLineas[i].setPadding(60,30,30,30);
                lyLineas[i].setGravity(View.TEXT_ALIGNMENT_CENTER);
                // proceso de annadir botones

                botones[i] = new Button[nivel[i].length];
                // crear botones
                for(int j = 0; j < botones[i].length; j++){
                    botones[i][j] = new Button(activity);
                    botones[i][j].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                    botones[i][j].setBackgroundResource(R.drawable.botoncillo);
                    botones[i][j].setHeight(1);
                    botones[i][j].setWidth(1);
                    botones[i][j].setEnabled(false);
                    botones[i][j].setPadding(60,30,60,30);
                }
                //annadir los botones al layout horizontal
                for(int j = 0; j < botones[i].length;j++){
                    lyLineas[i].addView(botones[i][j]);
                }
                // annadir el layout
                lyContenedor.addView(lyLineas[i]);
            }


        }
    }
    // annadir acciones a los botones

    /**
     * metodo que annade acciones a los bones
     */
    private void acciones(){
        if(nivel != null){
            // recorido por los botones para annadir las acciones
            for(int i = 0 ; i < botones.length; i++){
                for(int j = 0 ; j < botones[i].length; j++){
                    // se annade la propia clase como oyen te das acciones de los botones
                    botones[i][j].setOnClickListener(this);
                }
            }

        }

    }

    // analizador ///////////////////////////////////////////////////

    /**
     * metodo que realiza el analisis del estado de los botones para
     * ir completando el juego
     */
    private void analizador(){
        // recorrido del nivel completando con las losas adecuadas
        for(int i = 0 ; i < nivel.length; i++){
            for(int j = 0 ; j < nivel[i].length; j++){
                switch(nivel[i][j]){
                    case 0:
                        botones[i][j].setBackgroundResource(R.drawable.botoncillo);
                        botones[i][j].setEnabled(false);
                        break;
                    case 1:
                        botones[i][j].setBackgroundResource(R.drawable.pared);
                        break;
                    case 3:
                        botones[i][j].setBackgroundResource(R.drawable.caminada);
                        botones[i][j].setEnabled(false);
                        break;
                    case 2:
                        botones[i][j].setBackgroundResource(R.drawable.jugador);
                }
            }
            victoria();
            habilitar();
        }

    }

    /**
     * metodo que habilita los botones en las posiciones adyacentes al jugador
     * solamente la superior e inferior
     */
    private void habilitar(){
        // abajo
        try{
            if (nivel[jugador.getY() + 1][jugador.getX()] != 1  && nivel[jugador.getY() + 1][jugador.getX()] != 3 ) {
                botones[jugador.getY() + 1][jugador.getX()].setEnabled(true);
            }
        }catch(Exception ex){}
        // derecha
        try{
            if (nivel[jugador.getY()][jugador.getX() + 1] != 1 && nivel[jugador.getY() ][jugador.getX() + 1] != 3) {
                botones[jugador.getY()][jugador.getX() + 1].setEnabled(true);
            }
        }catch(Exception ex){}
        // arriaba
        try{
            if (nivel[jugador.getY() - 1][jugador.getX()] != 1 && nivel[jugador.getY() - 1][jugador.getX()] != 3) {
                botones[jugador.getY() - 1][jugador.getX()].setEnabled(true);
            }
        }catch(Exception ex){}
        // izquierda
        try{
            if (nivel[jugador.getY()][jugador.getX() - 1] != 1 && nivel[jugador.getY()][jugador.getX() - 1] != 3) {
                botones[jugador.getY()][jugador.getX() - 1].setEnabled(true);
            }
        }catch(Exception ex){}

    }
    /**
     * metodo que lanza la accion de victoria una vez se ha terminado el juego
     */
    private void victoria(){
        for(int i = 0; i < nivel.length; i++){
            for(int j = 0 ;j < nivel[i].length; j++){
                if(nivel[i][j] == 0){
                    return;
                }
            }
        }
        // en el caso que se gane la partida se pasa al menu principal
        Intent intent = new Intent(activity, MainActivity.class);
        Toast.makeText(activity, "Has ganado el nivel Felicidades", Toast.LENGTH_SHORT).show();
        activity.startActivity(intent);
    }
    // /////////////////////////////////////////////////////
    public LinearLayout getLyContenedor() {
        return lyContenedor;
    }

    public void setLyContenedor(LinearLayout lyContenedor) {
        this.lyContenedor = lyContenedor;
    }
    // ACCIONES /////////////////////////////////////////////////////////////////////////
    @Override
    public void onClick(View v) {
        // cambio del valor en el que se situava el jugador
        nivel[jugador.getY()][jugador.getX()] = 3;
        int x = 0,y = 0;
        // recorrido de los botones para sacar que boton se ha pulsado y desplazar al jugador
        for(int i = 0; i < botones.length ; i++){
            for(int j = 0; j < botones[i].length ; j++){
                if(botones[i][j] == v) {
                    x = i;
                    y = j;
                }
            }
        }
        nivel[x][y] = 2;
        jugador.setX(y);
        jugador.setY(x);
        analizador();
    }
}
class Jugador{
    private int x,y;

    /**
     * constructor definiendo la posicion del jugador
     * @param x
     * @param y
     */
    public Jugador(int x,int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * metodo construcotor que a partir del nivel saca
     * la posicion del jugador segun donde se encuentr el numero 2
     * @param nivel
     */
    public Jugador(int[][] nivel){
        int xaux = 0, yaux = 0;
        boolean control = true;
        // recorrido del nivel
        for(int i = 0; i < nivel.length && control; i++ ){
            for(int j = 0; j < nivel[i].length && control ; j++){
                // si el numero en esa posicion es 2 se guarda la posicion
               if(nivel[i][j] == 2){
                   xaux = j;
                   yaux = i;
                   control = false;
               }

            }
        }
        this.y = yaux;
        this.x = xaux;
    }

    /**
     * metodo que define la nueva posicion del jugador
     * @param x
     * @param y
     */
    public void cambiarPosiciones(int x, int y){
        this.x = x;
        this.y = y;

    }
    // GETTERS SETTERS //////////////////////////////////////////////////
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
