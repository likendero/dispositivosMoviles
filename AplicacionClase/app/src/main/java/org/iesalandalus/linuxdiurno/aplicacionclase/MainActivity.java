package org.iesalandalus.linuxdiurno.aplicacionclase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) { // mientras se esta creando
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,"evento onCreate",Toast.LENGTH_SHORT).show();

        int quimica = 5;
        int fisica = 5;
        int matematicas = 5;
        int media = 0;

        media = (quimica + fisica + matematicas)/3;
        if(media >= 5){
            Toast.makeText(this,"Aprobado",Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this,"suspenso",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() { // esta apunto de acerse visible
        super.onStart();

        Toast.makeText(this,"evento onStart",Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume(){ // actividad se ha vuelto visible
        super.onResume();
        Toast.makeText(this,"evento onResume",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() { // cuando la aplicacion esta apunto de detenerse
        super.onPause();
        Toast.makeText(this,"evento pausa", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() { // se ejecuta cuando la actividad ya no es visible
        super.onStop();
        Toast.makeText(this,"onStop",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {// cuando la actividad esta apunto de ser destruida
        super.onDestroy();
        Toast.makeText(this,"onDestroy",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this,"onRestart",Toast.LENGTH_SHORT).show();
    }
}
