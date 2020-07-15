package com.example.iprodottidellamiaterra;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class FragmentColtivazioni extends DialogFragment {

    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);


        v = inflater.inflate(R.layout.fgm_coltivazioni, container, false);

        return v;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedinstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(R.layout.fgm_coltivazioni);
        builder.setPositiveButton("ANNULLA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        builder.setNegativeButton("AGGIUNGI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Context context = getActivity();
                SharedPreferences sharedPref = context.getSharedPreferences("Coltivazioni", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();

                String nomeProdotto = ((EditText) getDialog().findViewById(R.id.nomeTx)).getText().toString();
                String descProd = ((EditText) getDialog().findViewById(R.id.desProd)).getText().toString();
                editor.putString(nomeProdotto, descProd + "#_#" + String.valueOf(new Date().getTime()));

                editor.apply();
                dismiss();
                getActivity().recreate();
            }
        });

        return builder.create();
    }
}
