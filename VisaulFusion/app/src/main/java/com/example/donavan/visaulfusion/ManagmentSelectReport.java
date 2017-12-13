package com.example.donavan.visaulfusion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.donavan.visaulfusion.R;
public class ManagmentSelectReport extends AppCompatActivity {

    @Override
    public void onBackPressed() {     }      @Override  protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.managment_select_report_layout);
    }

    public void viewReports(View view) {


        Intent intent = new Intent(ManagmentSelectReport.this, ManagmentReportViews.class );

        startActivity(intent); finish();
    }
}
