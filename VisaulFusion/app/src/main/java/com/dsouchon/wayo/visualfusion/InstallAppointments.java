package com.dsouchon.wayo.visualfusion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONException;

import java.util.ArrayList;

public class InstallAppointments extends AppCompatActivity {
    private DBManager dbManager;
    GridView gridView;
    InstallGridViewAppointments grisViewCustomeAdapter;
    ArrayList<AndroidAppointment> myrequests;

    @Override
    public void onBackPressed() {

        startActivity(new Intent(this,HomeMenu.class));
        finish();


    }

    /************SET AND GET GLOBAL VARIABLES ******************/
    public static void setDefaults(Context context, String key, String value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
    public static String getDefaultsOld(Context context,String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, "");
    }
    /******************************/

    @Override  protected void onCreate(Bundle savedInstanceState)
    {
         super.onCreate(savedInstanceState);  dbManager = new DBManager(this);        dbManager.open();
        setContentView(R.layout.install_appointments_layout);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        myrequests = getOpenRequestsData();

        gridView=(GridView)findViewById(R.id.gridViewCustom3);
        // Create the Custom Adapter Object
        grisViewCustomeAdapter = new InstallGridViewAppointments(this, myrequests);
        // Set the Adapter to GridView
        gridView.setAdapter(grisViewCustomeAdapter);



        //sets where list item is clicking too
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int pos, long id) {
                //Toast.makeText(getApplicationContext(), names[pos], Toast.LENGTH_LONG).show();

                Intent intent = new Intent(InstallAppointments.this, InstallSetAppointment.class );
                //Sending data to another Activity
                AndroidAppointment appointment = myrequests.get(pos);

                dbManager.setValue( "RequestID", Integer.toString(appointment.getID()));

                intent.putExtra("RequestID", appointment.getID());
                intent.putExtra("Mileage", appointment.getMileage());
                intent.putExtra("StoreID", appointment.getStoreID());

                intent.putExtra("BrandName", appointment.getBrandName());
                intent.putExtra("OutletTypeName", appointment.getOutletTypeName());
                intent.putExtra("TierTypeName", appointment.getTierTypeName());


                intent.putExtra("JobCardCount", appointment.getJobCardCount());
                intent.putExtra("ExteriorImageCount", appointment.getExteriorImageCount());
                intent.putExtra("InteriorImageCount", appointment.getInteriorImageCount());
                intent.putExtra("SurveySignatureCount", appointment.getSurveySignatureCount());
                intent.putExtra("PerformanceReviewSignatureCount", appointment.getPerformanceReviewSignatureCount());



                intent.putExtra("RequestTypeName", appointment.getRequestTypeName());
                intent.putExtra("StoreName", appointment.getStoreName());

                intent.putExtra("StoreNameURN", appointment.getStoreNameURN());

                intent.putExtra("URN", appointment.getURN());


                intent.putExtra("CurrentPhase", appointment.getCurrentPhase());
                intent.putExtra("ContactPerson", appointment.getContactPerson());
                intent.putExtra("ContactEmail", appointment.getContactEmail());
                intent.putExtra("ContactPhone", appointment.getContactPhone());
                intent.putExtra("OpeningTime", appointment.getOpeningTime());
                intent.putExtra("ClosingTime", appointment.getClosingTime());
                intent.putExtra("TotalUnitCount", appointment.getTotalUnitCount());
                intent.putExtra("StoreID", appointment.getStoreID());
                intent.putExtra("DateRequested", appointment.getDateRequested());
                intent.putExtra("DateAccepted", appointment.getDateAccepted());
                intent.putExtra("AppointmentDateTime", appointment.getAppointmentDateTime());
                intent.putExtra("AppointmentDateDay", appointment.getAppointmentDateDay());
                intent.putExtra("AppointmentDateMonth", appointment.getAppointmentDateMonth());
                intent.putExtra("AppointmentDateYear", appointment.getAppointmentDateYear());
                intent.putExtra("AppointmentDateTimeTime", appointment.getAppointmentDateTimeTime());
                intent.putExtra("DateConfirmed", appointment.getDateConfirmed());
                intent.putExtra("DateRecordChanged", appointment.getDateRecordChanged());

                intent.putExtra("Address", appointment.getAddress());
                intent.putExtra("Region", appointment.getRegion());


                startActivity(intent); finish();
            }

        } );


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

            Intent intent = new Intent(InstallAppointments.this, HomeMenu.class );

            startActivity(intent); finish();

            // return true;
        }

       /* if (id == R.id.downloadBtn){
            // startActivity(new Intent(this,MainLogin.class));
            Intent intent = new Intent(InstallAppointments.this, DownloadActivity.class );

            startActivity(intent); finish();

        }*/

        if (id == R.id.logoutbutton){
            startActivity(new Intent(this,LoginActivity.class));
        }

        if (id == R.id.backbutton){
            startActivity(new Intent(this,InstallHome.class));
        }

        return super.onOptionsItemSelected(item);
    }
    private ArrayList<AndroidAppointment> getOpenRequestsData() {
        ArrayList<AndroidAppointment> openRequests = new ArrayList<>();
        String json = "";
        json = dbManager.getValue( "AndroidInsAppointments");
        try {
            openRequests = JsonUtil.parseJsonArrayAndroidAppointment(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return openRequests;
    }
}
