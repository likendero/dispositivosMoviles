package org.likendero.institutomanage;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements LoginDialog.OnSingListener  {
    private final String EMAIL = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";

    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // captura de usuario
        auth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();
        // captura de usuario si esta iniciado
        FirebaseUser currentUser = auth.getCurrentUser();
        //comprovacion si esta inicado
        if(currentUser != null){

       }else{
            Toast.makeText(this, "El usuario esta iniciado", Toast.LENGTH_SHORT).show();
        }
    }
    // ACCIONES /////////////////////////////////////////////
    // metodo para realizar un registro
    public void onRegistrar(View view){
        abrirDialogo();
    }
    private void abrirDialogo(){
        LoginDialog login =  new LoginDialog();
        login.setCancelable(false);
        login.show(getSupportFragmentManager(),"sing");
    }


    // ACCION REGISTRO
    @Override
    public void onSing(String email, String pass, String repass) {
        boolean bandera = false;
        // caso que halla algun campo vacio
        if(email.trim().isEmpty() || pass.trim().isEmpty() || repass.trim().isEmpty()){
            // comprovacion de correo valido
            if(email.matches(email)){
                // la contrasena sea correcta
                if(pass.equals(repass))
                {

                    crearUsuario(email, pass);
                }
                // CASOS DE ERROR
                else{
                    Toast.makeText(this, "Las contrsennas no coinciden", Toast.LENGTH_SHORT).show();
                    bandera = true;
                }
            }else{
                Toast.makeText(this, "El correo no es valido", Toast.LENGTH_SHORT).show();
                bandera = true;
            }
        }else{
            Toast.makeText(this, "Flatan campos por completar", Toast.LENGTH_SHORT).show();
            bandera = true;
        }
        // si ocurre algun error se muestra un dialogo
        if(bandera) abrirDialogo();
    }

    /**
     * metodo que crea un usuario nuevo
     * @param email
     * @param pass
     */
    private void crearUsuario(String email, String pass){

        auth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "se ha creado la cuenta", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "no se ha creado nada", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
