package org.likendero.pruebafirestre;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private GoogleSignInOptions googleSO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void annadir(View view){
        FirebaseFirestore fire = FirebaseFirestore.getInstance();
        Map<String,Object> maria = new HashMap<>();
        maria.put("nombre","maria de las Mercedes");
        maria.put("funcion","profesora");
        maria.put("edad",45);
        DocumentReference ref = fire.document("personas/maria");
        ref.set(maria)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this, "la inserccion ha sido unb exito", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
