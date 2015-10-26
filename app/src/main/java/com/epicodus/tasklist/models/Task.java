package com.epicodus.tasklist.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by admin on 10/26/15.
 */

// SQLite table should always be plural form of class name
@Table(name = "Tasks", id = "_id")

public class Task extends Model {
    // provides two-way mapping to the database
    @Column(name = "Description")
    private String mDescription;

    public Task() {
        super();
    }

    public Task(String description) {
        super();
        mDescription = description;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public static List<Task> all() {
        // Active Android translates this query into SQL, sends data, converts into objects, returns
        return new Select().from(Task.class).execute();
    }
}
