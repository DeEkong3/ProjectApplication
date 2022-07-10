package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Inbox extends AppCompatActivity implements View.OnClickListener {

    //Creating 4 different objects for 4 different cardviews
    public CardView card1, card2, card3, card4, card5, card6, card7, card8, card9; // Can be public or private

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        card1 = (CardView) findViewById(R.id.c1);
        card2 = (CardView) findViewById(R.id.c2);
        card3 = (CardView) findViewById(R.id.c3);
        card4 = (CardView) findViewById(R.id.c4);
        card5 = (CardView) findViewById(R.id.c5);
        card6 = (CardView) findViewById(R.id.c6);
        card7 = (CardView) findViewById(R.id.c7);
        card8 = (CardView) findViewById(R.id.c8);
        card9 = (CardView) findViewById(R.id.c9);


        //Assigning OnClick listener to the four objects/cards
        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
        card5.setOnClickListener(this);
        card6.setOnClickListener(this);
        card7.setOnClickListener(this);
        card8.setOnClickListener(this);
        card9.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            //Whenever you click on a particular view, it will get the id of the view ie if you click on card1, you'd get c1... That is what getId() is for
            case R.id.c1 : // Get the Id of the card c1
                i = new Intent(this, One.class);
                startActivity(i);
                break;
            case R.id.c2 :
                i = new Intent(this, Two.class);
                startActivity(i);
                break;
            case R.id.c3 :
                i = new Intent(this, Three.class);
                startActivity(i);
                break;
            case R.id.c4 :
                i = new Intent(this, Four.class);
                startActivity(i);
                break;
            case R.id.c5 :
                i = new Intent(this, Five.class);
                startActivity(i);
                break;
            case R.id.c6 :
                i = new Intent(this, Six.class);
                startActivity(i);
                break;
            case R.id.c7 :
                i = new Intent(this, Seven.class);
                startActivity(i);
                break;
            case R.id.c8 :
                i = new Intent(this, Eight.class);
                startActivity(i);
                break;
            case R.id.c9 :
                i = new Intent(this, Nine.class);
                startActivity(i);
                break;
        }
    }
}