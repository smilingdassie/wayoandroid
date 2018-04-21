package com.example.donavan.visaulfusion;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class PreSignPerformanceReview extends AppCompatActivity {

    Button Fav1btn;
    Button Fav2btn;
    Button Fav3btn;
    Button Fav4btn;
    Button Fav5btn;
    Button Continue;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pressignperformancereview);




    }



    public void Continue(View view) throws JSONException {



        Local.Set(getApplicationContext(), "SignaturePurpose", "PerformanceReview");

        AndroidPerformanceReview review = new AndroidPerformanceReview();
        review.setScore1(Integer.parseInt(Local.Get(getApplicationContext(), "PRS1")));
        review.setScore2(Integer.parseInt(Local.Get(getApplicationContext(), "PRS2")));
        review.setScore3(Integer.parseInt(Local.Get(getApplicationContext(), "PRS3")));
        review.setScore4(Integer.parseInt(Local.Get(getApplicationContext(), "PRS4")));
        review.setScore5(Integer.parseInt(Local.Get(getApplicationContext(), "PRS5")));
        review.setScore6(Integer.parseInt(Local.Get(getApplicationContext(), "PRS6")));
        review.setScore7(Integer.parseInt(Local.Get(getApplicationContext(), "PRS7")));

        EditText editPersonName = (EditText)  findViewById(R.id.editPersonName);
        EditText editPersonDesignation = (EditText)  findViewById(R.id.editPersonDesignation);

        review.setSignaturePersonName( editPersonName.getText().toString() );
        review.setSignatureDesignation( editPersonDesignation.getText().toString() );
        review.setInstallationUserName( Local.Get(getApplicationContext(), "UserName"));
        review.setRequestID(Integer.parseInt(Local.Get(getApplicationContext(), "RequestID")));

        //save review locally

        SavePerformanceReviewToArray(review);

        final AlertDialog ad=new AlertDialog.Builder(this).create();
        String isWebsiteAvailable = Local.Get(getApplicationContext(), "AmIOnline");

        if(isWebsiteAvailable.equals("True")) {
            //Save My Appointments - this will also 'delete' the request online
            MySOAPCallActivity cs1 = new MySOAPCallActivity();
            try {


                String reviews = Local.Get(getApplicationContext(), "AndroidPerformanceReviews");
                SavePerformanceReviewParams params = new SavePerformanceReviewParams(cs1, reviews);

                new CallSoapSavePerformanceReview().execute(params);
            } catch (Exception ex) {
                ad.setTitle("Error!");
                ad.setMessage(ex.toString());
            }
            ad.show();
        }
        
        
        
        
        
        

        
        
        

    }


    public void  SavePerformanceReviewToArray(AndroidPerformanceReview review) throws JSONException {

        try {

            String reviewsString = Local.Get(getApplicationContext(), "AndroidPerformanceReviews");

            ArrayList<AndroidPerformanceReview> reviews = JsonUtil.parseJsonArrayAndroidPerformanceReview(reviewsString);
            reviews.clear();
            reviews.add(review);

            //Save Requests back to local
            Local.Set(getApplicationContext(), "AndroidPerformanceReviews", JsonUtil.convertPerformanceReviewArrayToJson(reviews));


        } catch (Exception ex) {
            //ad.setTitle("Error!");
            //ad.setMessage(ex.toString());
        }
        //ad.show();

    }



    public class CallSoapSavePerformanceReview extends AsyncTask<SavePerformanceReviewParams, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(SavePerformanceReviewParams... params) {
            return params[0].foo.SavePerformanceReview(params[0].jsonPerformanceReviews);
        }

        protected void onPostExecute(String result) {

            try {


                Intent intent = new Intent(PreSignPerformanceReview.this, Signature.class );

                startActivity(intent); finish();


            } catch (Exception ex) {
                String e3 = ex.toString();
            }

        }



    }
    private static class SavePerformanceReviewParams {
        MySOAPCallActivity foo;
        String jsonPerformanceReviews;



        SavePerformanceReviewParams(MySOAPCallActivity foo, String jsonPerformanceReviews) {
            this.foo = foo;
            this.jsonPerformanceReviews = jsonPerformanceReviews;


        }
    }



}
