package com.cleanup.todoc.database;

import androidx.room.Database;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

@Database(entities = {Task.class, Project.class}, version = 1, exportSchema = false)

public abstract class TodocRoomDatabase extends androidx.room.RoomDatabase {
    public  abstract TodocRoomDao roomDao();
}
