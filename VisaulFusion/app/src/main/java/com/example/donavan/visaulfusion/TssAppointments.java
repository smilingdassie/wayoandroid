package com.example.donavan.visaulfusion;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONException;

import java.util.ArrayList;

public class TssAppointments extends AppCompatActivity {



    GridView gridView;
    TssGridViewAppointments grisViewCustomeAdapter;

    ArrayList<AndroidAppointment> myrequests;

    @Override
    public void onBackPressed() {     }      @Override  protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tss_appointments_layout);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        myrequests = getOpenRequestsData();

        gridView=(GridView)findViewById(R.id.gridViewCustom3);
        // Create the Custom Adapter Object
        grisViewCustomeAdapter = new TssGridViewAppointments(this, myrequests);
        // Set the Adapter to GridView
        gridView.setAdapter(grisViewCustomeAdapter);



        //sets where list item is clicking too
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int pos, long id) {
                //Toast.makeText(getApplicationContext(), names[pos], Toast.LENGTH_LONG).show();

                Intent intent = new Intent(TssAppointments.this, TssSetAppointment.class );
                //Sending data to another Activity

                AndroidAppointment store = myrequests.get(pos);
                intent.putExtra("ID", store.getID());
                intent.putExtra("RequestTypeName", store.getRequestTypeName());
                intent.putExtra("StoreName", store.getStoreName());
                intent.putExtra("StoreNameURN", store.getStoreNameURN());
                intent.putExtra("URN", store.getURN());
                intent.putExtra("CurrentPhase", store.getCurrentPhase());
                intent.putExtra("ContactPerson", store.getContactPerson());
                intent.putExtra("ContactEmail", store.getContactEmail());
                intent.putExtra("ContactPhone", store.getContactPhone());
                intent.putExtra("OpeningTime", store.getOpeningTime());
                intent.putExtra("ClosingTime", store.getClosingTime());
                intent.putExtra("TotalUnitCount", store.getTotalUnitCount());
                intent.putExtra("StoreID", store.getStoreID());
                intent.putExtra("DateRequested", store.getDateRequested());
                intent.putExtra("DateAccepted", store.getDateAccepted());
                intent.putExtra("AppointmentDateTime", store.getAppointmentDateTime());
                intent.putExtra("AppointmentDateDay", store.getAppointmentDateDay());
                intent.putExtra("AppointmentDateMonth", store.getAppointmentDateMonth());
                intent.putExtra("AppointmentDateYear", store.getAppointmentDateYear());
                intent.putExtra("AppointmentDateTimeTime", store.getAppointmentDateTimeTime());
                intent.putExtra("DateConfirmed", store.getDateConfirmed());
                intent.putExtra("DateRecordChanged", store.getDateRecordChanged());



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

            Intent intent = new Intent(TssAppointments.this, HomeMenu.class );

            startActivity(intent); finish();

            // return true;
        }



        if (id == R.id.logoutbutton){
             startActivity(new Intent(this,LoginActivity.class));
        }


        if (id == R.id.backbutton){
            startActivity(new Intent(this,TssHome.class));
        }

        return super.onOptionsItemSelected(item);
    }

    private ArrayList<AndroidAppointment> getOpenRequestsData() {
        ArrayList<AndroidAppointment> openRequests = new ArrayList<>();
        String json = "[\n" +
                "  {\n" +
                "    \"ID\": 63,\n" +
                "    \"RequestTypeName\": \"Technical Site Survey\",\n" +
                "    \"StoreID\": 1042,\n" +
                "    \"AppointmentDateTime\": null,\n" +
                "    \"DateRequested\": null,\n" +
                "    \"DateAccepted\": null,\n" +
                "    \"UserName\": \"dsouchon@gmail.com\",\n" +
                "    \"DateConfirmed\": null\n" +
                "  },\n" +
                "  {\n" +
                "    \"ID\": 70,\n" +
                "    \"RequestTypeName\": \"Technical Site Survey\",\n" +
                "    \"StoreID\": 1041,\n" +
                "    \"AppointmentDateTime\": null,\n" +
                "    \"DateRequested\": null,\n" +
                "    \"DateAccepted\": null,\n" +
                "    \"UserName\": \"dsouchon@gmail.com\",\n" +
                "    \"DateConfirmed\": null\n" +
                "  }\n" +
                "]";
        json = Local.Get(getApplicationContext(), "AndroidTssAppointments");
        try {
            openRequests = JsonUtil.parseJsonArrayAndroidAppointment(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return openRequests;
    }
}