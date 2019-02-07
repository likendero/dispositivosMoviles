package javiergonzalezrives.iesalandalus.ejercicio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class JuegoActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout lyJuego;
    private int nivel;
    private Tablero tablero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        // capturar layout
        lyJuego = (LinearLayout) findViewById(R.id.lyJuego);
        // captura valor de nivel
        nivel = getIntent().getIntExtra(MainActivity.NIVEL,1);
        crearTablero();
    }

    /**
     * metodo que sirve para generar un tablero segun el nivel elegido,
     *
      */
    private void crearTablero(){
        tablero = new Tablero(this,nivel);
        lyJuego.addView(tablero.getLyContenedor());
        Button botonRendirse = new Button(this);
        botonRendirse.setText("RENDIRSE");
        lyJuego.addView(botonRendirse);
        botonRendirse.setOnClickListener(this);
    }
    // accion en caso de rendirse
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        Toast.makeText(this, "Manta que se rinde", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}
