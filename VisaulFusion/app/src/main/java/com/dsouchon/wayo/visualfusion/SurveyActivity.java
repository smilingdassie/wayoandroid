package com.dsouchon.wayo.visualfusion;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;



/**
 * Created by Donavan on 2017/01/16.
 */

public class SurveyActivity extends AppCompatActivity {
    Button searchbtn;
    String mURN;
    private Spinner spinner2;
    private DBManager dbManager;
    private TextView tvDisplayTime;
    private TimePicker timePicker1;
    private Button btnChangeTime;
    private TextView tvDisplayTime2;
    private TimePicker timePicker2;
    private Button btnChangeTime2;
    private TextView tvDisplayTime3;
    private TimePicker timePicker3;
    private Button btnChangeTime3;

    private int hour;
    private int minute;
    private int hour2;
    private int minute2;
    private int hour3;
    private int minute3;


    static final int TIME_DIALOG_ID = 999;
    static final int TIME_DIALOG_ID2 = 888;
    static final int TIME_DIALOG_ID3 = 777;


    private AndroidSurvey survey = new AndroidSurvey();
    public static final String PREFS_NAME = "MyPrefsFile";
    GsonBuilder gsonb = new GsonBuilder();
    Gson mGson = gsonb.create();

    public boolean mIsCommittedToInstallation;
    public int mSurveyID;
    public int mStoreID;
    public int RequestID;

    EditText editTalkingTo;
//    public void SaveSurvey(View view) {
//        Intent intent = new Intent(SurveyActivity.this, SurveyUploadMenu.class );
//
//        startActivity(intent); finish();
//    }

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

