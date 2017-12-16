package com.vf.admin.visualfusion;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLoadScreen extends Fragment {


    public FragmentLoadScreen() {
        // Required empty public constructor
    }

    Button BtnGoTo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_load_screen, container, false);

        BtnGoTo = (Button)view.findViewById(R.id.buttonToScan);

        BtnGoTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FragmentLoadScreen.this.getActivity(), "Scan Barcode",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), BarcodeScannerNew.class);
                startActivity(intent);


            }
        });

        return view;






    }

}
