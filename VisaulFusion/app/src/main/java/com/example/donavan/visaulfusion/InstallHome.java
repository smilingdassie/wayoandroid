package com.example.donavan.visaulfusion;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.Menu;
import android.widget.Toast;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


public class InstallHome extends AppCompatActivity {
    public boolean mIsSurvey = false;


    @Override
    public void onBackPressed() {     }

    @Override  protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.install_home);

        //new check for is survey
        String primaryRole = Local.Get( getApplicationContext(), "PrimaryRole");
        if (primaryRole.equals("Srv")){
            mIsSurvey = true;
        }

        if(mIsSurvey) {
            //getActionBar().setTitle("Survey Unitary List");
            getSupportActionBar().setTitle("Survey Team Home");  //
        }

        Date date = new Date();
        String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
        Local.Set(getApplicationContext(), "Today", modifiedDate);

        Button btnUnitaryLists = (Button) findViewById(R.id.UnitaryLists);
        btnUnitaryLists.setVisibility(View.INVISIBLE);

        Button InstallAppoint = (Button) findViewById(R.id.InstallAppoint);
        InstallAppoint.setVisibility(View.INVISIBLE);

        String isWebsiteAvailable = Local.Get(getApplicationContext(), "AmIOnline");

        if (isWebsiteAvailable.equals("True")) {

            if(!mIsSurvey) {
                GetAppointmentsForUser(Local.Get(this.getApplication(), "UserName"), "openinsappointment");
                AndroidDocumentTypes();
            }
            else {
                GetAppointmentsForUser(Local.Get(this.getApplication(), "UserName"), "opensrvappointment");
            }


            if(!mIsSurvey) {
                GetStoresForUser(Local.Get(this.getApplication(), "UserName"), "installation scheduled");
            }
            else {
                GetStoresForUser(Local.Get(this.getApplication(), "UserName"), "ess survey scheduled");
            }

        }
        else{
            //I am Offline
            //Use locally saved lists

        }
    }

    private ArrayList<AndroidStore> getStoresData() {
        ArrayList<AndroidStore> stores = new ArrayList<>();
        String json = "[\n" +
                "  {\n" +
                "    \"ID\": 1041,\n" +
                "    \"StoreName\": \"TEST Daniel\",\n" +
                "    \"UserName\": \"dsouchon@gmail.com\",\n" +
                "    \"ContactPerson\": \"Daniel Souchon\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"ID\": 1042,\n" +
                "    \"StoreName\": \"Test Don1\",\n" +
                "    \"UserName\": \"dsouchon@gmail.com\",\n" +
                "    \"ContactPerson\": \"Donavan\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"ID\": 1043,\n" +
                "    \"StoreName\": \"TEST Roadhouse Grill Hurlingham\",\n" +
                "    \"UserName\": \"dsouchon@gmail.com\",\n" +
                "    \"ContactPerson\": \"Robert Kingori\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"ID\": 1044,\n" +
                "    \"StoreName\": \"TEST Soggybottom INN\",\n" +
                "    \"UserName\": \"dsouchon@gmail.com\",\n" +
                "    \"ContactPerson\": \"Saggy Sogbottom\"\n" +
                "  }]";


        json = Local.Get(getApplicationContext(), "AndroidStoresIns");

        try {
            stores = JsonUtil.parseJsonArrayAndroidStore(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return stores;
    }



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

            Intent intent = new Intent(InstallHome.this, HomeMenu.class );

            startActivity(intent); finish();

            // return true;
        }


        if (id == R.id.logoutbutton){
            startActivity(new Intent(this,LoginActivity.class));
        }

        if (id == R.id.backbutton){
            startActivity(new Intent(this,HomeMenu.class));
        }

        return super.onOptionsItemSelected(item);
    }


    public void InstallAppointments(View view) {

        Intent intent = new Intent(InstallHome.this, InstallAppointments.class );
        startActivity(intent); finish();
    }

    public void UnitaryList(View view) {

        Intent intent = new Intent(InstallHome.this, InstallSelectStoreForUnitry.class );
        startActivity(intent); finish();
    }


    public void GetStoresForUser(String UserName, String Phase) {

        String isWebsiteAvailable = Local.Get(getApplicationContext(), "AmIOnline");

        if(isWebsiteAvailable.equals("True")) {

            final AlertDialog ad = new AlertDialog.Builder(this).create();
            MySOAPCallActivity cs = new MySOAPCallActivity();
            try {

                GetStoresForUserParams params = new GetStoresForUserParams(cs, UserName, Phase);

                new CallSoapGetStoresForUser().execute(params);


            } catch (Exception ex) {
                ad.setTitle("Error!");
                ad.setMessage(ex.toString());
            }
            ad.show();

        }
        else {
            //I am OFFLINE
            //Do Nothing - you'll just use the stores you have :)


        }
    }

    public void GetAppointmentsForUser(String UserName, String RequestType) {

        final AlertDialog ad=new AlertDialog.Builder(this).create();
        MySOAPCallActivity cs = new MySOAPCallActivity();
        try {

            GetAppointmentsForUserParams params = new GetAppointmentsForUserParams(cs, UserName, RequestType);

            new CallSoapGetAppointmentsForUser().execute(params);


        } catch (Exception ex) {
            ad.setTitle("Error!");
            ad.setMessage(ex.toString());
        }
        ad.show();


    }

    public void AndroidDocumentTypes() {

        final AlertDialog ad=new AlertDialog.Builder(this).create();
        MySOAPCallActivity cs = new MySOAPCallActivity();
        try {

            AndroidDocumentTypesParams params = new AndroidDocumentTypesParams(cs);

            new CallSoapAndroidDocumentTypes().execute(params);


        } catch (Exception ex) {
            ad.setTitle("Error!");
            ad.setMessage(ex.toString());
        }
        ad.show();


    }

