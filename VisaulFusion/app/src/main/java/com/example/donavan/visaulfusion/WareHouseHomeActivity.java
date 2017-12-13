package com.example.donavan.visaulfusion;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class WareHouseHomeActivity extends AppCompatActivity {

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

            Intent intent = new Intent(WareHouseHomeActivity.this, HomeMenu.class );

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

    @Override
    public void onBackPressed() {     }      @Override  protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ware_house_home_layout);
        Button btnUnitaryLists = (Button) findViewById(R.id.UnitaryLists);
        btnUnitaryLists.setVisibility(View.INVISIBLE);
        String isWebsiteAvailable = Local.Get(getApplicationContext(), "AmIOnline");

        if (isWebsiteAvailable.equals("True")) {
        GetOpenRequestsForUser(Local.Get(this.getApplication(), "UserName"), "openwhsrequest");

        GetAppointmentsForUser(Local.Get(this.getApplication(), "UserName"), "openwhsappointment");

            GetUnitListsForUser1(Local.Get(this.getApplication(), "UserName"), "awaiting dispatch");

            GetStoresForUser(Local.Get(this.getApplication(), "UserName"), "awaiting dispatch");


        }
        else{
            //I am Offline
            //Use locally saved lists
            btnUnitaryLists.setVisibility(View.VISIBLE);
        }
    }

    public void ViewPick(View view) {
        Intent intent = new Intent(WareHouseHomeActivity.this, WarehouseOpenRequests.class );//Open Requests
        startActivity(intent); finish();


    }

    public void PickRequests(View view) {
        Intent intent = new Intent(WareHouseHomeActivity.this, WarehouseViewAccepted.class );//Appointments
        startActivity(intent); finish();
    }

    public void UnitaryList(View view) {
        Intent intent = new Intent(WareHouseHomeActivity.this, WarehouseSelectStoreForUnitry.class );
        startActivity(intent); finish();
    }

    public void GetOpenRequestsForUser(String UserName, String RequestType) {

        final AlertDialog ad=new AlertDialog.Builder(this).create();
        MySOAPCallActivity cs = new MySOAPCallActivity();
        try {

            WareHouseHomeActivity.GetOpenRequestsForUserParams params = new WareHouseHomeActivity.GetOpenRequestsForUserParams(cs, UserName, RequestType);

            new WareHouseHomeActivity.CallSoapGetOpenRequestsForUser().execute(params);


        } catch (Exception ex) {
            ad.setTitle("Error!");
            ad.setMessage(ex.toString());
        }
        ad.show();


    }

    public class CallSoapGetOpenRequestsForUser extends AsyncTask<WareHouseHomeActivity.GetOpenRequestsForUserParams, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(WareHouseHomeActivity.GetOpenRequestsForUserParams... params) {
            return params[0].foo.GetOpenRequestsForUser(params[0].username, params[0].requestType);
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
                    Local.Set(getApplicationContext(), "AndroidWhsOpenRequests", "");
                    Local.Set(getApplicationContext(), "AndroidWhsOpenRequests", result);
                }

            } catch (Exception ex) {
                String e3 = ex.toString();
            }

        }



    }
    private static class GetOpenRequestsForUserParams {
        MySOAPCallActivity foo;
        String username;
        String requestType;



        GetOpenRequestsForUserParams(MySOAPCallActivity foo, String username, String requestType) {
            this.foo = foo;
            this.username = username;
            this.requestType = requestType;


        }
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

            WareHouseHomeActivity.GetAppointmentsForUserParams params = new WareHouseHomeActivity.GetAppointmentsForUserParams(cs, UserName, RequestType);

            new WareHouseHomeActivity.CallSoapGetAppointmentsForUser().execute(params);


        } catch (Exception ex) {
            ad.setTitle("Error!");
            ad.setMessage(ex.toString());
        }
        ad.show();


    }

    public class CallSoapGetAppointmentsForUser extends AsyncTask<WareHouseHomeActivity.GetAppointmentsForUserParams, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(WareHouseHomeActivity.GetAppointmentsForUserParams... params) {
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
                    Local.Set(getApplicationContext(), "AndroidWhsAppointments", result);
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


    public void GetUnitListsForUser1(String UserName, String PhaseMatters) {

        String isWebsiteAvailable = Local.Get(getApplicationContext(), "AmIOnline");

        if(isWebsiteAvailable.equals("True")) {

            final AlertDialog ad = new AlertDialog.Builder(this).create();
            MySOAPCallActivity cs = new MySOAPCallActivity();
            try {

                GetUnitListsForUserParams params = new GetUnitListsForUserParams(cs, UserName, PhaseMatters);

                new CallSoapGetUnitListsForUser().execute(params);


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

    public class CallSoapGetUnitListsForUser extends AsyncTask<GetUnitListsForUserParams, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(GetUnitListsForUserParams... params) {
            return params[0].foo.GetUnitListsForUser1(params[0].username, params[0].phasematters);
        }

        protected void onPostExecute(String result) {
            // TODO: check this.exception
            // TODO: do something with the feed
            try {
                //process Json Result
                //Save results in local storage
                if(result.toLowerCase().contains("error")||result.toLowerCase().contains("exception")){
                    Button btnUnitaryLists = (Button) findViewById(R.id.UnitaryLists);
                    btnUnitaryLists.setVisibility(View.INVISIBLE);
                }
                else {
                    Local.Set(getApplicationContext(), "AndroidStoreUnitsWhs", result);
                    Button btnUnitaryLists = (Button) findViewById(R.id.UnitaryLists);
                    btnUnitaryLists.setVisibility(View.VISIBLE);
                }

            } catch (Exception ex) {
                String e3 = ex.toString();
            }

        }



    }
    private static class GetUnitListsForUserParams {
        MySOAPCallActivity foo;
        String username;
        String phasematters;



        GetUnitListsForUserParams(MySOAPCallActivity foo, String username, String phasematters) {
            this.foo = foo;
            this.username = username;
            this.phasematters = phasematters;


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
                if(result.toLowerCase().contains("error")||result.toLowerCase().contains("exception")){
                    String bob = result;
                }
                else {
                    Local.Set(getApplicationContext(), "AndroidStoresWhs", result);
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



}
