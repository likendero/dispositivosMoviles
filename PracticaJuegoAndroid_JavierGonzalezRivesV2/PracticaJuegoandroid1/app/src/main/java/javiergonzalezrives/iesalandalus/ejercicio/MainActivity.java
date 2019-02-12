package javiergonzalezrives.iesalandalus.ejercicio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private Spinner spSelector;
    public static final String NIVEL = "nivel";
    private final String[] NIVELES = {
            "nivel 1",
            "nivel 2",
            "nivel 3",
            "nivel 4",
            "nivel 5"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // instanciacion del spinner
        spSelector = (Spinner) findViewById(R.id.spNivel);
        generarSpinner();
    }

    /**
     * preparar el spinner para la seleccion del
     * nivel
     */
    private void generarSpinner(){
        // inflater con los niveles disponibles
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,NIVELES) ;
        spSelector.setAdapter(adapter);
    }

    /**
     * metodo que realiza las acciones al pulsar el boton
     * @param view
     */
    public void onClickJugar (View view){
        Intent intent = new Intent(this,JuegoActivity.class);
        // se pasa el indice del nivel seleccionado para cargar el nivel en
        // el siguiente activity
        int indice = spSelector.getSelectedItemPosition() + 1;
        intent.putExtra(NIVEL,indice);
        startActivity(intent);
    }
}
