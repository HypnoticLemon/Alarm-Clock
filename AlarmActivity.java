package com.example.abcd.alarmclock;

import android.app.AlarmManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Calendar;
public class AlarmActivity extends AppCompatActivity {
    AlarmManager alarmManager;
    //private PendingIntent pendingIntent;
    private static AlarmActivity inst;
    private TextView alarmTextView;
    Calendar calendar;
    Button buttonTime;


    public static AlarmActivity instance() {
        return inst;
    }

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }

    public void next(View v){
        Intent i = new Intent(AlarmActivity.this,WifiActivity.class);
        startActivity(i);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        alarmTextView = (TextView) findViewById(R.id.alarmText);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        buttonTime = (Button)findViewById(R.id.btnTime);
        calendar = Calendar.getInstance();
    }
    public void setAlarmText(String alarmText) {
        alarmTextView.setText(alarmText);
    }

    public void doSomething(View view){
        if(view.getId() == R.id.btnTime){
            Intent i = new Intent(this,TimePickerActivity.class);
            startActivity(i);
        }
    }
}