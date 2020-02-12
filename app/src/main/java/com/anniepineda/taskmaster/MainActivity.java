package com.anniepineda.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Go to add task
        Button goToAddTask = findViewById(R.id.button);
        goToAddTask.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent goToAddTaskView = new Intent(MainActivity.this, AddTask.class);
                MainActivity.this.startActivity(goToAddTaskView);
            }
        });
        // Go to add task
        Button goToAllTask = findViewById(R.id.button2);
        goToAllTask.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent goToTasksView = new Intent(MainActivity.this, AllTasks.class);
                MainActivity.this.startActivity(goToTasksView);
            }
        });






    }








}
