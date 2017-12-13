package com.example.donavan.visaulfusion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.donavan.visaulfusion.R;
public class ManagementHome extends AppCompatActivity {

    @Override
    public void onBackPressed() {     }      @Override  protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.management_home_layout);
    }

    public void BrandReport(View view) {

        Intent intent = new Intent(ManagementHome.this, ManagmentSelectBrand.class );

        startActivity(intent); finish();

    }

    public void OveralReport(View view) {


        Intent intent = new Intent(ManagementHome.this, ManagmentSelectOverall.class );

        startActivity(intent); finish();

    }

    public void SearchStores(View view) {

        Intent intent = new Intent(ManagementHome.this, ManagmentSearch.class );

        startActivity(intent); finish();
    }

    public void TeamManagment(View view) {
        Intent intent = new Intent(ManagementHome.this, ManagmentTeamManagment.class );

        startActivity(intent); finish();
    }
}
