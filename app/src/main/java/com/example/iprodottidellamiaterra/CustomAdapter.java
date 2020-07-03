package com.example.iprodottidellamiaterra;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

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
        final Prodotto p = getItem(position);

        if(v == null) {
            v = inflater.inflate(R.layout.list_view_inv, null);
        }

        //mod ROs
        final TextView numeroQ = v.findViewById(R.id.numeroQ);
        numeroQ.setText(p.getQnt());
        Button plus = v.findViewById(R.id.plus);
        //Fine mod


        TextView descr = v.findViewById(R.id.textViewDescr);

        descr.setText(p.getDescr());

        final NumberPicker numberPicker = v.findViewById(R.id.numPick);

        numberPicker.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker view, int scrollState) {
                if(scrollState == SCROLL_STATE_FLING) {
                    final int val = numberPicker.getValue();

                    //if(scrollState == SCROLL_STATE_IDLE) {
                        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                        alertDialog.setTitle("Attenzione");
                        alertDialog.setMessage("Quest'operazione modificherà la quantità dell'elemento selezionato a " + numberPicker.getValue() + "" + ". Sei sicuro di voler procedere?");
                        alertDialog.setButton("ANNULLA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                numberPicker.setValue(val);
                                dialog.dismiss();
                            }
                        });
                        alertDialog.setButton2("PROCEDI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Context context = getContext();
                                SharedPreferences sharedPref = context.getSharedPreferences("Ok", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.remove(p.getDescr());
                                editor.commit();
                                editor.putInt(p.getDescr(), numberPicker.getValue());
                                editor.commit();
                                numberPicker.setValue(val);
                                dialog.dismiss();
                            }
                        });
                        alertDialog.show();
                    //}

                }
                }

            //Modifiche Rosario

            public void plusClick() {
                String val = new String();
                int value = 0;
                val = (String) numeroQ.getText();
                value = Integer.parseInt(val);
                value++;
                numeroQ.setText(""+value);
                Context context = getContext();
                SharedPreferences sharedPref = context.getSharedPreferences("Ok", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.remove(p.getDescr());
                editor.commit();
                editor.putInt(p.getDescr(), value);
                editor.commit();
            }

            public void minusClick() {
                String val = new String();
                int value = 0;
                val = (String) numeroQ.getText();
                value = Integer.parseInt(val);
                if(value!=1){
                    value--;
                }
                else{
                    value=value;
                }
                numeroQ.setText(""+value);
                Context context = getContext();
                SharedPreferences sharedPref = context.getSharedPreferences("Ok", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.remove(p.getDescr());
                editor.commit();
                editor.putInt(p.getDescr(), value);
                editor.commit();
            }
            //FINE MODIFICHE
        });




        numberPicker.setMaxValue(10000);
        numberPicker.setMinValue(1);
        numberPicker.setValue(Integer.parseInt(p.getQnt()));
        numberPicker.setWrapSelectorWheel(false);

        return v;
    }

}
