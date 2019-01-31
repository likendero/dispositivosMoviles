package com.example.practica2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.RadioGroup;

public class DialogoNombreNivel extends DialogFragment implements DialogInterface.OnClickListener {
    protected EditText etNombre;
    protected RadioGroup radioG;
    protected String nombre;
    OnDialogNombreNivel miListener;
    /*
        el new instance sustitulle al metodo constructor
     */
    @org.jetbrains.annotations.NotNull
    @org.jetbrains.annotations.Contract(" -> new")
    public static DialogoNombreNivel newInstance(){
        return new DialogoNombreNivel();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = null;
        if(context instanceof Activity){
            activity = (Activity)context;
        }
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            miListener = (OnDialogNombreNivel) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }


    // Creacion del dialogo ///////////////////////////////////////////////////////
    @Override
    public Dialog onCreateDialog(Bundle bundle){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // layout inflater
        LayoutInflater inf = getActivity().getLayoutInflater();
        // inflate
        builder.setView(inf.inflate(R.layout.dialogo_nombre_nivel,null))
                .setTitle(R.string.nueva_partida)
                .setPositiveButton(R.string.jugar,this)
                .setNegativeButton(R.string.volver,this)
                .setCancelable(false);
        return builder.create();

    }


    // interfaz ////////////////////////////////////////////////////////////////

    /**
     * interfaz para pasar los parametros a la activity
     */
    public interface OnDialogNombreNivel {
        public void onAceptarDialogo(String nombre, int velocidad);
    }

    // Metodos acciones ////////////////////////////////////////////////////////
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        etNombre = (EditText) ((Dialog)dialogInterface).findViewById(R.id.tieNombre);
        nombre = etNombre.getText().toString();
        radioG = (RadioGroup)((Dialog)dialogInterface).findViewById(R.id.rGroupNivel);
        int velocidad = 0;
        int nivel = radioG.getCheckedRadioButtonId();
        switch (nivel){
            case R.id.rbDificil:
                velocidad = 60;
            break;
            case R.id.rbFacil:
                velocidad = 20;
                break;
            case R.id.rbMedio:
                velocidad = 40;
                break;
        }
        switch(i){
            case DialogInterface.BUTTON_POSITIVE:
                miListener.onAceptarDialogo(nombre,velocidad);
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                getActivity().finish();
                break;
        }
    }
}
