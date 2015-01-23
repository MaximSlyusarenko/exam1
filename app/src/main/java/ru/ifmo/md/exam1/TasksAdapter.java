package ru.ifmo.md.exam1;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class TasksAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    List <Task> tasks;

    public TasksAdapter(List <Task> tasks, Context context) {
        this.tasks = tasks;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.task, null, false);
        }
        TextView title = (TextView) convertView.findViewById(R.id.title);
        String titleStr = tasks.get(position).getTitle();
        title.setText(titleStr);

        TextView pubDate = (TextView) convertView.findViewById(R.id.date);
        String date = tasks.get(position).getDate();
        pubDate.setText(date);
        return convertView;
    }

    public void setTasks(List <Task> tasks) {
        this.tasks = tasks;
    }


}
