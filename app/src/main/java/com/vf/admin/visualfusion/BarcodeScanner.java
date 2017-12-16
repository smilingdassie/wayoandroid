package com.vf.admin.visualfusion;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;


public class BarcodeScanner extends AppCompatActivity {


    //@Override
    // public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    //     getMenuInflater().inflate(R.menu.menu_home, menu);
    //    return true;
    //  }

    // @Override
    //  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.

    //   int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    //    if (id == R.id.action_settings) {
    //  return true;
    // }

    //   if (id == R.id.homebutton){
    //      startActivity(new Intent(this,MainActivity.class));
    //  }

    //  return super.onOptionsItemSelected(item);
    // }


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scanner);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        LinearLayout Layoutpop1 = (LinearLayout)this.findViewById(R.id.Linearlayot_for_barcodescanner);
        Animation expandIn = AnimationUtils.loadAnimation(this, R.anim.animate_expand_in);
        Layoutpop1.startAnimation(expandIn);


        TextView editTagNumber = (TextView) findViewById(R.id.editTagNumber);
        Bundle bu;
        bu = getIntent().getExtras();

        if (bu != null) {
            editTagNumber.setText(bu.getString(""));




        }


    }




    public void buttonToRescan(View view) {

        startActivity(new Intent(this,BarcodeScannerNew.class));
    }

    public void buttonToConfirm(View view) {
        startActivity(new Intent(this,AcceptProduct.class));
    }
}




