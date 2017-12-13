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

public class WareHouseUnitary extends AppCompatActivity
{


    GridView gridView;
    WarehouseGridViewUnitary grisViewCustomeAdapter;


    @Override
    public void onBackPressed() {     }      @Override  protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ware_house_unitary_layout);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


      /*  gridView=(GridView)findViewById(R.id.gridViewCustom);
        // Create the Custom Adapter Object
        grisViewCustomeAdapter = new WarehouseGridViewUnitary(this);
        // Set the Adapter to GridView
        gridView.setAdapter(grisViewCustomeAdapter);
*/



    }

    public void continuebtn(View view) {

        Intent intent = new Intent(WareHouseUnitary.this, WareHouseHomeActivity.class );

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

            Intent intent = new Intent(WareHouseUnitary.this, HomeMenu.class );

            startActivity(intent); finish();

            // return true;
        }



        if (id == R.id.logoutbutton){
            startActivity(new Intent(this,LoginActivity.class));
        }

        if (id == R.id.backbutton){
            startActivity(new Intent(this,WareHouseHomeActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }




}