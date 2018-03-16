package com.example.donavan.visaulfusion;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import com.example.donavan.visaulfusion.R;
import com.example.donavan.visaulfusion.R;

import org.json.JSONException;

import java.util.ArrayList;

public class TssUnitaryList extends AppCompatActivity
{


    GridView gridView;
    TssGridViewUnitary grisViewCustomeAdapter;
    ArrayList<AndroidStoreUnit> myunits;

    @Override
    public void onBackPressed() {     }      @Override  protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tss_unitary_list_layout);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Intent me = getIntent();
        String storeNameURN = me.getStringExtra("StoreNameURN");

        myunits = getOpenRequestsData(storeNameURN);


        gridView=(GridView)findViewById(R.id.gridViewCustom);
        // Create the Custom Adapter Object
        grisViewCustomeAdapter = new TssGridViewUnitary(this, myunits);
        // Set the Adapter to GridView
        gridView.setAdapter(grisViewCustomeAdapter);




    }

    public void Close(View view) {

        Intent intent = new Intent(TssUnitaryList.this, TssSelectStoreForUnitry.class );

        startActivity(intent); finish();
    }


    //this is the main menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        if (id == R.id.homebutton) {

            Intent intent = new Intent(TssUnitaryList.this, HomeMenu.class );

            startActivity(intent); finish();

            // return true;
        }


        if (id == R.id.logoutbutton){
            startActivity(new Intent(this,LoginActivity.class));
        }

        if (id == R.id.backbutton){
            startActivity(new Intent(this,TssSelectStoreForUnitry.class));
        }

        return super.onOptionsItemSelected(item);
    }


    private ArrayList<AndroidStoreUnit> getOpenRequestsData(String storeNameURN) {
        ArrayList<AndroidStoreUnit> openRequests = new ArrayList<>();
        String json = "[]";
        json = Local.Get(getApplicationContext(), "AndroidStoreUnitsTss");
        try {
            openRequests = JsonUtil.parseJsonArrayAndroidStoreUnit(json, storeNameURN);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return openRequests;
    }


}