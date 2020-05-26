package com.example.iprodottidellamiaterra;

import android.app.Activity;
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


        String[] nomi = {"Pacchetto di semi di pomodoro", "Bottiglia fertilizzante"};
        listView = findViewById(R.id.inventarioLv);

        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.list_view_inv, new ArrayList<Prodotto>());

        listView.setAdapter(customAdapter);

        for(int i = 0; i < nomi.length; i++) {
            Prodotto prodotto = new Prodotto(nomi[i], "2");
            customAdapter.add(prodotto);
        }


    }

    public void aggiungiInventarioClicked(View v) {
        FragmentInventario fragmentInventario = new FragmentInventario();
        fragmentInventario.show(getSupportFragmentManager(), "Fg");
    }

}
