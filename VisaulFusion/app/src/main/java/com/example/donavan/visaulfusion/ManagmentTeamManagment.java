package com.example.donavan.visaulfusion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.example.donavan.visaulfusion.R;
public class ManagmentTeamManagment extends AppCompatActivity {

    @Override
    public void onBackPressed() {     }      @Override  protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.managment_team_managment_layout);
    }

    public void TssTeam(View view) {
        Intent intent = new Intent(ManagmentTeamManagment.this, ManagmentTssTeam.class);
        startActivity(intent); finish();
    }

    public void InstallationTeam(View view) {
        Intent intent = new Intent(ManagmentTeamManagment.this, ManagmentTssTeam.class);
        startActivity(intent); finish();
    }

    public void WarehouseTeam(View view) {
        Intent intent = new Intent(ManagmentTeamManagment.this, ManagmentTssTeam.class);
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

            Intent intent = new Intent(ManagmentTeamManagment.this, HomeMenu.class );

            startActivity(intent); finish();

            // return true;
        }



        if (id == R.id.logoutbutton){
            startActivity(new Intent(this,LoginActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }




}

