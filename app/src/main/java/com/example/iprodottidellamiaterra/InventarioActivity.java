package com.example.iprodottidellamiaterra;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class InventarioActivity extends AppCompatActivity {
    ListView listView;
    InventarioActivity inventarioActivity = this;
    NumberPicker numberPicker;
    SearchView searchView;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventario_activity);

        listView = findViewById(R.id.inventarioLv);
        searchView = findViewById(R.id.searchInventario);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                CustomAdapter customAdapterColtivazioni = new CustomAdapter(context, R.layout.list_view_inv, new ArrayList<Prodotto>());
                listView.setAdapter(customAdapterColtivazioni);

                Context context2 = getApplicationContext();
                SharedPreferences sharedPref2 = context.getSharedPreferences("Ok", Context.MODE_PRIVATE);
                Map<String, ?> map2 = sharedPref2.getAll();
                for (Map.Entry<String, ?> entry2 : map2.entrySet()) {
                    int qryLngt = query.length();
                    boolean eq = false;

                    String k2 = entry2.getKey();
                    int info2 =  (Integer)entry2.getValue();
                    for (int i = 0; i < qryLngt; i++) {
                        if (k2.toUpperCase().substring(0, qryLngt).equals(query.toUpperCase())) {
                            eq = true;
                        } else
                            eq = false;
                    }

                    if (eq) {
                        Prodotto prodotto = new Prodotto(k2, ""+ info2);
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

        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.list_view_inv, new ArrayList<Prodotto>());

        listView.setAdapter(customAdapter);

        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences("Ok", Context.MODE_PRIVATE);
        Map<String, ?> map = sharedPref.getAll();
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            String k = entry.getKey();
            int v = (Integer) entry.getValue();
            Prodotto prodotto = new Prodotto(k, "" + v);
            customAdapter.add(prodotto);
        }

    }



    public void aggiungiInventarioClicked(View v) {
        FragmentInventario fragmentInventario = new FragmentInventario();
        fragmentInventario.show(getSupportFragmentManager(), "Fg");
    }

    public void cancelCrossClicked(final View v) {
        AlertDialog alertDialog = new AlertDialog.Builder(InventarioActivity.this).create();
        alertDialog.setTitle("Attenzione");
        alertDialog.setMessage("Quest'operazione cancellerà l'elemento selezionato dall'inventario. Sei sicuro di voler procedere?");
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
                SharedPreferences sharedPref = context.getSharedPreferences("Ok", Context.MODE_PRIVATE);
                ViewParent parent = v.getParent();
                ConstraintLayout constraintLayout = (ConstraintLayout)parent;
                TextView desc = constraintLayout.findViewById(R.id.textViewDescr);
                String descr =  desc.getText().toString();
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.remove(descr);
                editor.commit();
                inventarioActivity.recreate();
            }
        });
        alertDialog.show();
    }


    public void scrollNumPick(View v) {
        NumberPicker numberPicker = (NumberPicker)v;
       // numberPicker.setOnScrollListener();
    }

}
