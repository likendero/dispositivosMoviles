package org.iesalandalus.likendero.p03imagebuttonjavier_gonzalez_rives;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * metodo que muestra un mesaje al pusar el libro
     * @param view
     */
    public void libro(View view){
        Toast.makeText(this,"boton libro pulsado",Toast.LENGTH_LONG).show();
    }
    /**
     * metodo que muestra un mesaje al pusar la mano
     * @param view
     */
    public void mano(View view){
        Toast.makeText(this,"boton mano pulsado",Toast.LENGTH_LONG).show();
    }
}
