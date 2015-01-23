package ru.ifmo.md.exam1;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class MainActivity extends ActionBarActivity implements LoaderManager.LoaderCallbacks <List<Task>> {

    private ListView tasks;
    private TasksAdapter adapter;
    private boolean first;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tasks = (ListView) findViewById(R.id.tasks);
        first = getIntent().getBooleanExtra("first", true);
        adapter = new TasksAdapter(new ArrayList<Task>(), this);
        tasks.setAdapter(adapter);
        tasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, FullTaskActivity.class);
                Task currentTask = (Task) adapter.getItem(position);
                intent.putExtra("title", currentTask.getTitle());
                intent.putExtra("description", currentTask.getDescription());
                startActivity(intent);
            }
        });
        if (first) {
            getLoaderManager().initLoader(1, null, MainActivity.this);
        } else {
            getLoaderManager().restartLoader(1, null, MainActivity.this);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onNew(View view) {
        Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
        startActivity(intent);
    }

    @Override
    public Loader <List <Task>> onCreateLoader(int i, Bundle bundle) {
        return new TasksLoader(MainActivity.this);
    }

    @Override
    public void onLoadFinished(Loader <List <Task>> listLoader, List <Task> tasks) {
        adapter.setTasks(tasks);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader <List <Task>> listLoader) {
        adapter = new TasksAdapter(new ArrayList<Task>(), MainActivity.this);
        adapter.notifyDataSetChanged();
    }
}
