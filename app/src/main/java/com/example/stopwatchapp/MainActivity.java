package com.example.stopwatchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }

//    public void onclickstart(View view){}
//    public void onclickstop(View view){}
//    public void onclickreset(View view){}

    String record="";

    private int seconds=0;
    private boolean running=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState!=null)
        {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }
        runTime();
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
    }

    public void onclickstart(View view)
    {
//        Button bttn1 = (Button) findViewById(R.id.bttn1);
        Button bttn2 = (Button) findViewById(R.id.bttn2);
        Button bttn3 = (Button) findViewById(R.id.bttn3);
//        bttn1.setVisibility(View.INVISIBLE);
        bttn2.setVisibility(View.VISIBLE);
        bttn3.setVisibility(View.VISIBLE);
        running=true;
    }

    public void onclickstop(View view)
    {
//        Button bttn1 = (Button) findViewById(R.id.bttn1);
//        Button bttn2 = (Button) findViewById(R.id.bttn2);
//        bttn2.setVisibility(View.GONE);
//        bttn1.setVisibility(View.VISIBLE);
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
    }

    public void saveData(String time)
    {
//        SharedPreferences sharedPref = getSharedPreferences("records", Context.MODE_PRIVATE);
//
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putString("username",time);
//        editor.apply();

        TextView textView1 = (TextView) findViewById(R.id.tvONE);
        String data = textView1.getText().toString();
        data += time;
        textView1.setText(data);
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