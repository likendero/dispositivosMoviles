package org.iesalandalus.likendero.peluqueria_javier_gonzalez_rives;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class AcDatos extends AppCompatActivity {
    private EditText edCometarios,edTelefono;
    private ListView lvDias;
    private RadioButton rdPelo[] = new RadioButton[5];
    private String dias[] = {"lunes","martes","miercoles","jueves","viernes","sabado","domingo"};
    private String identificador;
    private boolean existe;
    private TextView tvDia;
    // valores actuales
    private int peloAc;
    private String comentariosAc;
    private String telefonoAc;
    private String diaAc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac_datos);
        // rescate elementos
        edCometarios = (EditText) findViewById(R.id.edComentarios);
        edTelefono = (EditText) findViewById(R.id.edTelefono);
        lvDias = (ListView) findViewById(R.id.lvDias);
        tvDia = (TextView) findViewById(R.id.edMostrarDia);
        // creacion lista dias
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.estilo_lista,dias);
        lvDias.setAdapter(adapter);
        // captura rd
        capturaRadio();
        // captura del identificador
        identificador = getIntent().getStringExtra("nombre");
        existe = getIntent().getBooleanExtra("existe",false);
        // annadir accion dia
        accionList();
        rescatarValores();
    }

    /**
     * metodo que captura todos los radiobutton
     * - pelo0 moreno
     * - pelo1 rubio
     * - pelo2 pelirojo
     * - pelo3 castanno
     * - pelo4 sinpelo
     */
    private void capturaRadio(){
        // captura moreno
        rdPelo[0] = (RadioButton) findViewById(R.id.rdMoreno);
        // captura Rubio
        rdPelo[1] = (RadioButton) findViewById(R.id.rdRubio);
        // captura pelirojo
        rdPelo[2] = (RadioButton) findViewById(R.id.rdPelirojo);
        // captura pelirojo
        rdPelo[3] = (RadioButton) findViewById(R.id.rdCastanno);
        // captura sin pelo
        rdPelo[4] = (RadioButton) findViewById(R.id.rdSinPelo);
    }
    /**
     * metodo que sirve para rescatar los valores de los registros
     */
    private void rescatarValores(){
        // comprobacion que existe el usuario
        if(existe){
            peloAc = rescatarPelo();
            comentariosAc = rescatarComentarios();
            telefonoAc = rescatarTelefono();
            diaAc = rescatarDia();
            // cambiar para al usuario
            cambiarPelo(peloAc);
            tvDia.setText("dia: " + diaAc);
            edTelefono.setText(telefonoAc);
            edCometarios.setText(comentariosAc);
            Toast.makeText(this, "Cargar", Toast.LENGTH_SHORT).show();

        }else{
            // primer guardado con valores por defecto
            saveComentarios("");
            saveTelefono("");
            saveRadio(0);
            saveDia("lunes");
            // valores temporales
            diaAc = "lunes";
            comentariosAc = "";
            telefonoAc = "";
            peloAc = 0;
            Toast.makeText(this, "primer inicio", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * metodo que guarda el dia de la semana
     * @param dia
     */
    public void saveDia(String dia){
        // creacion del shared preferences
        SharedPreferences guardarPelo = getSharedPreferences("dia",Context.MODE_PRIVATE);
        SharedPreferences.Editor editorPelu = guardarPelo.edit();
        // guardado del valor
        editorPelu.putString(identificador,dia);
        editorPelu.commit();

    }
    /**
     * metodo que guarda el telefono
     * @param telefono
     */
    public void saveTelefono(String telefono){
        // creacion del shared preferences
        SharedPreferences guardarPelo = getSharedPreferences("telefono",Context.MODE_PRIVATE);
        SharedPreferences.Editor editorPelu = guardarPelo.edit();
        // guardado del valor
        editorPelu.putString(identificador,telefono);
        editorPelu.commit();
    }
    /**
     * metodo que guarda los comentarios
     * @param Comentarios
     */
    public void saveComentarios(String Comentarios){
        // creacion del shared preferences
        SharedPreferences guardarPelo = getSharedPreferences("comentarios",Context.MODE_PRIVATE);
        SharedPreferences.Editor editorPelu = guardarPelo.edit();
        // guardado del valor
        editorPelu.putString(identificador,Comentarios);
        editorPelu.commit();
    }
    /**
     * metodo que guarda el pelo seleccionado
     * @param pelo
     */
    public void saveRadio(int pelo){
        // creacion del shared preferences
        SharedPreferences guardarPelo = getSharedPreferences("pelo",Context.MODE_PRIVATE);
        SharedPreferences.Editor editorPelu = guardarPelo.edit();
        // guardado del valor
        editorPelu.putInt(identificador,pelo);
        editorPelu.commit();
    }

    /**
     * metdo qeu rescata el numero de telefono
     * @return
     */
    public String rescatarTelefono(){
        // creacion del shared preferences
        SharedPreferences guardarPelo = getSharedPreferences("telefono",Context.MODE_PRIVATE);
        // rescatar
        return guardarPelo.getString(identificador,"");
    }

    /**
     * metodo que rescata los comentarios
     * @return
     */
    public String rescatarComentarios(){
        // creacion del shared preferences
        SharedPreferences guardarPelo = getSharedPreferences("comentarios",Context.MODE_PRIVATE);
        // rescatar
        return guardarPelo.getString(identificador,"");
    }

    /**
     * metodo que devuelve el pelo actual
     * @return
     */
    public int rescatarPelo(){

        // creacion del shared preferences
        SharedPreferences guardarPelo = getSharedPreferences("pelo",Context.MODE_PRIVATE);
        // rescatar
        return guardarPelo.getInt(identificador,0);

    }

    /**
     * metodo que rescata el dia
     * @return
     */
    public String rescatarDia(){
        // conexion share
        SharedPreferences rescatar = getSharedPreferences("dia",Context.MODE_PRIVATE);
        // rescate
        return rescatar.getString(identificador,"lunes");
    }
    // cambia el pelo seleccionado
    public void cambiarPelo(int pelo){
        rdPelo[pelo].setChecked(true);
    }

    /**
     * metodo que captura la accion de los
     * radio button para cambiar la seleccion
     */
    public void radioOnClick(View view){
        // castanno
        if(rdPelo[0].isChecked()){
            peloAc = 0;
        }
        // rubio
        if(rdPelo[1].isChecked()){
            peloAc = 1;
        }
        // pelirojo
        if(rdPelo[2].isChecked()){
            peloAc = 2;
        }
        // moreno
        if(rdPelo[3].isChecked()){
            peloAc = 3;
        }
        // sin pelo
        if(rdPelo[4].isChecked()){
            peloAc = 4;
        }

    }
    /**
     * creacion de accion
     */
    private void accionList(){
        lvDias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                diaAc = lvDias.getItemAtPosition(i).toString();
                tvDia.setText("dia: " + diaAc);
            }
        });
    }

    /**
     * metodo para el boton guardar
     */
    public void guardarOnClick(View view){
        comentariosAc = edCometarios.getText().toString();
        telefonoAc = edTelefono.getText().toString();
        saveComentarios(comentariosAc);
        saveTelefono(telefonoAc);
        saveRadio(peloAc);
        saveDia(diaAc);
        Toast.makeText(this, "guardado realizado", Toast.LENGTH_SHORT).show();
    }
    public void volverOnClick(View view){
        Intent volver = new Intent(this,MainActivity.class);
        startActivity(volver);
    }

}
