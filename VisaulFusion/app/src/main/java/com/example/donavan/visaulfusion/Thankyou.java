package com.example.donavan.visaulfusion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Thankyou extends AppCompatActivity {

    Button Fav1btn;
    Button Fav2btn;
    Button Fav3btn;
    Button Fav4btn;
    Button Fav5btn;
    Button Continue;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thankyou);




    }



    public void Continue(View view) {

      //  Intent intent = new Intent(NewFavorites7.this, Thankyou.class );

       // startActivity(intent);
    }
}
