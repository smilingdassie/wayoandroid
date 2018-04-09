package com.example.donavan.visaulfusion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NewFavorites2 extends AppCompatActivity {

    Button Fav1btn;
    Button Fav2btn;
    Button Fav3btn;
    Button Fav4btn;
    Button Fav5btn;
    Button Continue;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_favorites2);




    }

    public void Favorites1(View view) {
        Fav1btn = (Button) findViewById(R.id.fav1id);
        Fav2btn = (Button) findViewById(R.id.fav2id);
        Fav3btn = (Button) findViewById(R.id.fav3id);
        Fav4btn = (Button) findViewById(R.id.fav4id);
        Fav5btn = (Button) findViewById(R.id.fav5id);
        Continue = (Button) findViewById(R.id.continue_btn);

        Fav1btn.setBackgroundResource(R.drawable.button_active_heart);
        Fav2btn.setBackgroundResource(R.drawable.button_notactive_heart);
        Fav3btn.setBackgroundResource(R.drawable.button_notactive_heart);
        Fav4btn.setBackgroundResource(R.drawable.button_notactive_heart);
        Fav5btn.setBackgroundResource(R.drawable.button_notactive_heart);
        Continue.setEnabled(true);



        TextView tv = (TextView)findViewById(R.id.answer);
        tv.setText("Very Poor");


    }

    public void Favorites2(View view) {
        Fav1btn = (Button) findViewById(R.id.fav1id);
        Fav2btn = (Button) findViewById(R.id.fav2id);
        Fav3btn = (Button) findViewById(R.id.fav3id);
        Fav4btn = (Button) findViewById(R.id.fav4id);
        Fav5btn = (Button) findViewById(R.id.fav5id);
        Continue = (Button) findViewById(R.id.continue_btn);

        Fav1btn.setBackgroundResource(R.drawable.button_active_heart);
        Fav2btn.setBackgroundResource(R.drawable.button_active_heart);
        Fav3btn.setBackgroundResource(R.drawable.button_notactive_heart);
        Fav4btn.setBackgroundResource(R.drawable.button_notactive_heart);
        Fav5btn.setBackgroundResource(R.drawable.button_notactive_heart);
        Continue.setEnabled(true);

        TextView tv = (TextView)findViewById(R.id.answer);
        tv.setText("Poor");


    }

    public void Favorites3(View view) {
        Fav1btn = (Button) findViewById(R.id.fav1id);
        Fav2btn = (Button) findViewById(R.id.fav2id);
        Fav3btn = (Button) findViewById(R.id.fav3id);
        Fav4btn = (Button) findViewById(R.id.fav4id);
        Fav5btn = (Button) findViewById(R.id.fav5id);
        Continue = (Button) findViewById(R.id.continue_btn);

        Fav1btn.setBackgroundResource(R.drawable.button_active_heart);
        Fav2btn.setBackgroundResource(R.drawable.button_active_heart);
        Fav3btn.setBackgroundResource(R.drawable.button_active_heart);
        Fav4btn.setBackgroundResource(R.drawable.button_notactive_heart);
        Fav5btn.setBackgroundResource(R.drawable.button_notactive_heart);
        Continue.setEnabled(true);

        TextView tv = (TextView)findViewById(R.id.answer);
        tv.setText("Average");

    }

    public void Favorites4(View view) {
        Fav1btn = (Button) findViewById(R.id.fav1id);
        Fav2btn = (Button) findViewById(R.id.fav2id);
        Fav3btn = (Button) findViewById(R.id.fav3id);
        Fav4btn = (Button) findViewById(R.id.fav4id);
        Fav5btn = (Button) findViewById(R.id.fav5id);
        Continue = (Button) findViewById(R.id.continue_btn);

        Fav1btn.setBackgroundResource(R.drawable.button_active_heart);
        Fav2btn.setBackgroundResource(R.drawable.button_active_heart);
        Fav3btn.setBackgroundResource(R.drawable.button_active_heart);
        Fav4btn.setBackgroundResource(R.drawable.button_active_heart);
        Fav5btn.setBackgroundResource(R.drawable.button_notactive_heart);
        Continue.setEnabled(true);

        TextView tv = (TextView)findViewById(R.id.answer);
        tv.setText("Good");



    }

    public void Favorites5(View view) {
        Fav1btn = (Button) findViewById(R.id.fav1id);
        Fav2btn = (Button) findViewById(R.id.fav2id);
        Fav3btn = (Button) findViewById(R.id.fav3id);
        Fav4btn = (Button) findViewById(R.id.fav4id);
        Fav5btn = (Button) findViewById(R.id.fav5id);
        Continue = (Button) findViewById(R.id.continue_btn);

        Fav1btn.setBackgroundResource(R.drawable.button_active_heart);
        Fav2btn.setBackgroundResource(R.drawable.button_active_heart);
        Fav3btn.setBackgroundResource(R.drawable.button_active_heart);
        Fav4btn.setBackgroundResource(R.drawable.button_active_heart);
        Fav5btn.setBackgroundResource(R.drawable.button_active_heart);
        Continue.setEnabled(true);


        TextView tv = (TextView)findViewById(R.id.answer);
        tv.setText("Expetional");




    }


    public void Continue(View view) {

        Intent intent = new Intent(NewFavorites2.this, NewFavorites3.class );

        startActivity(intent);
    }
}
