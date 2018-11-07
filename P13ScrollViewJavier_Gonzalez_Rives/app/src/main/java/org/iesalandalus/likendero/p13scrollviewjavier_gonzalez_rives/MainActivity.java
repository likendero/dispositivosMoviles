package org.iesalandalus.likendero.p13scrollviewjavier_gonzalez_rives;

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

    /*
        Procedimiento que consulta el id del objeto que ha generado el evento
        sgun sea muestra el mensaje
     */
    public void seleccionFruta(View view){
        // view tiene la propiedad id que identifica cada objeto del activity

        switch (view.getId()){
            // devuelve el identificador del elemento grafico
            case R.id.ibCereza:
                Toast.makeText(this, "Has pulsao unas Seresica", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ibFresa:
                Toast.makeText(this, "Has pulsao unas Fresicas" , Toast.LENGTH_SHORT).show();
                break;
            case R.id.ibKiwi:
                Toast.makeText(this, "Has pulsao uns kivis" , Toast.LENGTH_SHORT).show();
                break;
            case R.id.ibPera:
                Toast.makeText(this, "Has pulsao unas pericas" , Toast.LENGTH_SHORT).show();
                break;
            case R.id.ibpinna:
                Toast.makeText(this, "Has pulsao unas Pinnicas" , Toast.LENGTH_SHORT).show();
                break;
            /*case R.id.ibPlatano:
                Toast.makeText(this, "Has pulsao unos Platanicos" , Toast.LENGTH_SHORT).show();
                break; */
            case R.id.ibRemolacha:
                Toast.makeText(this, "Has pulsao unas remolachas" , Toast.LENGTH_SHORT).show();
                break;
            case R.id.ibTomate:
                Toast.makeText(this, "Has pulsao unos Tomaticos" , Toast.LENGTH_SHORT).show();
                break;
            case R.id.ibUva:
                Toast.makeText(this, "Has pulsao unas uvicas" , Toast.LENGTH_SHORT).show();
                break;
        }

    }

}
