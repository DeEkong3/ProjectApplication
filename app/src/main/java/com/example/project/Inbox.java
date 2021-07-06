package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.GestureDetector;
import android.view.View;

public class Inbox extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private TextToSpeech TTS;
    private static int MIN_DISTANCE = 150;
    private GestureDetector gestureDetector;
    private float x1, x2, y1, y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);

        switch (event.getAction()) {
            /** Starting to swipe time gesture **/
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;

            /** Ending swipe gesture **/
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();

                /** Getting value for Horizontal swipe **/
                float valueX = x2 - x1;
                /** Getting value for Vertical swipe **/
                float valueY = y2 - y1;

                if (Math.abs(valueX) > MIN_DISTANCE) {
                    /** Detect left to right swipe **/
                    if (x2 > x1) {
                        Toast.makeText(this, "" + "Left Swipe", Toast.LENGTH_SHORT).show();
                    } else {
                        /** Detect right to left swipe **/
                        Toast.makeText(getApplicationContext(), "Right Swipe", Toast.LENGTH_SHORT).show();
                        // Open MainActivity
                        Intent intent = new Intent(Inbox.this, MainActivity.class);
                        startActivity(intent);
                    }}
                else
                    if (Math.abs(valueY) > MIN_DISTANCE) {
                    /** Detect Top to Bottom Swipe **/
                    if (y2 > y1) {
                        Toast.makeText(this, "Bottom Swipe", Toast.LENGTH_SHORT).show();

                    } else {
                        /** Detect Bottom to Bottom swipe **/
                        Toast.makeText(getApplicationContext(), "Top Swipe", Toast.LENGTH_SHORT).show();
                    }
                }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return false;
    }

    public void TTS(String word) {
        TTS = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    TTS.setLanguage(Locale.ENGLISH);
                    String one = word;

                    TTS.speak(one, TextToSpeech.QUEUE_ADD, null);
                }
            }
        });

    }


    @Override
    protected void onPause() {
        TTS.stop();
        super.onPause();
    }

    @Override
    protected void onStop() {
        TTS.stop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        TTS.stop();
        super.onDestroy();
    }
}