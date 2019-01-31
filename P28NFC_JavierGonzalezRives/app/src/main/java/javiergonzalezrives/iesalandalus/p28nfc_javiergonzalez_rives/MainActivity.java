package javiergonzalezrives.iesalandalus.p28nfc_javiergonzalez_rives;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private NfcAdapter nfc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // captura del nfc
        nfc = NfcAdapter.getDefaultAdapter(this);
        // si es nulo no tenemos nfc en el dispositivo D:
        if(nfc == null){
            Toast.makeText(this, "Este dispositivo no tiene nfc", Toast.LENGTH_SHORT).show();
        }else if(!nfc.isEnabled()){
            Toast.makeText(this, "Activa el NFC para usar la aplicacion :D", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if(nfc != null) {
            Toast.makeText(this, "Dispositivo encontrado", Toast.LENGTH_SHORT).show();
            super.onNewIntent(intent);
        }
    }
    /*
        con el paso por pause y resume eliminamos el abrir otra pantalla
        y resetemaos la actual
     */
    @Override
    protected void onResume() {
        if(nfc != null) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
            IntentFilter[] intentFilters = new IntentFilter[]{};
            nfc.enableForegroundDispatch(this, pendingIntent, intentFilters, null);

        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        if(nfc != null) {
            nfc.disableForegroundDispatch(this);
        }
        super.onPause();
    }
}
