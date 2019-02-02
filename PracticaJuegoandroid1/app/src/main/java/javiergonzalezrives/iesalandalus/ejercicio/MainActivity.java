package javiergonzalezrives.iesalandalus.ejercicio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private Spinner spSelector;
    private final String[] = {
            "nivel 1",
            "nivel 2",
            "nivel 3",
            "nivel 4",
            "nivel 5",
            "nivel 6"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // instanciacion del spinner
        spSelector = (Spinner) findViewById(R.id.spNivel);

    }

    /**
     * preparar el spinner para la seleccion del
     * nivel
     */
    public void generarSpinner(){

        ArrayAdapter<String> adapter() = ArrayAdapter.createFromResource(this, android.R.layout.simple_spinner_item,);

    }
}
