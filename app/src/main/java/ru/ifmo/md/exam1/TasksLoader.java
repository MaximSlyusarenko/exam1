package ru.ifmo.md.exam1;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.List;

public class TasksLoader extends AsyncTaskLoader <List <Task>> {

    ArrayList <Task> tasks;
    Context context;

    TasksLoader(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public List <Task> loadInBackground() {
        tasks = new ArrayList<Task>();
        Cursor cursor = context.getContentResolver().query(MyContentProvider.TASKS_CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String title = cursor.getString(cursor.getColumnIndex(SQLiteHelper.TITLE));
                String description = cursor.getString(cursor.getColumnIndex(SQLiteHelper.DESCRIPTION));
                String date = cursor.getString(cursor.getColumnIndex(SQLiteHelper.DATE));
                tasks.add(new Task(title, description, date));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return tasks;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }
}
