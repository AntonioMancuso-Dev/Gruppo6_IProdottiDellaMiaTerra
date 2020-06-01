package com.example.iprodottidellamiaterra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapterColtivazioni extends ArrayAdapter<Prodotto> {
    private int resource;
    private LayoutInflater inflater;

    public CustomAdapterColtivazioni(Context context, int resourceId, ArrayList<Prodotto> objects){
        super(context, resourceId, objects);
        resource = resourceId;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        Prodotto p = getItem(position);

        if(v == null) {
            v = inflater.inflate(R.layout.list_view_colt, null);
        }

        TextView descr = v.findViewById(R.id.textViewColtDes);
        descr.setText(p.getDescr());

        TextView tempo = v.findViewById(R.id.textViewColtTempo);
        tempo.setText(p.getTempo() + " giorni");

        return v;
    }
}