//Soap region

    public class CallSoapGetAppointmentsForUser extends AsyncTask<InstallHome.GetAppointmentsForUserParams, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(InstallHome.GetAppointmentsForUserParams... params) {
            return params[0].foo.GetAppointmentsForUser(params[0].username, params[0].requestType);
        }

        protected void onPostExecute(String result) {
            // TODO: check this.exception
            // TODO: do something with the feed
            try {
                //process Json Result
                //Save results in local storage
                if(result.toLowerCase().contains("error")){

                }
                else {
                    Local.Set(getApplicationContext(), "AndroidInsAppointments", result);
                }

            } catch (Exception ex) {
                String e3 = ex.toString();
            }

        }



    }
    private static class GetAppointmentsForUserParams {
        MySOAPCallActivity foo;
        String username;
        String requestType;



        GetAppointmentsForUserParams(MySOAPCallActivity foo, String username, String requestType) {
            this.foo = foo;
            this.username = username;
            this.requestType = requestType;


        }
    }

    public class CallSoapGetStoresForUser extends AsyncTask<GetStoresForUserParams, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(GetStoresForUserParams... params) {
            return params[0].foo.GetStoresForUser(params[0].username, params[0].phase);
        }

        protected void onPostExecute(String result) {
            // TODO: check this.exception
            // TODO: do something with the feed
            try {
                //process Json Result
                //Save results in local storage
                if(result.toLowerCase().contains("error")){

                }
                else {
                    Local.Set(getApplicationContext(), "AndroidStoresIns", result);

                    String Today =  Local.Get(getApplicationContext(), "Today");
                    String resetcheckins = Local.Get(getApplicationContext(), "ResetAllCheckins");
                    if(resetcheckins.equals("True")) {
                        ArrayList<AndroidStore> stores = getStoresData();
                        Iterator<AndroidStore> it = stores.iterator();
                        while (it.hasNext()) {
                            AndroidStore user = it.next();
                            String URN = user.getURN();
                            Local.Set(getApplicationContext(),URN+":CheckedIn:"+Today, "");
                            Local.Set(getApplicationContext(),URN+":CheckedOut:"+Today, "");
                        }
                    }


                    Intent intent = new Intent(InstallHome.this, InstallAppointments.class );
                    startActivity(intent); finish();

                }

            } catch (Exception ex) {
                String e3 = ex.toString();
            }

        }



    }
    private static class GetStoresForUserParams {
        MySOAPCallActivity foo;
        String username;
        String phase;



        GetStoresForUserParams(MySOAPCallActivity foo, String username, String phase) {
            this.foo = foo;
            this.username = username;
            this.phase = phase;


        }
    }

    public class CallSoapAndroidDocumentTypes extends AsyncTask<AndroidDocumentTypesParams, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(AndroidDocumentTypesParams... params) {
            return params[0].foo.AndroidDocumentTypes();
        }

        protected void onPostExecute(String result) {
            // TODO: check this.exception
            // TODO: do something with the feed
            try {
                //process Json Result
                //Save results in local storage
                if(result.toLowerCase().contains("error")){

                }
                else {
                    Local.Set(getApplicationContext(), "AndroidDocumentTypes", result);
                }

            } catch (Exception ex) {
                String e3 = ex.toString();
            }

        }



    }
    private static class AndroidDocumentTypesParams {
        MySOAPCallActivity foo;




        AndroidDocumentTypesParams (MySOAPCallActivity foo) {
            this.foo = foo;


        }
    }


}
