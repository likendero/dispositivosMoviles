package org.iesalandalus.likendero.p05dosactivityjavier_gonzalez_rives;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Actividad2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void anterior(View view){
        // creamos el objeto ontermedio intent
        Intent siguienteActivity = new Intent(this,MainActivity.class);
        // iniciar el activity que queramos
        startActivity(siguienteActivity);
    }
}
