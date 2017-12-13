package com.example.donavan.visaulfusion;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class HomeMenu extends AppCompatActivity {

    @Override
    public void onBackPressed() {     }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homemenu_layout);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        String roles = Local.Get(this.getApplication(), "Roles");


        int counter = 0;
        for( int i=0; i<roles.length(); i++ ) {
            if( roles.charAt(i) == '\n' ) {
                counter++;
            }
        }

        if(counter > 0)//more than one role so show menu
        {
            if (!roles.contains("Rep")) {
                Button btnRep = (Button) findViewById(R.id.btnRep);
                btnRep.setVisibility(View.GONE);
            }
            if (!roles.contains("Tss")) {
                Button btnTss = (Button) findViewById(R.id.btnTss);
                btnTss.setVisibility(View.GONE);
            }
            if (!roles.contains("Whs")) {
                Button btnWhs = (Button) findViewById(R.id.btnWhs);
                btnWhs.setVisibility(View.GONE);
            }
            if (!roles.contains("Ins")) {
                Button btnIns = (Button) findViewById(R.id.btnIns);
                btnIns.setVisibility(View.GONE);
            }
            if (!roles.contains("Srv")) {
                Button btnIns = (Button) findViewById(R.id.btnSrv);
                btnIns.setVisibility(View.GONE);
            }
        }
        else {
            //New fast track for Ins
            if (roles.contains("Ins")) {
                Local.Set(getApplicationContext(), "PrimaryRole", "Ins");
                Intent intent = new Intent(HomeMenu.this, InstallHome.class);
                startActivity(intent);
                finish();
            }

            //New fast track for Srv
            if (roles.contains("Srv")) {
                Local.Set(getApplicationContext(), "PrimaryRole", "Srv");
                Intent intent = new Intent(HomeMenu.this, InstallHome.class);
                startActivity(intent);
                finish();
            }
        }

    }

    public void Login(View view) {
        //Intent intent = new Intent(LoginActivity.this, RepHomeActivity.class );
        Intent intent = new Intent(HomeMenu.this, RepHomeActivity.class );

        startActivity(intent); finish();
    }

    public void TssLogin(View view) {

        Intent intent = new Intent(HomeMenu.this, TssHome.class );
        startActivity(intent); finish();
    }

    public void WareHouseLogin(View view) {

        Intent intent = new Intent(HomeMenu.this, WareHouseHomeActivity.class );
        startActivity(intent); finish();
    }

    public void InstLogin(View view) {

        Local.Set(getApplicationContext(), "PrimaryRole", "Ins");
        Intent intent = new Intent(HomeMenu.this, InstallHome.class);
        startActivity(intent);
        finish();
    }

    public void SrvLogin(View view) {

        Local.Set(getApplicationContext(), "PrimaryRole", "Srv");
        Intent intent = new Intent(HomeMenu.this, InstallHome.class);
        startActivity(intent);
        finish();
    }




}
