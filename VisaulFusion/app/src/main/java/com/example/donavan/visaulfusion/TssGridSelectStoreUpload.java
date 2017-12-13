package com.example.donavan.visaulfusion;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.donavan.visaulfusion.R;

import java.util.ArrayList;

public class TssGridSelectStoreUpload extends  ArrayAdapter {

    Context context;
    private ArrayList<AndroidStore> data = new ArrayList<AndroidStore>();


    public TssGridSelectStoreUpload(Context context, ArrayList<AndroidStore> data)
    {
        super(context, 0);
        this.context=context;
        this.data = data;
    }

    @Override
    public int getCount()
    {
        return data.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        View row;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(R.layout.tss_row_select_store, parent, false);
        }
        else{
            row = (View) convertView;
        }

            TextView textViewTitle = (TextView) row.findViewById(R.id.textView2);

            TextView urnNumber = (TextView) row.findViewById(R.id.textViewsub);
            TextView progress = (TextView) row.findViewById(R.id.textViewsub2);




            AndroidStore item = data.get(position);

            //Set control values DANIEL these must change
            urnNumber.setText(item.getURN());
            textViewTitle.setText(item.getStoreName());
            progress.setText(item.getCurrentPhase());



        return row;



    }




}
