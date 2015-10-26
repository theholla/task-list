package com.epicodus.tasklist.ui;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.tasklist.R;
import com.epicodus.tasklist.models.Task;

import java.util.ArrayList;

public class MainActivity extends ListActivity {
    private ArrayList<String> mTasks;
    private Button mNewTaskutton;
    private EditText mNewTaskText;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNewTaskutton = (Button) findViewById(R.id.newTaskButton);
        mNewTaskText = (EditText) findViewById(R.id.newTask);

        mTasks = new ArrayList<String>();
        for ( Task task : Task.all() ) {
            mTasks.add(task.getDescription());
        }

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mTasks);
        // use this adapter to populate the list:
        setListAdapter(mAdapter);

        mNewTaskutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
            }
        });
    }

    private void addTask() {
        String description = mNewTaskText.getText().toString();
        Task newTask = new Task(description);
        newTask.save();
        mTasks.add(description);
        // signal to adapter that a task is added so it can rerender the ListView
        mAdapter.notifyDataSetChanged();
    }

}
