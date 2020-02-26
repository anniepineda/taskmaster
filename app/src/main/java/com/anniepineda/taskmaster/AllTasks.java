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

        //*********** when UNCOMENTING THIS NEXT LINE ALSO UNCOMENT rv.setAdapter(new MyTaskRecy.....)*********
        List<Task> listOfTasks = database.taskDao().getAll();
//        List<Task> items = new ArrayList<>();
//        items.add(new Task("test1", "description1"));
//        items.add(new Task("test2", "description2"));
//        items.add(new Task("test3", "description3"));
        RecyclerView rv = findViewById(R.id.allTaskRecyclerView);
        rv.setLayoutManager(new LinearLayoutManager(AllTasks.this));
        rv.setAdapter(new AllTaskRecyclerViewAdapter(listOfTasks, AllTasks.this));


//        rv.setAdapter(new MyTaskRecyclerViewAdapter(items, this));
    }
    @Override
    public void onTaskSelected(Task task) {
        Toast t = new Toast(this);
        CharSequence chars = task.getDescription();
        Toast.makeText(getApplicationContext(), chars, Toast.LENGTH_SHORT).show();
    }



}
