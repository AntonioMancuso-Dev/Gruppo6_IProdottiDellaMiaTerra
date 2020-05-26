package com.example.iprodottidellamiaterra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Prodotto> {
    private int resource;
    private LayoutInflater inflater;

    public CustomAdapter(Context context, int resourceId, ArrayList<Prodotto> objects) {
        super(context, resourceId, objects);
        resource = resourceId;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        Prodotto p = getItem(position);

        if(v == null) {
            v = inflater.inflate(R.layout.list_view_inv, null);
        }

        TextView descr = v.findViewById(R.id.textViewDescr);

        descr.setText(p.getDescr());

        NumberPicker numberPicker = v.findViewById(R.id.numPick);
        numberPicker.setMaxValue(1000);
        numberPicker.setMinValue(1);
        numberPicker.setWrapSelectorWheel(false);

        return v;
    }
}
