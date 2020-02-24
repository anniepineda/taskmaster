package com.anniepineda.taskmaster;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class TaskDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_task_detail);
        Intent intent = getIntent();
        String title = intent.getExtras().getString("taskTitle");
        String description = intent.getExtras().getString("taskDescription");
        String state = intent.getExtras().getString("taskState");

        TextView titleTextView = findViewById(R.id.taskTitle);
        TextView descriptionTextView = findViewById(R.id.description);
        TextView stateTextView = findViewById(R.id.state);
        titleTextView.setText(title);
        descriptionTextView.setText(description);
        stateTextView.setText(state);

    }

    @Override
    protected void onStart(){
        super.onStart();
    }



}
