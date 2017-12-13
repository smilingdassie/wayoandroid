package com.example.donavan.visaulfusion;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class TssHome extends AppCompatActivity {

    @Override
    public void onBackPressed() {     }      @Override  protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tss_home_layout);
        Button btnUnitaryLists = (Button) findViewById(R.id.UnitaryLists);
        btnUnitaryLists.setVisibility(View.INVISIBLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        String isWebsiteAvailable = Local.Get(getApplicationContext(), "AmIOnline");

        if(isWebsiteAvailable.equals("True")) {



            GetOpenRequestsForUser(Local.Get(this.getApplication(), "UserName"), "opentssrequest");

            GetAppointmentsForUser(Local.Get(this.getApplication(), "UserName"), "opentssappointment");

            GetUnitListsForUser1(Local.Get(this.getApplication(), "UserName"), "awaiting tss");

            GetStoresForUser(Local.Get(this.getApplication(), "UserName"), "awaiting tss");
        }
        else{
            //I am Offline
            //Use locally saved lists
            btnUnitaryLists.setVisibility(View.VISIBLE);
        }
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

            Intent intent = new Intent(TssHome.this, HomeMenu.class );

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


    public void requests(View view) {

        Intent intent = new Intent(TssHome.this, TssOpenRequests.class );
        startActivity(intent); finish();
    }

    public void appointments(View view) {

        Intent intent = new Intent(TssHome.this, TssAppointments.class );
        startActivity(intent); finish();
    }

    public void UnitaryList(View view) {

        Intent intent = new Intent(TssHome.this, TssSelectStoreForUnitry.class );
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


    public void GetOpenRequestsForUser(String UserName, String RequestType) {

        final AlertDialog ad=new AlertDialog.Builder(this).create();
        MySOAPCallActivity cs = new MySOAPCallActivity();
        try {

            GetOpenRequestsForUserParams params = new GetOpenRequestsForUserParams(cs, UserName, RequestType);

            new CallSoapGetOpenRequestsForUser().execute(params);


        } catch (Exception ex) {
            ad.setTitle("Error!");
            ad.setMessage(ex.toString());
        }
        ad.show();


    }

    public class CallSoapGetOpenRequestsForUser extends AsyncTask<TssHome.GetOpenRequestsForUserParams, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(TssHome.GetOpenRequestsForUserParams... params) {
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
                    Local.Set(getApplicationContext(), "AndroidTssOpenRequests", "");
                    Local.Set(getApplicationContext(), "AndroidTssOpenRequests", result);
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

    public class CallSoapGetAppointmentsForUser extends AsyncTask<TssHome.GetAppointmentsForUserParams, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(TssHome.GetAppointmentsForUserParams... params) {
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
                    Local.Set(getApplicationContext(), "AndroidTssAppointments", "");
                    Local.Set(getApplicationContext(), "AndroidTssAppointments", result);
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
                if(result.toLowerCase().contains("error")){
                    Button btnUnitaryLists = (Button) findViewById(R.id.UnitaryLists);
                    btnUnitaryLists.setVisibility(View.INVISIBLE);
                }
                else {
                    Local.Set(getApplicationContext(), "AndroidStoreUnitsTss", result);
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
                if(result.toLowerCase().contains("error")){

                }
                else {
                    Local.Set(getApplicationContext(), "AndroidStoresTss", result);
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
