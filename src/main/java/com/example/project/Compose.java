package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.text.Html;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Compose extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;
    private static final int REQ = 1;
    private static final int REQ2 = 2;
    private static int MIN_DISTANCE = 150;
    private float x1, x2, y1, y2;
    private GestureDetector constraintGestureDetector;

    private GestureDetector gestureDetector;
    private TextToSpeech TTS;
    private TextView sub, rec, msg;

    String sEmail, sPassword;
    static String gSubject, gRecipient, gMessage;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {

            //textView.setText(userSaid);

            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        //Initialize gestureDetector
        this.gestureDetector = new GestureDetector(this, this);

        sub = (TextView) findViewById(R.id.etSubject);
        rec = (TextView) findViewById(R.id.etRecipient);
        msg = (TextView) findViewById(R.id.etMessage);

        //Sender Email Credentials
        sEmail = "benjaminambrose99@gmail.com";
        sPassword ="ieoiwkepwnspmcqg";

        getSupportActionBar().hide(); // To hide the action bar

        Toast.makeText(this, "You are in Compose Message", Toast.LENGTH_SHORT).show();
        tts("You are in Compose Message.");
        tts("After recording your msg, swipe up to send.");

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(new Run1(), 3 * 1000);
                handler.postDelayed(new Run2(), 8 * 1000);
                handler.postDelayed(new Run3(), 12 * 1000);
                handler.postDelayed(new Run4(), 16 * 1000);
                handler.postDelayed(new Run5(), 21 * 1000);
                handler.postDelayed(new Run6(), 25 * 1000);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

    }

    // Method for text to speech
    public void tts(String word) {

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

    /**
     * Method for STT
     **/
    private void stt() {
        //Intent to show speech to text
        Intent int2 = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        int2.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        int2.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        int2.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi, say something");

        //Start Intent
        try {
            //If there was no error
            //Show dialog
            startActivityForResult(int2, REQUEST_CODE_SPEECH_INPUT);
        } catch (Exception e) {
            //If there was some error
            //Get msg of error and show
            Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void stt2() {
        //Intent to show speech to text
        Intent int2 = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        int2.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        int2.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        int2.putExtra(RecognizerIntent.EXTRA_PROMPT, "LMAO");

        //Start Intent
        try {
            //If there was no error
            //Show dialog
            startActivityForResult(int2, REQ);
        } catch (Exception e) {
            //If there was some error
            //Get msg of error and show
            Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void stt3() {
        //Intent to show speech to text
        Intent int2 = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        int2.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        int2.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        int2.putExtra(RecognizerIntent.EXTRA_PROMPT, "LMAO2");

        //Start Intent
        try {
            //If there was no error
            //Show dialog
            startActivityForResult(int2, REQ2);
        } catch (Exception e) {
            //If there was some error
            //Get msg of error and show
            Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receive voice input and handle it
     **/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    //Get text array from voice intent
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    // Set to textView
                    rec.setText(result.get(0));

                    //gRecipient = result.get(0);
                }
                break;
            }
        }

        switch (requestCode) {
            case REQ: {
                if (resultCode == RESULT_OK && null != data) {
                    // Get text array from voice intent
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    // Set text to text view
                    sub.setText(result.get(0));

                    //gSubject = result.get(0);
                }
                break;
            }
        }

        switch (requestCode) {
            case REQ2: {
                if (resultCode == RESULT_OK && null != data) {
                    // Get text array fdrom voice intent
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    // Set text to text view
                    msg.setText(result.get(0));

                    //gMessage = result.get(0);
                }
                break;
            }
        }
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
                        Toast.makeText(this, "Right Swipe", Toast.LENGTH_SHORT).show();
                    } else {
                        /** Detect right to left swipe **/
                        Toast.makeText(getApplicationContext(), "Left Swipe", Toast.LENGTH_SHORT).show();
                    }
                } else if (Math.abs(valueY) > MIN_DISTANCE) {
                    /** Detect Top to Bottom Swipe **/
                    if (y2 > y1) {
                        Toast.makeText(this, "Bottom Swipe", Toast.LENGTH_SHORT).show();

                    } else {
                        /** Detect Bottom to Bottom swipe **/
                        Toast.makeText(getApplicationContext(), "Top Swipe", Toast.LENGTH_SHORT).show();
                        //Initialize Properties
                        Properties properties = new Properties();
                        properties.put("mail.smtp.auth", "true");
                        properties.put("mail.smtp.starttls.enable", "true");
                        properties.put("mail.smtp.host", "smtp.gmail.com");
                        properties.put("mail.smtp.port", "587");

                        //Initialize Session
                        Session session = Session.getInstance(properties, new Authenticator() {
                            @Override
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(sEmail, sPassword);
                            }
                        });

                        try {
                            //Initialize email content
                            Message message = new MimeMessage(session);
                            //Sender's email
                            message.setFrom(new InternetAddress(sEmail));
                            //Recipient's email
                            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(rec.getText().toString().trim()));
                            //Email sub
                            message.setSubject(sub.getText().toString().trim());
                            //Email msg
                            message.setText(msg.getText().toString().trim());

                            //Send Mail
                            new Compose.SendMail().execute(message);

                        } catch (MessagingException exception) {
                            exception.printStackTrace();
                        }
                    }
                }
        }
        return super.onTouchEvent(event);
    }

    private class SendMail extends AsyncTask<Message, String, String> {
        //Initialize Progress Dialog
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Create and Show progress Dialog
            progressDialog = ProgressDialog.show(Compose.this, "Please Wait", "Sending Mail...", true, false);
        }

        @Override
        protected String doInBackground(Message... messages) {
            try {
                //When Success
                Transport.send(messages[0]);
                return "Success";
            } catch (MessagingException exception) {
                //When Error
                exception.printStackTrace();
                return "Error";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Dismiss ProgressDialog
            progressDialog.dismiss();
            if (s.equals("Success")) {
                //When Success

                //Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(Compose.this);
                builder.setCancelable(false);
                builder.setTitle(Html.fromHtml("<font color = '#509324'>Success</font>"));
                builder.setMessage("Mail Send Successfully.");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //Clear all edit text fields
                        rec.setText("");
                        sub.setText("");
                        msg.setText("");
                    }
                });
                //Show alert dialog
                builder.show();
            } else {
                //When error
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
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

    class Run1 implements Runnable {
        @Override
        public void run() {
            tts("To whom is the msg addressed ?");
            SystemClock.sleep(1000);
        }
    }

    class Run2 implements Runnable {
        @Override
        public void run() {

            stt();
            SystemClock.sleep(1000);
        }
    }

    class Run3 implements Runnable {
        @Override
        public void run() {

            tts("What is the sub ?");
            SystemClock.sleep(1000);
        }
    }

    class Run4 implements Runnable {

        @Override
        public void run() {

            stt2();
            SystemClock.sleep(1000);
        }
    }

    class Run5 implements Runnable {
        @Override
        public void run() {
            tts("Please speak your msg...");
            SystemClock.sleep(1000);
        }
    }

    class Run6 implements Runnable {
        @Override
        public void run() {
            stt3();
            SystemClock.sleep(1000);
        }
    }

    //sending email
    /**private boolean sendEmail(String mailAddress, String sub, String Message) {
        String mEmail = mailAddress;
        String mSubject = sub;
        String mMessage = Message;


        javaMailAPI javaailAPI = new javaMailAPI(this, mEmail, mSubject, mMessage);

        //javaailAPI.execute();
        Toast.makeText(getApplicationContext(), "Message not sent", Toast.LENGTH_SHORT).show();
        if(javaailAPI.execute().getStatus()){
            return true;
        }else{
            return false;
        }
    }**/

    /**private static void send() {
        String from = "benjaminambrose99@gmail.com";
        String password = "ieoiwkepwnspmcqg";
        String to = "davidfestus22@gmail.com";
        String sub = gSubject;
        String msg = gMessage;

        Mailer mail = new Mailer();

        mail.send(from, password, to, sub, msg);

    }**/

    /**private void sendEmail() {
        String email = editTextEmail.getText().toString().trim();
        String sub = editTextSubject.getText().toString().trim();
        String msg = editTextMessage.getText().toString().trim();
        SendMail sm = new SendMail(this, email, sub, msg);
        sm.execute();

    }**/
}

