package com.example.iprodottidellamiaterra;

import android.app.Activity;
import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class InventarioActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventario_activity);

        listView = findViewById(R.id.inventarioLv);

        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.list_view_inv, new ArrayList<Prodotto>());

        listView.setAdapter(customAdapter);

        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences("Ok", Context.MODE_PRIVATE);
        Map<String, ?> map = sharedPref.getAll();
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            String k = entry.getKey();
            int v = (Integer)entry.getValue();
            Prodotto prodotto = new Prodotto(k, ""+ v);
            customAdapter.add(prodotto);
        }


    }

    public void aggiungiInventarioClicked(View v) {
        FragmentInventario fragmentInventario = new FragmentInventario();
        fragmentInventario.show(getSupportFragmentManager(), "Fg");
    }

    public void cancelCrossClicked(View v) {
        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences("Ok", Context.MODE_PRIVATE);
        ViewParent parent = v.getParent();
        ConstraintLayout constraintLayout = (ConstraintLayout)parent;
        TextView desc = constraintLayout.findViewById(R.id.textViewDescr);
        String descr =  desc.getText().toString();
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(descr);
        editor.commit();
        this.recreate();
    }

    //CONTINUARE METODO
    public void scrollNumPick(View v) {
        NumberPicker numberPicker = (NumberPicker)v;
       // numberPicker.setOnScrollListener();
    }

}
