package com.example.alarmmanager;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DatePickerFragment.DialogDateListener, TimePickerFragment.DialogTimeListener {
    TextView date;
    TextView time;
    EditText message;
    ImageButton btnDate;
    ImageButton btnTime;
    Button dateReminder;
    Button cancel;

    ImageButton btnAlarm;
    Button setAlarmReminder;
    TextView setAlarm;

    private AlarmReceiver alarmReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date = findViewById(R.id.setDate);
        btnDate = findViewById(R.id.btn_date);
        time = findViewById(R.id.setTime);
        btnTime = findViewById(R.id.btn_time);
        message = findViewById(R.id.messageReminder);
        dateReminder = findViewById(R.id.setDateReminder);
        setAlarm = findViewById(R.id.setAlarm);
        cancel = findViewById(R.id.btn_cancel);

        setAlarmReminder = findViewById(R.id.setAlarmReminder);
        btnAlarm = findViewById(R.id.btn_Alarm);
        btnDate.setOnClickListener(this);
        btnTime.setOnClickListener(this);
        dateReminder.setOnClickListener(this);
        cancel.setOnClickListener(this);


        btnAlarm.setOnClickListener(this);
        setAlarmReminder.setOnClickListener(this);

        alarmReceiver = new AlarmReceiver();

    }

    final String DATE_PICKER_TAG = "DatePicker";
    final String TIME_PICKER_ONCE_TAG = "TimePickerOnce";
    final String TIME_PICKER_REPEAT_TAG = "TimePickerRepeat";

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_date:
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getSupportFragmentManager(), DATE_PICKER_TAG);
                break;
            case R.id.btn_time:
                TimePickerFragment timePickerFragmentOne = new TimePickerFragment();
                timePickerFragmentOne.show(getSupportFragmentManager(), TIME_PICKER_ONCE_TAG);
                break;
            case R.id.setDateReminder:
                String onceDate = date.getText().toString();
                String onceTime = time.getText().toString();
                String onceMessage = dateReminder.getText().toString();

                alarmReceiver.setOneTimeAlarm(this, AlarmReceiver.TYPE_DATE_REMINDER, onceDate, onceTime, onceMessage);
                break;
            case R.id.btn_Alarm:
                TimePickerFragment timePickerFragment = new TimePickerFragment();
                timePickerFragment.show(getSupportFragmentManager(),TIME_PICKER_REPEAT_TAG);
                break;
            case R.id.setAlarmReminder:
                String repeatTime = setAlarm.getText().toString();
                String repeatMessage = message.getText().toString();
                alarmReceiver.setRepeatingAlarm(this, AlarmReceiver.TYPE_REPEATING,repeatTime,repeatMessage);
                break;
            case R.id.btn_cancel:
                alarmReceiver.cancelAlarm(this, AlarmReceiver.TYPE_REPEATING);
                break;
        }
    }

    @Override
    public void onDialogDateSet(String tag, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        date.setText(dateFormat.format(calendar.getTime()));
    }

    @Override
    public void onDialogTimeSet(String tag, int hourOfDay, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        switch (tag) {
            case TIME_PICKER_ONCE_TAG:
                time.setText(dateFormat.format(calendar.getTime()));
                break;
            case TIME_PICKER_REPEAT_TAG:
                setAlarm.setText(dateFormat.format(calendar.getTime()));
                break;
            default:
                break;
        }
    }
}
