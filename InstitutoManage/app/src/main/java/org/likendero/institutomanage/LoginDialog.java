package org.likendero.institutomanage;

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

public class LoginDialog extends DialogFragment implements Dialog.OnClickListener {
    public LoginDialog(){}
    private OnSingListener onSing;

    public interface OnSingListener{
        public void onSing(String email, String pass, String repass);
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        // creacion de un builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(R.layout.sing);
        builder.setTitle(R.string.singTitle);
        builder.setPositiveButton(R.string.singBtTitle,this);
        builder.setNegativeButton(R.string.singCancel,this);
        builder.setCancelable(false);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        EditText email = (EditText) ((Dialog)dialog).findViewById(R.id.edNombre);
        EditText pass = (EditText) ((Dialog)dialog).findViewById(R.id.edPass);
        EditText rePass = (EditText) ((Dialog)dialog).findViewById(R.id.edRePass);
        // textos
        String strEmail = email.getText().toString();
        String strPass = pass.getText().toString();
        String strRepass = rePass.getText().toString();

        //realizar acciones
        onSing.onSing(strEmail,strPass,strRepass);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // acctiviti nulo para mas adelante
        Activity act = null;
        if(context instanceof Activity){
            act = (Activity)context;
        }
        // se captura el listener
        try{

            onSing = (OnSingListener) act;

        }catch(ClassCastException cl){
            Log.d("error", cl.getMessage());
        }
    }
}
