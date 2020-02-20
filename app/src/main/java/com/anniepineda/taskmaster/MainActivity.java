package com.anniepineda.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "anniepineda.main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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






        //Go to task detail from buy cupcakes
        Button goToBuyCupcakesTaskDetail = findViewById(R.id.button7);
        goToBuyCupcakesTaskDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent buyCupcakes = new Intent(MainActivity.this, TaskDetail.class);
                MainActivity.this.startActivity(buyCupcakes);
            }
        });

        //Go to task detail from eat cupcakes
        Button goToEatCupcakesTaskDetail = findViewById(R.id.button8);
        goToEatCupcakesTaskDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent buyCupcakes = new Intent(MainActivity.this, TaskDetail.class);
                MainActivity.this.startActivity(buyCupcakes);
            }
        });

        //Go to task detail from buy cookies
        Button goToBuyCookiesTaskDetail = findViewById(R.id.button9);
        goToBuyCookiesTaskDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToTasksView = new Intent(MainActivity.this, TaskDetail.class);
                MainActivity.this.startActivity(goToTasksView);
            }
        });







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









}
