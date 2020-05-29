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
               Context context = getActivity();
               SharedPreferences sharedPref = context.getSharedPreferences("Ok", Context.MODE_PRIVATE);
               SharedPreferences.Editor editor = sharedPref.edit();

               EditText descTx = v.findViewById(R.id.descTx);
               String desc = descTx.getText().toString();

               EditText qntTx = v.findViewById(R.id.qntTx);
               String qnt = qntTx.getText().toString();
               int finQnt = Integer.parseInt(qnt);


               editor.putInt("OK", 1);
               editor.commit();
               dismiss();
           }
       });

       builder.setNegativeButton("AGGIUNGI", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               Context context = getActivity();
               SharedPreferences sharedPref = context.getSharedPreferences("Ok", Context.MODE_PRIVATE);
               SharedPreferences.Editor editor = sharedPref.edit();

               LayoutInflater vi = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
               View nnn = vi.inflate(R.layout.fgm_pop, null);
               TextView tv = nnn.findViewById(R.id.nomeProdotto);
               String np = tv.getText().toString();
               EditText descTx = nnn.findViewById(R.id.descTx);
               String desc = descTx.getText().toString();
               desc.length();

               /*
               CON QUESTA RIGA DI CODICE POSSO PRENDERE I DATI INSERITI NEL TEXTEDIT ------------- IMPORTANTE!!!!!!!!
                */
              String d =  ((EditText) getDialog().findViewById(R.id.descTx)).getText().toString();


               //EditText qntTx = v.findViewById(R.id.qntTx);
              // String qnt = qntTx.getText().toString();
               //int finQnt = Integer.parseInt(qnt);


               editor.putInt("OK", 1);
               editor.apply();
               dismiss();
           }
       });

       return builder.create();
    }

   /* public void aggiungiPop(View v) {
        this.dismiss();
    }*/

}
