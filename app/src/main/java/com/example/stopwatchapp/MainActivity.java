package com.example.stopwatchapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String sharedPrefName = "com.example.stopwatchapp" ;
    SharedPrefDataHelper sharedPrefDataHelper = new SharedPrefDataHelper();
    String record="";
    private int seconds=0;
    private boolean running=false;
    TextView myData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myData = (TextView) findViewById(R.id.tvONE);

        if (savedInstanceState!=null)
        {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }
        runTime();
        showData();
    }

    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
    }

    public void onclickstart(View view)
    {
        Button bttn2 = (Button) findViewById(R.id.bttn2);
        Button bttn3 = (Button) findViewById(R.id.bttn3);
        bttn2.setVisibility(View.VISIBLE);
        bttn3.setVisibility(View.VISIBLE);
        running=true;
    }

    public void onclickstop(View view)
    {
        running=false;
    }

    public void onclickreset(View view)
    {

        Button bttn1 = (Button) findViewById(R.id.bttn1);
        Button bttn2 = (Button) findViewById(R.id.bttn2);
        Button bttn3 = (Button) findViewById(R.id.bttn3);
        bttn2.setVisibility(View.INVISIBLE);
        bttn3.setVisibility(View.INVISIBLE);
        bttn1.setVisibility(View.VISIBLE);
        running=false;
        seconds=0;
        saveData(record);
        showData();
    }

    public void saveData(String record){

        SharedPreferences sharedPref = getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);
        sharedPrefDataHelper.putSharedPref(sharedPref, record);
    }

    public void showData(){
        SharedPreferences sharedPref = getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);
        List<String> timeList = sharedPrefDataHelper.getSharedPref(sharedPref);
        StringBuilder showDataText = new StringBuilder();
        for (String time: timeList) {
            showDataText.append(time);
        }
        myData.setText(showDataText.toString());
    }

    private void runTime()
    {
        TextView textView = (TextView) findViewById(R.id.tv);
        Handler handler = new Handler();

        handler.post(new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;

                @SuppressLint("DefaultLocale")
                String time = String.format("%02d:%02d:%02d",hours,minutes,secs);

                textView.setText(time);
                record= "\n"+time;

                if (running)
                {
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }
}