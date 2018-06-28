package com.dsouchon.wayo.visualfusion;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.gcacace.signaturepad.views.SignaturePad;
import com.kosalgeek.android.photoutil.ImageBase64;
import com.kosalgeek.android.photoutil.ImageLoader;

import org.json.JSONException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class SignatureActivity extends AppCompatActivity {
    private DBManager dbManager;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private SignaturePad mSignaturePad;
    private Button mClearButton;
    private Button mSaveButton;
    private Integer mGlobalStoreID;
    private String mGlobalSignatureType;
    private String globalStoreNameURN;
    private String yourname="";
    private String designation="";
    private Integer RequestID;


    @Override
    public void onBackPressed() {
        //DO NOTHING

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
        verifyStoragePermissions(this);
        setContentView(R.layout.signature_pad);

        Intent me = getIntent();
        mGlobalSignatureType = me.getStringExtra("SignatureType");

        mGlobalStoreID = me.getIntExtra("StoreID", 0);

        if(mGlobalSignatureType.equals("SurveySignature")) {

            globalStoreNameURN = me.getStringExtra("StoreNameURN");
        }

        RequestID  = me.getIntExtra("RequestID",0);
        yourname = me.getStringExtra("YourName");
        designation = me.getStringExtra("Designation");


        mSignaturePad = (SignaturePad) findViewById(R.id.signature_pad);
        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
                //Toast.makeText(SignatureActivity.this, "OnStartSigning", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSigned() {
                mSaveButton.setEnabled(true);
                mClearButton.setEnabled(true);
            }

            @Override
            public void onClear() {
                mSaveButton.setEnabled(false);
                mClearButton.setEnabled(false);
            }
        });

        mClearButton = (Button) findViewById(R.id.clear_button);
        mSaveButton = (Button) findViewById(R.id.save_button);

        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSignaturePad.clear();
            }
        });

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap signatureBitmap = mSignaturePad.getSignatureBitmap();
                String fileName = String.format("Signature_%s_%d.jpg", mGlobalSignatureType, mGlobalStoreID );//System.currentTimeMillis());

                String filePath = addJpgSignatureToGallery(signatureBitmap, fileName);

                if (filePath !="False") {
                    //Toast.makeText(SignatureActivity.this, "JPG Signature saved into the Gallery", Toast.LENGTH_SHORT).show();
                    String strUserName = dbManager.getValue("UserName");

                    Bitmap bitmap = null;//1024,1024).getBitmap();
                    try {
                        bitmap = ImageLoader.init().from(filePath).requestSize(300, 300).getBitmap();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    String encodedImage = ImageBase64.encode(bitmap);

                    //Please wait
                    yourname = dbManager.getValue( "YourName");
                    designation = dbManager.getValue( "Designation");

                    String complaint = String.format("YourName:%s;Designation:%s", yourname, designation);

                    UploadStoreItemImage( RequestID, mGlobalStoreID, encodedImage , strUserName ,mGlobalSignatureType,complaint,"");


                }
                else {
                    Toast.makeText(SignatureActivity.this, "Unable to store the signature", Toast.LENGTH_SHORT).show();
                    Intent i = new  Intent(getApplicationContext(),InstallSetAppointment.class);
                    startActivity(i);
                }
              /*  if (addSvgSignatureToGallery(mSignaturePad.getSignatureSvg())) {
                    Toast.makeText(SignatureActivity.this, "SVG Signature saved into the Gallery", Toast.LENGTH_SHORT).show();
                    Intent i = new  Intent(getApplicationContext(),SuccessActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(SignatureActivity.this, "Unable to store the SVG signature", Toast.LENGTH_SHORT).show();
                    Intent i = new  Intent(getApplicationContext(),SuccessActivity.class);
                    startActivity(i);
                }*/
            }
        });
    }

    public void UploadSignature()
    {

        //on ws if barcode is blank and its a store item image then its not a barcode image
        //UploadStoreItemImage(StoreItemID, StoreID, encodedImage, UserName, ImageType, ProductComplaint, Barcode);
        return;

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

            Intent intent = new Intent(SignatureActivity.this, LoginActivity.class );

            startActivity(intent); finish();

            // return true;
        }



        if (id == R.id.logoutbutton){
            startActivity(new Intent(this,LoginActivity.class));
        }

        if (id == R.id.backbutton){
            startActivity(new Intent(this,LoginActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length <= 0
                        || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(SignatureActivity.this, "Cannot write images to external storage", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e(albumName, "Directory not created");
        }
        return file;
    }

    public void saveBitmapToJPG(Bitmap bitmap, File photo) throws IOException {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        OutputStream stream = new FileOutputStream(photo);
        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        stream.close();
    }

    public String addJpgSignatureToGallery(Bitmap signature, String fileName) {
        String result = "False";
        try {
            File photo = new File(getAlbumStorageDir("SignaturePad"), fileName);
            saveBitmapToJPG(signature, photo);
            scanMediaFile(photo);
            result = photo.getPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void scanMediaFile(File photo) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(photo);
        mediaScanIntent.setData(contentUri);
        SignatureActivity.this.sendBroadcast(mediaScanIntent);
    }

    public boolean addSvgSignatureToGallery(String signatureSvg) {
        boolean result = false;
        try {
            File svgFile = new File(getAlbumStorageDir("SignaturePadSVG"), String.format("Signature_%d.svg", System.currentTimeMillis()));
            OutputStream stream = new FileOutputStream(svgFile);
            OutputStreamWriter writer = new OutputStreamWriter(stream);
            writer.write(signatureSvg);
            writer.close();
            stream.flush();
            stream.close();
            scanMediaFile(svgFile);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Checks if the app has permission to write to device storage
     * <p/>
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity the activity from which permissions are checked
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    public void ConfirmSignature(View view) {

        Intent i = new  Intent(getApplicationContext(),InstallSetAppointment.class);
        startActivity(i);
        finish();


    }
    public void UploadStoreItemImage(Integer StoreItemID,
                                     Integer StoreID,
                                     String Base64Image,
                                     String UserName,
                                     String Type,
                                     String ProductComplaint,
                                     String Barcode
    ) {

        final AlertDialog ad=new AlertDialog.Builder(this).create();
        MySOAPCallActivity cs = new MySOAPCallActivity();
        try {

            UploadStoreItemImageParams params = new UploadStoreItemImageParams(cs, StoreItemID, StoreID,Base64Image,UserName,Type, ProductComplaint, Barcode);

            new CallSoapUploadStoreItemImage().execute(params);


        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG).show();
        }
        ad.show();


    }

    public class CallSoapUploadStoreItemImage extends AsyncTask<UploadStoreItemImageParams, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(UploadStoreItemImageParams... params) {
            return params[0].foo.UploadStoreItemImage(params[0].StoreItemID, params[0].StoreID,params[0].Base64Image,params[0].UserName,params[0].Type, params[0].ProductComplaint, params[0].Barcode );
        }

        protected void onPostExecute(String result) {
            // TODO: check this.exception
            // TODO: do something with the feed
            try {
                //process Json Result
                //Save results in local storage
                if(result.toLowerCase().contains("error") || result.toLowerCase().contains("fault")){
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

                }
                else {
                    result = result.replace("\n", "").replace("\r", "");
                    WhenUploadIsComplete();
                }

            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG).show();
            }

        }



    }
    private static class UploadStoreItemImageParams {
        MySOAPCallActivity foo;
        Integer StoreItemID;
        Integer StoreID;
        String Base64Image;
        String UserName;
        String Type;
        String ProductComplaint;
        String Barcode;

        UploadStoreItemImageParams(MySOAPCallActivity foo, Integer StoreItemID,
                                   Integer StoreID,
                                   String Base64Image,
                                   String UserName,
                                   String Type,
                                   String ProductComplaint,
                                   String Barcode
        ) {
            this.foo = foo;
            this.StoreItemID = StoreItemID;
            this.StoreID = StoreID;
            this.Base64Image = Base64Image;
            this.UserName = UserName;
            this.Type = Type;
            this.ProductComplaint = ProductComplaint;
            this.Barcode = Barcode;

        }
    }


    public void WhenUploadIsComplete ()throws JSONException {
        Intent me = getIntent();

        mGlobalSignatureType = me.getStringExtra("SignatureType");
        switch(mGlobalSignatureType)
        {
            case "PerformanceScoreCardSignature":
                Toast.makeText(getApplicationContext(),"Performace Review Saved.", Toast.LENGTH_LONG).show();
                ArrayList<AndroidAppointment> myappointments;
                Intent intent = new Intent(getApplicationContext(), InstallSetAppointment.class);

                myappointments = getAppointmentsData();
                RequestID = Integer.parseInt(dbManager.getValue( "RequestID"));

                //Sending data to another Activity
                AndroidAppointment appointment = findAppointmentByID(RequestID, myappointments);

                intent.putExtra("ID", appointment.getID());
                intent.putExtra("Mileage", appointment.getMileage());
                intent.putExtra("StoreID", appointment.getStoreID());


                intent.putExtra("RequestTypeName", appointment.getRequestTypeName());
                intent.putExtra("BrandName", appointment.getBrandName());
                intent.putExtra("OutletTypeName", appointment.getOutletTypeName());
                intent.putExtra("TierTypeName", appointment.getTierTypeName());

                String StoreName = appointment.getStoreName();
                String StoreNameURN = appointment.getStoreNameURN();

                intent.putExtra("StoreName", StoreName);
                intent.putExtra("StoreNameURN", StoreNameURN);
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
                intent.putExtra("BrandName", appointment.getBrandName());
                intent.putExtra("OutletTypeName", appointment.getOutletTypeName());
                intent.putExtra("TierTypeName", appointment.getTierTypeName());

                startActivity(intent);
                finish();
                break;


            case "SurveySignature":
                Toast.makeText(getApplicationContext(),"Survey Sign Off Successful", Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(getApplicationContext(), InstallHome.class);
                startActivity(intent2);
                finish();
                break;

            case "InstallationSignature":
                Toast.makeText(getApplicationContext(),"Installation Sign Off Successful", Toast.LENGTH_LONG).show();
                Intent intent3 = new Intent(getApplicationContext(), InstallHome.class);
                startActivity(intent3);
                finish();
                break;

            default: break;
        }









            /*


      */


    }


    private ArrayList<AndroidAppointment> getAppointmentsData() {
        ArrayList<AndroidAppointment> appointments = new ArrayList<>();
        String json = "";
        json = dbManager.getValue( "AndroidInsAppointments");
        try {
            appointments = JsonUtil.parseJsonArrayAndroidAppointment(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return appointments;
    }

    public AndroidAppointment findAppointmentByID(int id, ArrayList<AndroidAppointment> requests){
        for (AndroidAppointment request : requests) {
            if (request.getID() == id) {
                return request;
            }
        }
        return null;
    }

}
