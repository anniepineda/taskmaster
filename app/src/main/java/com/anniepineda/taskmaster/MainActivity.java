package com.anniepineda.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.anniepineda.taskmaster.fragments.MyTaskRecyclerViewAdapter;
import com.anniepineda.taskmaster.models.Task;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyTaskRecyclerViewAdapter.OnTaskSelectedListener {

    private static final String TAG = "anniepineda.main";
    private RecyclerView recyclerView;
    private MyTaskRecyclerViewAdapter taskAdapter;
    //holds everything for recycler view
    private List<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Task taskOne = new Task("Buy Cupcakes", "Get Pink Frosting Cupcakes");
        Task taskTwo = new Task("Eat Cupcakes", "Finish All Cupcakes By 3pm");
        Task taskThree = new Task("Buy Cookies", "Buy Chocolate Chip Cookies");
        tasks = new LinkedList<> ();
        tasks.add(taskOne);
        tasks.add(taskTwo);
        tasks.add(taskThree);


        //recycler view ser-up
        this.recyclerView = findViewById(R.id.taskRecycler);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.taskAdapter = new MyTaskRecyclerViewAdapter(this.tasks,this);
        this.recyclerView.setAdapter(this.taskAdapter);


        //Go to add task
        Button goToAddTask = findViewById(R.id.button);
        goToAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToAddTaskView = new Intent(MainActivity.this, AddTask.class);
                MainActivity.this.startActivity(goToAddTaskView);
            }
        });
        // Go to all task
        Button goToAllTask = findViewById(R.id.button2);
        goToAllTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToTasksView = new Intent(MainActivity.this, AllTasks.class);
                MainActivity.this.startActivity(goToTasksView);
            }
        });
        //Go to task detail
        Button goToTaskDetail = findViewById(R.id.button6);
        goToTaskDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToTasksView = new Intent(MainActivity.this, TaskDetail.class);
                MainActivity.this.startActivity(goToTasksView);
            }
        });


//        //Go to task detail from buy cupcakes
//        Button goToBuyCupcakesTaskDetail = findViewById(R.id.button7);
//        goToBuyCupcakesTaskDetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent buyCupcakes = new Intent(MainActivity.this, TaskDetail.class);
//                buyCupcakes.putExtra("task", "Buy Cupcakes");
//                MainActivity.this.startActivity(buyCupcakes);
//            }
//        });
//
//        //Go to task detail from eat cupcakes
//        final Button goToEatCupcakesTaskDetail = findViewById(R.id.button8);
//        goToEatCupcakesTaskDetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent eatCupcakes = new Intent(MainActivity.this, TaskDetail.class);
//                eatCupcakes.putExtra("task", "Eat Cupcakes");
//                MainActivity.this.startActivity(eatCupcakes);
//            }
//        });
//
//        //Go to task detail from buy cookies
//        Button goToBuyCookiesTaskDetail = findViewById(R.id.button9);
//        goToBuyCookiesTaskDetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent buyCookies = new Intent(MainActivity.this, TaskDetail.class);
//                buyCookies.putExtra("task", "Buy Cookies");
//                MainActivity.this.startActivity(buyCookies);
//            }
//        });


        //Go to settings
        Button goToSettings = findViewById(R.id.button5);
        goToSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSettings = new Intent(MainActivity.this, Settings.class);
                MainActivity.this.startActivity(goToSettings);
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences userLoggedIn = PreferenceManager.getDefaultSharedPreferences(this);
        String userID = userLoggedIn.getString("username", "default");
        TextView headerTask = findViewById(R.id.usernameTasks);
        headerTask.setText(userID);
    }

    //this method defines what happens when a task is clicked in the recycler view
    @Override
    public void onTaskSelected(Task task){
        Log.i(TAG, "task title clicked " +task.getTitle());
    }




}


