package ru.ifmo.md.exam1;

import android.content.ContentValues;
import android.content.Intent;
import android.inputmethodservice.ExtractEditText;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;

import java.util.Calendar;

public class AddTaskActivity extends ActionBarActivity {

    private EditText title;
    private EditText description;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_task);
        title = (EditText) findViewById(R.id.title);
        description = (EditText) findViewById(R.id.description);
    }

    public void onAdd(View view) {
        String t = title.getText().toString();
        String d = description.getText().toString();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        String hr = Integer.toString(hour);
        if (hour < 10) {
            hr = "0" + hr;
        }
        String mn = Integer.toString(minute);
        if (minute < 10) {
            mn = "0" + mn;
        }
        month++;
        String mth = Integer.toString(month);
        if (month < 10) {
            mth = "0" + month;
        }
        String date = hr + ":" + mn + " " + Integer.toString(day) + "." + mth + "." + Integer.toString(year);
        ContentValues cv = new ContentValues();
        cv.put("date", date);
        cv.put("description", d);
        cv.put("title", t);
        getContentResolver().insert(MyContentProvider.TASKS_CONTENT_URI, cv);
    }

    public void onBackPressed() {
        Intent intent = new Intent(AddTaskActivity.this, MainActivity.class);
        intent.putExtra("first", false);
        startActivity(intent);
    }
}
