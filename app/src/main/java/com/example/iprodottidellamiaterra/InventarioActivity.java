package com.example.iprodottidellamiaterra;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class InventarioActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventario_activity);


        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.file_name), Context.MODE_PRIVATE);
        int qnt = sharedPref.getInt("Insalata", 10000);
        listView = findViewById(R.id.inventarioLv);

        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.list_view_inv, new ArrayList<Prodotto>());

        listView.setAdapter(customAdapter);

        Prodotto prodotto = new Prodotto("Insalata", ""+ qnt);
        customAdapter.add(prodotto);


    }

    public void aggiungiInventarioClicked(View v) {
        FragmentInventario fragmentInventario = new FragmentInventario();
        fragmentInventario.show(getSupportFragmentManager(), "Fg");
    }

}
