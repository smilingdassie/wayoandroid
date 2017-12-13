package com.example.donavan.visaulfusion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.donavan.visaulfusion.R;
public class ManagmentSelectOverall extends AppCompatActivity {

    @Override
    public void onBackPressed() {     }      @Override  protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.managment_select_overall_layout);
    }

    public void viewReports(View view) {
        Intent intent = new Intent(ManagmentSelectOverall.this, ManagmentViewOverallReport.class );
        startActivity(intent); finish();


    }
}
