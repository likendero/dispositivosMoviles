package org.likendero.aplicacionescolar;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class MainActivity extends AppCompatActivity {
    private GoogleSignInOptions googleOp;
    private GoogleSignInClient googleClient;
    private final int COD_SING = 1111;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onCreate(@androidx.annotation.Nullable Bundle savedInstanceState, @androidx.annotation.Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        googleOp = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("hola que tal")
                .requestEmail()
                .build();
        googleClient = GoogleSignIn.getClient(this,googleOp);
    }

    /**
     * metodo que lanzal una activity para acceder con una cuneta de google
     */
    public void singIn(){
        Intent intent = googleClient.getSignInIntent();
        startActivityForResult(intent,COD_SING);
    }
}
