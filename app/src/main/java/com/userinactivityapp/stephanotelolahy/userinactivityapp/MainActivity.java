package com.userinactivityapp.stephanotelolahy.userinactivityapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonClicked(View sender)
    {
        Toast.makeText(this, "Clicked", Toast.LENGTH_LONG).show();

        TextView textView = (TextView)this.findViewById(R.id.textView);
        textView.setText("Clicked");
    }
}
