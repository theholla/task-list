package com.epicodus.tasklist.ui;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.activeandroid.query.Select;
import com.epicodus.tasklist.R;
import com.epicodus.tasklist.models.Category;
import com.epicodus.tasklist.models.Task;

import java.util.ArrayList;

public class CategoryActivity extends ListActivity {
    private Category mCategory;
    private ArrayList<String> mTasks;
    private Button mNewTaskButton;
    private EditText mNewTaskText;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // find the category name from the intent
        String name = getIntent().getStringExtra("categoryName");
        mCategory = Category.find(name);

        mNewTaskButton = (Button) findViewById(R.id.newTaskButton);
        mNewTaskText = (EditText) findViewById(R.id.newTask);

        mTasks = new ArrayList<String>();
        for ( Task task : mCategory.tasks() ) {
            mTasks.add(task.getDescription());
        }

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mTasks);
        // use this adapter to populate the list:
        setListAdapter(mAdapter);

        mNewTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
            }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String taskInDatabase = mAdapter.getItem(position);
        Task taskToDelete = Task.find(taskInDatabase);
        taskToDelete.delete();
        mAdapter.remove(taskInDatabase);
        mAdapter.notifyDataSetChanged();
    }

    private void addTask() {
        String description = mNewTaskText.getText().toString();
        Task newTask = new Task(description, mCategory);
        newTask.save();
        mNewTaskText.setText("");
        mTasks.add(description);
        // signal to adapter that a task is added so it can rerender the ListView
        mAdapter.notifyDataSetChanged();
    }
}
