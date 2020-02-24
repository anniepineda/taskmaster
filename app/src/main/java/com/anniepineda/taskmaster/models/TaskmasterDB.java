package com.anniepineda.taskmaster.models;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class}, exportSchema = false, version = 1)
public abstract class TaskmasterDB extends RoomDatabase{
    public abstract TaskDao taskDao();

}
