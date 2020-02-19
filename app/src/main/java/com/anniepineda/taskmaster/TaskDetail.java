package com.anniepineda.taskmaster;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TaskDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_task_detail);
    }

    @Override
    protected void onStart(){
        super.onStart();
        TextView taskTitle = findViewById(R.id.textView2);
        String title = getIntent().getStringExtra("task");

        //taskTitle.setText(title);
        //sets title of task in view
    }



}
