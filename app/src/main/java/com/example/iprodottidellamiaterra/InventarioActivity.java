package com.example.iprodottidellamiaterra;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class InventarioActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventario_activity);


        String[] nomi = {"Pacchetto di semi di pomodoro", "Bottiglia fertilizzante"};
        listView = findViewById(R.id.inventarioLv);

        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.list_view_inv, new ArrayList<Prodotto>());

        listView.setAdapter(customAdapter);

        for(int i = 0; i < nomi.length; i++) {
            Prodotto prodotto = new Prodotto(nomi[i], "2");
            customAdapter.add(prodotto);
        }
    }

}
