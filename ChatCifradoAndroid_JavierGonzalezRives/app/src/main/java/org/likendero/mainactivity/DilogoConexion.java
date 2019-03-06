package org.likendero.mainactivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.EditText;

public class DilogoConexion extends DialogFragment implements DialogInterface.OnClickListener {
    private Activity activity;
    private SucedeDialogListener sucede;

    public DilogoConexion(){}
    @Override
    public void onClick(DialogInterface dialog, int which) {
        EditText etip = (EditText) ((Dialog) dialog).findViewById(R.id.etip);
        EditText etPuerto = (EditText) ((Dialog) dialog).findViewById(R.id.etPuerto);
        String ip = etip.getText().toString();
        int puerto = Integer.parseInt(etPuerto.getText().toString());
        sucede.onDialogClick(ip,puerto);
    }

    /**
     * metodo con los listener para pasar la informacion
     */
    public interface SucedeDialogListener{
        public void onDialogClick(String ip, int puerto);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(R.layout.dialogo_vista);
        builder.setPositiveButton(R.string.positive,this);
        builder.setCancelable(false);
        return builder.create();
    }

    public SucedeDialogListener getSucede() {
        return sucede;
    }

    public void setSucede(SucedeDialogListener sucede) {
        this.sucede = sucede;
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
            sucede = (SucedeDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onDialogoNombreNivel");
        }
    }
}
