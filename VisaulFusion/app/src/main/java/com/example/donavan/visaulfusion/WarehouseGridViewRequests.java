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

public class WarehouseGridViewRequests extends   ArrayAdapter <AndroidOpenRequest> {

    Context context;
    private ArrayList<AndroidOpenRequest> data = new ArrayList<AndroidOpenRequest>();


    public WarehouseGridViewRequests(Context context, ArrayList<AndroidOpenRequest> data)
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
        View row = convertView;

        if (row == null)
        {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(R.layout.warehouse_row_requests, parent, false);


            TextView txtURN = (TextView) row.findViewById(R.id.txtURN);

            TextView txtStoreName = (TextView) row.findViewById(R.id.txtStoreName);
            TextView txtProgress = (TextView) row.findViewById(R.id.txtProgress);

            AndroidOpenRequest item = data.get(position);

            //Set control values DANIEL these must change
            txtURN.setText(item.getURN());
            txtStoreName.setText(item.getStoreName());
            txtProgress.setText(item.getCurrentPhase());





        }







        return row;



    }




}
