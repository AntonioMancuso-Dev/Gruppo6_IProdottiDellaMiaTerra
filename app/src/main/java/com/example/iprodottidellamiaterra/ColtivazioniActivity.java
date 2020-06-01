package com.example.iprodottidellamiaterra;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


public class ColtivazioniActivity extends AppCompatActivity {
    ListView listView;
    ColtivazioniActivity coltivazioniActivity = this;

    @Override
    protected void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.coltivazioni_activity);

        listView = findViewById(R.id.coltivazioniLv);

        CustomAdapterColtivazioni customAdapterColtivazioni = new CustomAdapterColtivazioni(this, R.layout.list_view_colt, new ArrayList<Prodotto>());
        listView.setAdapter(customAdapterColtivazioni);

        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences("Coltivazioni", Context.MODE_PRIVATE);
        Map<String, ?> map = sharedPref.getAll();
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            String k = entry.getKey();
            String info = (String)entry.getValue();
            Prodotto prodotto = new Prodotto(k, info);
            customAdapterColtivazioni.add(prodotto);
        }

    }

    public void aggiungiColtivazioniClicked(View v) {
        FragmentColtivazioni fragmentColtivazioni = new FragmentColtivazioni();
        fragmentColtivazioni.show(getSupportFragmentManager(), "F");
    }

    public void cancelColtivazioneCross(final View v) {
        AlertDialog alertDialog = new AlertDialog.Builder(ColtivazioniActivity.this).create();
        alertDialog.setTitle("Attenzione");
        alertDialog.setMessage("Quest'operazione cancellerà l'elemento selezionato dalle coltivazioni. Sei sicuro di voler procedere?");
        alertDialog.setButton("ANNULLA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.setButton2("PROCEDI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Context context = getApplicationContext();
                SharedPreferences sharedPref = context.getSharedPreferences("Coltivazioni", Context.MODE_PRIVATE);
                ViewParent parent = v.getParent();
                ConstraintLayout constraintLayout = (ConstraintLayout)parent;
                TextView desc = constraintLayout.findViewById(R.id.textViewColtDes);
                String descr =  desc.getText().toString();
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.remove(descr);
                editor.commit();
                coltivazioniActivity.recreate();
            }
        });
        alertDialog.show();
    }

    public void infoClicked(View v) {
        Context context = getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("Coltivazioni", Context.MODE_PRIVATE);
        ViewParent parent = v.getParent();
        ConstraintLayout constraintLayout = (ConstraintLayout)parent;
        TextView desc = constraintLayout.findViewById(R.id.textViewColtDes);
        String descr =  desc.getText().toString();
        String info = sharedPreferences.getString(descr, "null");

        AlertDialog alertDialog = new AlertDialog.Builder(ColtivazioniActivity.this).create();
        alertDialog.setTitle("INFORMAZIONI PRODOTTO");
        alertDialog.setMessage("Prodotto:          " + descr + "\n" + "INFO:          " + info);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "CHIUDI",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
