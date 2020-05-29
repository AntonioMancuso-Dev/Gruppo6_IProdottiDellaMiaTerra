package com.example.iprodottidellamiaterra;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.zip.Inflater;

public class FragmentInventario extends DialogFragment {
    View v;
    Button popAdd;
    Button popCanc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);


        v = inflater.inflate(R.layout.fgm_pop, container, false);

        return v;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
       AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(R.layout.fgm_pop);

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
               SharedPreferences sharedPref = context.getSharedPreferences("Ok", Context.MODE_PRIVATE);
               SharedPreferences.Editor editor = sharedPref.edit();

              String desc =  ((EditText) getDialog().findViewById(R.id.descTx)).getText().toString();
              String qntString = ((EditText) getDialog().findViewById(R.id.qntTx)).getText().toString();
              int qnt = Integer.parseInt(qntString);

              editor.putInt(desc, qnt);
              editor.apply();
              dismiss();
              getActivity().recreate();

           }
       });

       return builder.create();
    }

   /* public void aggiungiPop(View v) {
        this.dismiss();
    }*/

}
