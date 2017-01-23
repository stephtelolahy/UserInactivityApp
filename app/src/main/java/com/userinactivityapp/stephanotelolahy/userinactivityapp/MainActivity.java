package com.userinactivityapp.stephanotelolahy.userinactivityapp;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final long DISCONNECT_TIMEOUT = 1 * 60 * 1000; // 1 minute in milliseconds

    private Handler disconnectHandler = new Handler(){
        public void handleMessage(Message msg) {
        }
    };

    private Runnable disconnectCallback = new Runnable() {
        @Override
        public void run() {
            // TODO: Perform any required operation on timer fired

            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Timer")
                    .setMessage("Detected inactivity")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            // restart timer
                            resetDisconnectTimer();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    };

    public void resetDisconnectTimer(){
        disconnectHandler.removeCallbacks(disconnectCallback);
        disconnectHandler.postDelayed(disconnectCallback, DISCONNECT_TIMEOUT);

        TextView textView = (TextView) this.findViewById(R.id.textView);
        textView.setText("Restarted timer at :" + DateFormat.getDateTimeInstance().format(new Date()));
    }

    public void stopDisconnectTimer(){
        disconnectHandler.removeCallbacks(disconnectCallback);
    }

    @Override
    public void onUserInteraction(){
        resetDisconnectTimer();
    }

    @Override
    public void onResume() {
        super.onResume();
        resetDisconnectTimer();
    }

    @Override
    public void onStop() {
        super.onStop();
        stopDisconnectTimer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
    Represent an action performed by user inside your app
     */
    public void buttonClicked(View sender)
    {
        Toast.makeText(MainActivity.this, "Button clicked", Toast.LENGTH_LONG).show();
    }
}
