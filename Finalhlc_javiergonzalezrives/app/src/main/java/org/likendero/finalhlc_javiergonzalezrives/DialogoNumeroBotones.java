package org.likendero.finalhlc_javiergonzalezrives;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.RadioButton;


public class DialogoNumeroBotones extends DialogFragment implements Dialog.OnClickListener {
    private ComenzarListener comenzar;

    public DialogoNumeroBotones(){}

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(R.layout.dialogo);
        builder.setTitle("opciones");
        builder.setPositiveButton("comenzar",this);
        builder.setCancelable(false);
        return builder.create();
    }

    // LISTENER PERSONALIZADO ////////////////////////////////////
    public interface ComenzarListener{
        public void onComenzar(int numero,boolean color);
    }

    public ComenzarListener getComenzar() {
        return comenzar;
    }

    public void setComenzar(ComenzarListener comenzar) {
        this.comenzar = comenzar;
    }

    // ACCIONES ///////////////////////////////////////////////////
    @Override
    public void onClick(DialogInterface dialog, int which) {
        EditText edNumero = (EditText) ((Dialog) dialog).findViewById(R.id.edNumeros);
        RadioButton rdRojo = (RadioButton) ((Dialog)dialog).findViewById(R.id.rdRojo);
        int numero = 0;
        boolean rojo = true;
        try{
            numero = Integer.parseInt(edNumero.getText().toString());
            rojo = rdRojo.isChecked();

        }catch(NumberFormatException nm){
            Log.d("error",nm.getLocalizedMessage());
        }
        comenzar.onComenzar(numero,rojo);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Activity activity=getActivity();
        Activity activity = null;
        if (context instanceof Activity) {
            activity = (Activity) context;
        }
        try {
            comenzar = (ComenzarListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement ComenzarListener");
        }
    }
}
