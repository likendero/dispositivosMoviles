package javiergonzalezrives.iesalandalus.p32firebase_prueba_javiergonzalezrives;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity  {
    private EditText etNombre,etDni,etPrimerApellido,etSegundoApellido ,etEdad;
    private FirebaseDatabase data = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = data.getReference("Agenda");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // capturar componentes
        etNombre = (EditText) findViewById(R.id.etNombre);
        etDni = (EditText) findViewById(R.id.etDni);
        etPrimerApellido = (EditText) findViewById(R.id.etprimerApellido);
        etSegundoApellido = (EditText) findViewById(R.id.etSegundoApellido);
        etEdad = (EditText) findViewById(R.id.etEdad);
    }

    /**
     * guardar los registros
     */
    public void guardar (View view){
        // escribir mensaje en la base de datos

        DatabaseReference chipN;

        chipN = myRef.child(etDni.getText().toString());
        // annadir gato

        chipN.child("nombre").setValue(etNombre.getText().toString());
        chipN.child("primerApellido").setValue(etPrimerApellido.getText().toString());
        chipN.child("seundoApellido").setValue(etSegundoApellido.getText().toString());
        chipN.child("edad").setValue(etEdad.getText().toString());


    }

    /**
     * metodo que sirve para modificar los datos
     */
    public void modificar(){


    }
    @Override
    protected void onStart(){
        super.onStart();
        // optener referencia del campo edad
        DatabaseReference edad = myRef.child("54").child("edad");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        edad.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valorEdad = dataSnapshot.getValue(String.class);
                etEdad.setText(valorEdad);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
