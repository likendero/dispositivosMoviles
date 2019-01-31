package javierGonzalezRives.iesAlandalus.hlcjuego2_fast_buttons_javiergonzalezrives;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

// Base activity es igual que compact activity pero con la
// funcionalidad del modo inmersivo añadida
public class MainActivity extends BaseActivity implements View.OnClickListener {
    private ImageView ivIns,ivJugar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // captura de los elementos
        ivIns =(ImageView) findViewById(R.id.bvInstrucciones);
        ivJugar = (ImageView) findViewById(R.id.bvJugar);
        //
        ivJugar.setOnClickListener(this);
        ivIns.setOnClickListener(this);
        // set modo inmersivo
        setModoInmersicvo();

    }
    @Override
    public void onClick(View view){
        if(view.getId() == ivIns.getId()){
            new AlertDialog.Builder(this)
                    .setTitle(R.string.como_jugar)
                    .setMessage(R.string.explicacion)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setModoInmersicvo(); // cada vez que hay un evento es necesario llamarlo
                        }
                    });
        }
        if(view.getId() == ivJugar.getId()){
            // hemos pulsado en jugar
            Intent in = new Intent(this,SegundaActivity.class);
            startActivity(in);
        }
    }

}