            Intent intent = new Intent(SurveyActivity.this, HomeMenu.class );

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
            GoToInstallSetAppointment();
        }

        return super.onOptionsItemSelected(item);
    }

    public AndroidAppointment findAppointmentByID(int id, ArrayList<AndroidAppointment> requests){
        for (AndroidAppointment request : requests) {
            if (request.getID() == id) {
                return request;
            }
        }
        return null;
    }
    private void GoToInstallSetAppointment()
    {
        try{
        Intent intent = new Intent(SurveyActivity.this, InstallSetAppointment.class );

        //Sending data to another Activity
        String storesString = dbManager.getValue( "AndroidInsAppointments");

        ArrayList<AndroidAppointment> appointments = JsonUtil.parseJsonArrayAndroidAppointment(storesString);
            RequestID = Integer.parseInt(dbManager.getValue( "RequestID"));

            //Convert request to appointment
        AndroidAppointment appointment = findAppointmentByID(RequestID, appointments);
        intent.putExtra("ID", appointment.getID());
        intent.putExtra("Mileage", appointment.getMileage());
        intent.putExtra("StoreID", appointment.getStoreID());


        intent.putExtra("RequestTypeName", appointment.getRequestTypeName());
        intent.putExtra("StoreName", appointment.getStoreName());
        intent.putExtra("BrandName", appointment.getBrandName());
        intent.putExtra("OutletTypeName", appointment.getOutletTypeName());
        intent.putExtra("TierTypeName", appointment.getTierTypeName());

        intent.putExtra("StoreNameURN", appointment.getStoreNameURN());

        intent.putExtra("URN", appointment.getURN());

            intent.putExtra("JobCardCount", appointment.getJobCardCount());
            intent.putExtra("ExteriorImageCount", appointment.getExteriorImageCount());
            intent.putExtra("InteriorImageCount", appointment.getInteriorImageCount());
            intent.putExtra("SurveySignatureCount", appointment.getSurveySignatureCount());
            intent.putExtra("PerformanceReviewSignatureCount", appointment.getPerformanceReviewSignatureCount());

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

        intent.putExtra("BrandName", appointment.getBrandName());
        intent.putExtra("OutletTypeName", appointment.getOutletTypeName());
        intent.putExtra("TierTypeName", appointment.getTierTypeName());
            intent.putExtra("JobCardCount", appointment.getJobCardCount());
            intent.putExtra("ExteriorImageCount", appointment.getExteriorImageCount());
            intent.putExtra("InteriorImageCount", appointment.getInteriorImageCount());
            intent.putExtra("SurveySignatureCount", appointment.getSurveySignatureCount());
            intent.putExtra("PerformanceReviewSignatureCount", appointment.getPerformanceReviewSignatureCount());

        startActivity(intent); finish();



    } catch (Exception ex) {
        Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG).show();
    }



}


    public AndroidSurvey readJsonLocalSurvey(String yourSettingName)
    {
        SharedPreferences mSettings = getSharedPreferences(PREFS_NAME, 0);
        String loadValue = mSettings.getString(yourSettingName, "");
        AndroidSurvey c = mGson.fromJson(loadValue, AndroidSurvey.class);

        return c;
    }


    public boolean saveJsonLocalSurvey(String jsonStore, String yourSettingName)
    {
        SharedPreferences mSettings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor mEditor = mSettings.edit();
        try {

            mEditor.putString(yourSettingName, jsonStore);
            mEditor.commit();
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }



    private void SaveSurvey() throws JSONException
    {
        //save more to store
        survey = new AndroidSurvey();//readJsonLocalSurvey("Survey1");

        Spinner spinnerBrand = (Spinner) findViewById(R.id.spinnerBrand);
        survey.setBrandID(spinnerBrand .getSelectedItemPosition());

        Spinner spinnerOpeningTime = (Spinner) findViewById(R.id.spinnerOpeningTime);
        survey.setOpeningTime(spinnerOpeningTime .getSelectedItemPosition());

        Spinner spinnerClosingTime = (Spinner) findViewById(R.id.spinnerClosingTime);
        survey.setClosingTime(spinnerClosingTime .getSelectedItemPosition());

        Spinner spinnerPreferredDeliveryTime = (Spinner) findViewById(R.id.spinnerPreferredDeliveryTime);
        survey.setPreferredDeliveryTime(spinnerPreferredDeliveryTime .getSelectedItemPosition());

        Intent me = getIntent();
        survey.ID = me.getIntExtra("ID", 0);
        survey.RequestID = me.getIntExtra("RequestID", 0);
        survey.SurveyorUserName = dbManager.getValue( "UserName");

        EditText TalkingToPerson = (EditText) findViewById(R.id.editTalkingToPerson);
        survey.setTalkingToPerson(TalkingToPerson.getText().toString());
        EditText TalkingToRole = (EditText) findViewById(R.id.editTalkingToRole);
        survey.setTalkingToRole(TalkingToRole.getText().toString());
        //radio button stuff


        Spinner spinnerYesNo = (Spinner) findViewById(R.id.spinnerYesNo);
        Integer intYesNO = spinnerYesNo .getSelectedItemPosition();

        if(intYesNO == 1) mIsCommittedToInstallation = true;
        if(intYesNO == 2) mIsCommittedToInstallation = false;

        survey.setIsCommittedToInstallation(mIsCommittedToInstallation);

        EditText Comments = (EditText) findViewById(R.id.editComments);
        survey.setComments(Comments.getText().toString());

        EditText WhyNotCommitted = (EditText) findViewById(R.id.editWhyNotCommitted);
        survey.setWhyNotCommitted(WhyNotCommitted.getText().toString());

        String jsonStore = JsonUtil.toJSonAndroidSurvey(survey);
        saveJsonLocalSurvey(jsonStore, "Survey1");

        SaveSurveyToArray(survey);


        String isWebsiteAvailable = dbManager.getValue( "AmIOnline");

        if(isWebsiteAvailable.equals("True")) {
            //Save My Appointments - this will also 'delete' the request online
            MySOAPCallActivity cs1 = new MySOAPCallActivity();
            try {


                String surveys = dbManager.getValue( "AndroidSurveys");
                SaveSurveyParams params = new SaveSurveyParams(cs1, surveys);

                new CallSoapSaveSurvey().execute(params);
            } catch (Exception ex) {


            }

        }
        //Toast store saved locally - will sync when back in range

        //Go back to Rep Home
    }

    public void SaveSurvey(View view) throws JSONException {
        if(ValidateSurvey()) {
            SaveSurvey();
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioYes:
                if (checked)
                    mIsCommittedToInstallation = true;
                TextView txtWhyNotCommitted1 = (TextView)findViewById(R.id.txtWhyNotCommitted);
                txtWhyNotCommitted1.setVisibility(View.GONE);
                EditText editWhyNotCommitted1 = (EditText)findViewById(R.id.editWhyNotCommitted);
                editWhyNotCommitted1.setVisibility(View.GONE);
                    break;
            case R.id.radioNo:
                if (checked)
                    mIsCommittedToInstallation = false;
                    TextView txtWhyNotCommitted = (TextView)findViewById(R.id.txtWhyNotCommitted);
                    txtWhyNotCommitted.setVisibility(View.VISIBLE);
                EditText editWhyNotCommitted = (EditText)findViewById(R.id.editWhyNotCommitted);
                editWhyNotCommitted.setVisibility(View.VISIBLE);
                    break;
            default: mIsCommittedToInstallation = false;
                break;
        }
    }


    public void onYesNoSpinnerChanged() {
        // Is the button now checked?

        Spinner spinnerYesNo = (Spinner) findViewById(R.id.spinnerYesNo);
        Integer intYesNO = spinnerYesNo .getSelectedItemPosition();

        if(intYesNO == 1) mIsCommittedToInstallation = true;
        if(intYesNO == 2) mIsCommittedToInstallation = false;


        // Check which radio button was clicked
        switch(intYesNO) {
            case 1:

                mIsCommittedToInstallation = true;
                TextView txtWhyNotCommitted1 = (TextView)findViewById(R.id.txtWhyNotCommitted);
                txtWhyNotCommitted1.setVisibility(View.GONE);
                EditText editWhyNotCommitted1 = (EditText)findViewById(R.id.editWhyNotCommitted);
                editWhyNotCommitted1.setVisibility(View.GONE);
                break;
            case 2:

                mIsCommittedToInstallation = false;
                TextView txtWhyNotCommitted = (TextView)findViewById(R.id.txtWhyNotCommitted);
                txtWhyNotCommitted.setVisibility(View.VISIBLE);
                EditText editWhyNotCommitted = (EditText)findViewById(R.id.editWhyNotCommitted);
                editWhyNotCommitted.setVisibility(View.VISIBLE);
                break;
            default: mIsCommittedToInstallation = false;
                break;
        }
    }



    public void  SaveSurveyToArray(AndroidSurvey store) throws JSONException {

        try {

            String storesString = dbManager.getValue( "AndroidSurveys");

            ArrayList<AndroidSurvey> stores = JsonUtil.parseJsonArrayAndroidSurvey(storesString);
            stores.clear();
            stores.add(store);

            //Save Requests back to local
            dbManager.setValue( "AndroidSurveys", JsonUtil.convertSurveyArrayToJson(stores));


        } catch (Exception ex) {
            //
            //
        }
        //

    }


    @Override
    public void onBackPressed() {
        GoToInstallSetAppointment();

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);  dbManager = new DBManager(this);        dbManager.open();
        setContentView(R.layout.new_survey_layout);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mStoreID = getIntent().getIntExtra("StoreID",0);
        RequestID = getIntent().getIntExtra("RequestID",0);
        dbManager.setValue("RequestID", Integer.toString(RequestID));

        mURN = getIntent().getStringExtra("URN");
        addItemsOnSpinnerBrand();
        addItemsOnSpinnerYesNo();
        addItemsOnSpinnerOpeningTime();
        addItemsOnSpinnerClosingTime();
        addItemsOnSpinnerPreferredDeliveryTime();

        Spinner spinnerYesNo = (Spinner)findViewById(R.id.spinnerYesNo);

                spinnerYesNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                onYesNoSpinnerChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        try {
            LoadExistingSurvey();
        } catch (JSONException e) {
            e.printStackTrace();
        }


//        setCurrentTimeOnView();
//        addListenerOnButton();
//        setCurrentTimeOnView2();
//        addListenerOnButton2();
//        setCurrentTimeOnView3();
//        addListenerOnButton3();


    }


    protected boolean ValidateSurvey()
    {
        boolean valid = true;
        EditText TalkingToPerson = (EditText) findViewById(R.id.editTalkingToPerson);
        EditText TalkingToRole = (EditText) findViewById(R.id.editTalkingToRole);
        EditText editWhyNotCommitted = (EditText) findViewById(R.id.editWhyNotCommitted);
        EditText editComments = (EditText) findViewById(R.id.editComments);

       // Spinner spinnerBrand = (Spinner) findViewById(R.id.spinnerBrand);
       // TextView txtBrand = (TextView)findViewById(R.id.txtBrand);
        Spinner spinnerOpeningTime= (Spinner) findViewById(R.id.spinnerOpeningTime);
        Spinner spinnerClosingTime= (Spinner) findViewById(R.id.spinnerClosingTime);
       // Spinner spinnerPreferredDeliveryTime= (Spinner) findViewById(R.id.spinnerPreferredDeliveryTime);
        TextView txtOpen = (TextView)findViewById(R.id.txtOpen);
        TextView txtClose = (TextView)findViewById(R.id.txtClose);
       // TextView txtPreferred = (TextView)findViewById(R.id.txtPreferred);

        final String strTalkingToPerson=TalkingToPerson.getText().toString();
        final String strTalkingToRole=TalkingToRole.getText().toString();
        if(strTalkingToPerson.length()==0)
        {
            TalkingToPerson.requestFocus();
            TalkingToPerson.setError("Please enter name here.");
            valid = false;
        }
        if(strTalkingToRole.length()==0)
        {
            TalkingToRole.requestFocus();
            TalkingToRole.setError("Please enter role here.");
            valid = false;
        }
        Spinner spinnerYesNo = (Spinner) findViewById(R.id.spinnerYesNo);
        Integer intYesNO = spinnerYesNo .getSelectedItemPosition();

        if(intYesNO == 1) mIsCommittedToInstallation = true;
        if(intYesNO == 2) mIsCommittedToInstallation = false;

        if(intYesNO ==0)
        {
            valid = false;

        }

        if(intYesNO == 0)
        {
            //goto end
            final String streditWhyNotCommitted=editWhyNotCommitted.getText().toString();
            if(streditWhyNotCommitted.length()==0)
            {
                editWhyNotCommitted.requestFocus();
                editWhyNotCommitted.setError("Please enter reason here.");
                valid = false;
            }

        }
        if(intYesNO == 1)
        {
            //validate 3-6

            final String streditComments=editComments.getText().toString();

            String mOpen = spinnerOpeningTime.getSelectedItem().toString();
            if(mOpen.equals("00:00"))
            {
                txtOpen.setError("Please select opening time.");
                spinnerOpeningTime.requestFocus();
                valid = false;
            }
            String mClose = spinnerClosingTime.getSelectedItem().toString();
            if(mClose.equals("00:00"))
            {
                txtClose.setError("Please select closing time.");
                spinnerClosingTime.requestFocus();
                valid = false;
            }

        }

        return valid;
    }

    public AndroidSurvey findSurveyByRequestID(int requestID, ArrayList<AndroidSurvey> requests){
        for (AndroidSurvey request : requests) {
            if (request.getRequestID() == requestID) {
                return request;
            }
        }
        return null;
    }


    private void LoadExistingSurvey() throws JSONException {
        String jsonSurvey = dbManager.getValue( "AndroidSurveys");

        if(!jsonSurvey.isEmpty()) {

            ArrayList<AndroidSurvey> sss = JsonUtil.parseJsonArrayAndroidSurvey(jsonSurvey);
            AndroidSurvey ss = findSurveyByRequestID(RequestID, sss);

            if (ss != null) {
                Spinner spinnerBrand = (Spinner) findViewById(R.id.spinnerBrand);
                spinnerBrand.setSelection(ss.getBrandID());

                Spinner spinnerOpeningTime = (Spinner) findViewById(R.id.spinnerOpeningTime);
                spinnerOpeningTime.setSelection(ss.getOpeningTime());

                Spinner spinnerClosingTime = (Spinner) findViewById(R.id.spinnerClosingTime);
                spinnerClosingTime.setSelection(ss.getClosingTime());

                Spinner spinnerPreferredDeliveryTime = (Spinner) findViewById(R.id.spinnerPreferredDeliveryTime);
                spinnerPreferredDeliveryTime.setSelection(ss.getPreferredDeliveryTime());

                Intent me = getIntent();
                survey.ID = me.getIntExtra("ID", 0);
                survey.RequestID = me.getIntExtra("RequestID", 0);
                survey.SurveyorUserName = dbManager.getValue( "UserName");

                EditText TalkingToPerson = (EditText) findViewById(R.id.editTalkingToPerson);
                TalkingToPerson.setText(ss.getTalkingToPerson());

                EditText TalkingToRole = (EditText) findViewById(R.id.editTalkingToRole);
                TalkingToRole.setText(ss.getTalkingToRole());
                //radio button stuff

                boolean is = ss.getIsCommittedToInstallation();

                // small change test
                Spinner spinnerYesNo = (Spinner) findViewById(R.id.spinnerYesNo);


                if (is) {
                    spinnerYesNo.setSelection(1);
                } else {
                    spinnerYesNo.setSelection(2);
                }

                EditText Comments = (EditText) findViewById(R.id.editComments);
                Comments.setText(ss.getComments());

                if (!is) {
                    EditText editWhyNotCommitted = (EditText) findViewById(R.id.editWhyNotCommitted);
                    editWhyNotCommitted.setText(ss.getWhyNotCommitted());
                    editWhyNotCommitted.setVisibility(View.VISIBLE);

                    TextView txtWhyNotCommitted = (TextView) findViewById(R.id.txtWhyNotCommitted);
                    txtWhyNotCommitted.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void setCurrentTimeOnView2() {
        tvDisplayTime2 = (TextView) findViewById(R.id.tvTime2);
        timePicker2 = (TimePicker) findViewById(R.id.timePicker2);

        final Calendar B = Calendar.getInstance();
        hour2 = B.get(Calendar.HOUR_OF_DAY);
        minute2 = B.get(Calendar.MINUTE);

        // set current time into textview;
        tvDisplayTime2.setText(
                new StringBuilder().append(pad2(hour2))
                        .append(":").append(pad2(minute2)));

        // set current time into timepicker
        timePicker2.setCurrentHour(hour2);
        timePicker2.setCurrentMinute(minute2);


    }



    //date and time picket open time starts here
    // display current time
    public void setCurrentTimeOnView() {

        tvDisplayTime = (TextView) findViewById(R.id.tvTime);
        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);


        final Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        // set current time into textview
        tvDisplayTime.setText(
                new StringBuilder().append(pad(hour))
                        .append(":").append(pad(minute)));

        // set current time into timepicker
        timePicker1.setCurrentHour(hour);
        timePicker1.setCurrentMinute(minute);


    }

    public void addListenerOnButton2() {

        btnChangeTime2 = (Button) findViewById(R.id.btnChangeTime2);
        btnChangeTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID2);
            }
        });
    }

    public void addListenerOnButton() {

        btnChangeTime = (Button) findViewById(R.id.btnChangeTime);

        btnChangeTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(TIME_DIALOG_ID);

            }

        });

    }




    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID:
                // set time picker as current time
                return new TimePickerDialog(this,
                        timePickerListener, hour, minute, false);


            case TIME_DIALOG_ID2:
                // set time picker as current time
                return new TimePickerDialog(this, timePickerListener2, hour2, minute2, false);

            case TIME_DIALOG_ID3:
                // set time picker as current time
                return new TimePickerDialog(this, timePickerListener3, hour3, minute3, false);



        }
        return null;
    }


    private TimePickerDialog.OnTimeSetListener timePickerListener3 =
            new TimePickerDialog.OnTimeSetListener()

            {
                public void onTimeSet(TimePicker view, int selectedHour,
                                      int selectedMinute)

                {


                    hour3 = selectedHour;
                    minute3 = selectedMinute;

                    // set current time into textview
                    tvDisplayTime3.setText(new StringBuilder().append(pad3(hour3))
                            .append(":").append(pad3(minute3)));

                    // set current time into timepicker
                    timePicker3.setCurrentHour(hour3);
                    timePicker3.setCurrentMinute(minute3);


                }
            };

    private TimePickerDialog.OnTimeSetListener timePickerListener2 =
            new TimePickerDialog.OnTimeSetListener()

            {
                public void onTimeSet(TimePicker view, int selectedHour,
                                      int selectedMinute)

                {


                    hour2 = selectedHour;
                    minute2 = selectedMinute;

                    // set current time into textview
                    tvDisplayTime2.setText(new StringBuilder().append(pad2(hour2))
                            .append(":").append(pad2(minute2)));

                    // set current time into timepicker
                    timePicker2.setCurrentHour(hour2);
                    timePicker2.setCurrentMinute(minute2);


                }
            };


    private TimePickerDialog.OnTimeSetListener timePickerListener =
            new TimePickerDialog.OnTimeSetListener()

            {
                public void onTimeSet(TimePicker view, int selectedHour,
                                      int selectedMinute)

                {


                    hour = selectedHour;
                    minute = selectedMinute;

                    // set current time into textview
                    tvDisplayTime.setText(new StringBuilder().append(pad(hour))
                            .append(":").append(pad(minute)));

                    // set current time into timepicker
                    timePicker1.setCurrentHour(hour);
                    timePicker1.setCurrentMinute(minute);


                }
            };

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    private static String pad2(int B) {
        if (B >= 10)
            return String.valueOf(B);
        else
            return "0" + String.valueOf(B);
    }

    private static String pad3(int F) {
        if (F >= 10)
            return String.valueOf(F);
        else
            return "0" + String.valueOf(F);
    }

    //date and time picket open time ends here

    //date and time picket close time starts here


    // add items into spinner dynamically
    public void addItemsOnSpinnerBrand() {

        spinner2 = (Spinner) findViewById(R.id.spinnerBrand);
        List<String> list = new ArrayList<String>();
        list.add(0, "Choose Brand");
        list.add(1, "Tusker");
        list.add(2, "Guinness");
        list.add(3, "Kenya Cane");

        spinner2.setPrompt("Choose Brand");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinnerstyle, list);
        dataAdapter.setDropDownViewResource(R.layout.spinnerstyledrop);
        spinner2.setAdapter(dataAdapter);
    }

    public void addItemsOnSpinnerYesNo() {

        spinner2 = (Spinner) findViewById(R.id.spinnerYesNo);
        List<String> list = new ArrayList<String>();
        list.add(0, "Choose");
        list.add(1, "Yes");
        list.add(2, "No");


        spinner2.setPrompt("Choose");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinnerstyle, list);
        dataAdapter.setDropDownViewResource(R.layout.spinnerstyledrop);
        spinner2.setAdapter(dataAdapter);
    }


    public void addItemsOnSpinnerPreferredDeliveryTime() {

        spinner2 = (Spinner) findViewById(R.id.spinnerPreferredDeliveryTime);
        List<String> list = new ArrayList<String>();
        list.add(0, "00:00");
        list.add(1, "01:00");
        list.add(2, "02:00");
        list.add(3, "03:00");
        list.add(4, "04:00");
        list.add(5, "05:00");
        list.add(6, "06:00");
        list.add(7, "07:00");
        list.add(8, "08:00");
        list.add(9, "09:00");
        list.add(10, "10:00");
        list.add(11, "11:00");
        list.add(12, "12:00");
        list.add(13, "13:00");
        list.add(14, "14:00");
        list.add(15, "15:00");
        list.add(16, "16:00");
        list.add(17, "17:00");
        list.add(18, "18:00");
        list.add(19, "19:00");
        list.add(20, "20:00");
        list.add(21, "21:00");
        list.add(22, "22:00");
        list.add(23, "23:00");



        spinner2.setPrompt("Choose Time");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinnerstyle, list);
        dataAdapter.setDropDownViewResource(R.layout.spinnerstyledrop);
        spinner2.setAdapter(dataAdapter);
    }

    public void addItemsOnSpinnerOpeningTime() {

        spinner2 = (Spinner) findViewById(R.id.spinnerOpeningTime);
        List<String> list = new ArrayList<String>();
        list.add(0, "00:00");
        list.add(1, "01:00");
        list.add(2, "02:00");
        list.add(3, "03:00");
        list.add(4, "04:00");
        list.add(5, "05:00");
        list.add(6, "06:00");
        list.add(7, "07:00");
        list.add(8, "08:00");
        list.add(9, "09:00");
        list.add(10, "10:00");
        list.add(11, "11:00");
        list.add(12, "12:00");
        list.add(13, "13:00");
        list.add(14, "14:00");
        list.add(15, "15:00");
        list.add(16, "16:00");
        list.add(17, "17:00");
        list.add(18, "18:00");
        list.add(19, "19:00");
        list.add(20, "20:00");
        list.add(21, "21:00");
        list.add(22, "22:00");
        list.add(23, "23:00");


        spinner2.setPrompt("Choose Time");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinnerstyle, list);
        dataAdapter.setDropDownViewResource(R.layout.spinnerstyledrop);
        spinner2.setAdapter(dataAdapter);
    }

    public void addItemsOnSpinnerClosingTime() {

        spinner2 = (Spinner) findViewById(R.id.spinnerClosingTime);
        List<String> list = new ArrayList<String>();
        list.add(0, "00:00");
        list.add(1, "01:00");
        list.add(2, "02:00");
        list.add(3, "03:00");
        list.add(4, "04:00");
        list.add(5, "05:00");
        list.add(6, "06:00");
        list.add(7, "07:00");
        list.add(8, "08:00");
        list.add(9, "09:00");
        list.add(10, "10:00");
        list.add(11, "11:00");
        list.add(12, "12:00");
        list.add(13, "13:00");
        list.add(14, "14:00");
        list.add(15, "15:00");
        list.add(16, "16:00");
        list.add(17, "17:00");
        list.add(18, "18:00");
        list.add(19, "19:00");
        list.add(20, "20:00");
        list.add(21, "21:00");
        list.add(22, "22:00");
        list.add(23, "23:00");


        spinner2.setPrompt("Choose Time");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinnerstyle, list);
        dataAdapter.setDropDownViewResource(R.layout.spinnerstyledrop);
        spinner2.setAdapter(dataAdapter);
    }



    public void loadimage(View view) {
        Intent intent = new Intent(SurveyActivity.this, SurveyUploadMenu.class );

        startActivity(intent); finish();
    }

    public class CallSoapSaveSurvey extends AsyncTask<SaveSurveyParams, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(SaveSurveyParams... params) {
            return params[0].foo.SaveSurvey(params[0].jsonSurveys);
        }

        protected void onPostExecute(String result) {
            // TODO: check this.exception
            // TODO: do something with the feed
            try {

                if(result.toLowerCase().contains("exception")||result.toLowerCase().contains("error")||result.equals("0"))
                {
                    Toast.makeText(getApplicationContext(), String.format("Error occurred: %s", result), Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(getApplicationContext(), "Survey Saved", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(SurveyActivity.this, ThankYouSurvey.class);
                    intent.putExtra("Toast", "Thank the outlet owner for their time and state that one member of the Project management team or his local representative from EABL will be in contact shortly to coordinate the installation process and date.");

                    intent.putExtra("RequestID", RequestID);
                    startActivity(intent);
                    finish();
                }


            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG).show();

            }

        }



    }
    private static class SaveSurveyParams {
        MySOAPCallActivity foo;
        String jsonSurveys;



        SaveSurveyParams(MySOAPCallActivity foo, String jsonSurveys) {
            this.foo = foo;
            this.jsonSurveys = jsonSurveys;


        }
    }

}
















