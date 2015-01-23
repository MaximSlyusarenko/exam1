package ru.ifmo.md.exam1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

public class FullTaskActivity extends ActionBarActivity {

    private TextView title;
    private TextView description;
    String t;
    String d;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_task);
        title = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.description);
        t = getIntent().getStringExtra("title");
        d = getIntent().getStringExtra("description");
        title.setText(t);
        description.setText(d);
    }

    public void onRemove(View view) {
        getContentResolver().delete(MyContentProvider.TASKS_CONTENT_URI, SQLiteHelper.TITLE + "=? AND " + SQLiteHelper.DESCRIPTION + "=?", new String[]{t, d});
        onBackPressed();
    }

    public void onEdit(View view) {
        Intent intent = new Intent(FullTaskActivity.this, EditActivity.class);
        intent.putExtra("title", t);
        intent.putExtra("description", d);
        startActivity(intent);
    }

    public void onBackPressed() {
        Intent intent = new Intent(FullTaskActivity.this, MainActivity.class);
        intent.putExtra("first", false);
        startActivity(intent);
    }
}
