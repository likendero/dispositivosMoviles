package org.iesalandalus.likendero.p05dosactivityjavier_gonzalez_rives;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void siguiente(View view){
        // creamos el objeto ontermedio intent
        Intent anteriorActivity = new Intent(this,Actividad2.class);
        // iniciar el activity que queramos
        startActivity(anteriorActivity);
    }
}
