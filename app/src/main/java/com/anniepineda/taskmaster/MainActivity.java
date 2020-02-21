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
                buyCupcakes.putExtra("task", "Buy Cupcakes");
                MainActivity.this.startActivity(buyCupcakes);
            }
        });

        //Go to task detail from eat cupcakes
        final Button goToEatCupcakesTaskDetail = findViewById(R.id.button8);
        goToEatCupcakesTaskDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent eatCupcakes = new Intent(MainActivity.this, TaskDetail.class);
                eatCupcakes.putExtra("task", "Eat Cupcakes");
                MainActivity.this.startActivity(eatCupcakes);
            }
        });

        //Go to task detail from buy cookies
        Button goToBuyCookiesTaskDetail = findViewById(R.id.button9);
        goToBuyCookiesTaskDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent buyCookies = new Intent(MainActivity.this, TaskDetail.class);
                buyCookies.putExtra("task", "Buy Cookies");
                MainActivity.this.startActivity(buyCookies);
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

//    LAB 32  Requirements-------------------------------------
//    Feature Tasks
//    Tasks Are Cloudy
//        Using the amplify add api command, create a Task resource that replicates our existing Task schema. Update all references to the Task data to instead use AWS Amplify to access your data in DynamoDB instead of in Room.
//
//        Add Task Form
//        Modify your Add Task form to save the data entered in as a Task to DynamoDB.
//
//        Homepage
//        Refactor your homepage’s RecyclerView to display all Task entities in DynamoDB.

