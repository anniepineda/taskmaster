package com.anniepineda.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Toast;

import com.anniepineda.taskmaster.models.Task;
import com.anniepineda.taskmaster.models.TaskmasterDB;

import java.util.List;

public class AllTasks extends AppCompatActivity implements AllTaskRecyclerViewAdapter.OnTaskSelectedListener {

    static TaskmasterDB database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks2);

        database = Room.databaseBuilder(getApplicationContext(), TaskmasterDB.class,"tasks")
                .allowMainThreadQueries().build();


        List<Task> listOfTasks = database.taskDao().getAll();
        RecyclerView rv = findViewById(R.id.allTaskRecyclerView);
        rv.setLayoutManager(new LinearLayoutManager(AllTasks.this));
        rv.setAdapter(new AllTaskRecyclerViewAdapter(listOfTasks, AllTasks.this));

    }
    @Override
    public void onTaskSelected(Task task) {
        Toast t = new Toast(this);
        CharSequence chars = task.getDescription();
        Toast.makeText(getApplicationContext(), chars, Toast.LENGTH_SHORT).show();
    }



}
