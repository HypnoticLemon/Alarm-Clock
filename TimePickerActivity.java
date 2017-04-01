package com.example.abcd.alarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;

public class TimePickerActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private DatePicker datePicker;
    private Button btnBack;
    private int hour = 0, min = 0;
    private int year = 0, month = 0, date = 0;
    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        timePicker = (TimePicker) findViewById(R.id.tpTime);
        datePicker = (DatePicker) findViewById(R.id.dpDate);
        btnBack = (Button) findViewById(R.id.btnOk);


    }

    public void backToMain(View view) {

        Calendar calendar = Calendar.getInstance();
        TimeZone timezone = TimeZone.getTimeZone("GMT+05:30");
        calendar.setTimeZone(timezone);
        int today = calendar.get(Calendar.DAY_OF_MONTH);
        int nowMonth = calendar.get(Calendar.MONTH);
        int noeYear = calendar.get(Calendar.YEAR);
        int nowHour = calendar.get(Calendar.HOUR_OF_DAY);
        int nowMin = calendar.get(Calendar.MINUTE);
        int currentApiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentApiVersion > android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
            date = datePicker.getDayOfMonth();
            month = datePicker.getMonth();
            year = datePicker.getYear();
            hour = timePicker.getHour();
            min = timePicker.getMinute();
        } else {
            date = datePicker.getDayOfMonth();
            month = datePicker.getMonth();
            year = datePicker.getYear();
            hour = timePicker.getCurrentHour();
            min = timePicker.getCurrentMinute();
        }
        calendar.set(Calendar.DAY_OF_MONTH, date);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND,0);

        int durationHour = hour - nowHour;
        int durationMin = min - nowMin;
        int durationDay = date - today;
        int durationMonth = month - nowMonth;
        int durationYear = year - noeYear;

        Intent myIntent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent, 0);
        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);

        Log.e("samay", String.valueOf(System.currentTimeMillis()));
        Log.e("samay ", String.valueOf(calendar.getTimeInMillis()));

        Toast.makeText(this, "Alarm set for" + durationDay + " day " + durationHour + " hour and " + durationMin + " minute " + "from now", Toast.LENGTH_LONG).show();


        Intent intent = new Intent(this, AlarmActivity.class);
        startActivity(intent);
        finish();
    }
}