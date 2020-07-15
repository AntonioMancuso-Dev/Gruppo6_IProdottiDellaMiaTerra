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
import android.widget.SearchView;
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
    SearchView searchView;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.coltivazioni_activity);

        listView = findViewById(R.id.coltivazioniLv);
        searchView = findViewById(R.id.searchBtn);
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                searchView.setQuery(null, true);
                coltivazioniActivity.recreate();

                return true;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                CustomAdapterColtivazioni customAdapterColtivazioni = new CustomAdapterColtivazioni(context, R.layout.list_view_colt, new ArrayList<Prodotto>());
                listView.setAdapter(customAdapterColtivazioni);

                Context context2 = getApplicationContext();
                SharedPreferences sharedPref2 = context.getSharedPreferences("Coltivazioni", Context.MODE_PRIVATE);
                Map<String, ?> map2 = sharedPref2.getAll();
                for (Map.Entry<String, ?> entry2 : map2.entrySet()) {
                    int qryLngt = query.length();
                    boolean eq = false;

                    String k2 = entry2.getKey();
                    String value = (String)entry2.getValue();
                    String info = value.substring(0, value.indexOf("#_#"));
                    Date startDate = new Date(Long.parseLong(value.substring(info.length() + 3, value.length())));





                    eq = k2.toUpperCase().startsWith(query.toUpperCase());
                    /*
                    for(int i = 0; i < qryLngt; i++) {
                        if(k2.toUpperCase().substring(0, qryLngt).equals(query.toUpperCase())) {
                            eq = true;
                        } else
                            eq = false;
                    }
                    */

                    if(eq) {
                        Prodotto prodotto = new Prodotto(k2, info, startDate);
                        customAdapterColtivazioni.add(prodotto);
                    }
                }

                //}

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        CustomAdapterColtivazioni customAdapterColtivazioni = new CustomAdapterColtivazioni(this, R.layout.list_view_colt, new ArrayList<Prodotto>());
        listView.setAdapter(customAdapterColtivazioni);

        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences("Coltivazioni", Context.MODE_PRIVATE);
        Map<String, ?> map = sharedPref.getAll();


        for (Map.Entry<String, ?> entry : map.entrySet()) {
            String k = entry.getKey();
            String value = (String)entry.getValue();
            String info = value.substring(0, value.indexOf("#_#"));
            Date startDate = new Date(Long.parseLong(value.substring(info.length() + 3, value.length())));
            Prodotto prodotto = new Prodotto(k, info, startDate);
            customAdapterColtivazioni.add(prodotto);
        }

    }

    public void aggiungiColtivazioniClicked(View v) {
        FragmentColtivazioni fragmentColtivazioni = new FragmentColtivazioni();
        fragmentColtivazioni.show(getSupportFragmentManager(), "F");
    }

    public void cancelColtivazioneCross(final View v) {
        ViewParent parent = v.getParent();
        ConstraintLayout constraintLayout = (ConstraintLayout)parent;
        TextView desc = constraintLayout.findViewById(R.id.textViewColtDes);
        final String descr =  desc.getText().toString();
        AlertDialog alertDialog = new AlertDialog.Builder(ColtivazioniActivity.this).create();
        alertDialog.setTitle("Sicuro di voler rimuovere il prodotto?");
        alertDialog.setMessage("Prodotto: " + descr);
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
        String info = sharedPreferences.getString(descr, "");
        info = info.substring(0, info.indexOf("#_#"));

        AlertDialog alertDialog = new AlertDialog.Builder(ColtivazioniActivity.this).create();
        alertDialog.setTitle("          INFORMAZIONI PRODOTTO");
        alertDialog.setMessage("Prodotto: " + descr + "\n \n" + "                                   INFO          " + "\n" + info);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "CHIUDI",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
