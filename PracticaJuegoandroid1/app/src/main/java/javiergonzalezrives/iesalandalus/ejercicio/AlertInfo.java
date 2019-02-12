package javiergonzalezrives.iesalandalus.ejercicio;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Layout;
import android.view.LayoutInflater;

import java.util.zip.Inflater;

public class AlertInfo extends DialogFragment {
    JuegoActivity.Barra barra;
    public AlertInfo(){

    }

    public JuegoActivity.Barra getBarra() {
        return barra;
    }

    public void setBarra(JuegoActivity.Barra barra) {
        this.barra = barra;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflate = getActivity().getLayoutInflater();
        builder.setView(inflate.inflate(R.layout.mensaje,null));
        builder.setPositiveButton(R.string.btEntendido, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                barra.execute();
            }
        });
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
}
