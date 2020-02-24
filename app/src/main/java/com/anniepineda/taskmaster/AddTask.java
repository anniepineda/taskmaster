package com.anniepineda.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anniepineda.taskmaster.models.Task;
import com.anniepineda.taskmaster.models.TaskmasterDB;

public class AddTask extends AppCompatActivity {

    private TaskmasterDB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        this.database = Room.databaseBuilder(getApplicationContext(), TaskmasterDB.class,"tasks")
                .allowMainThreadQueries().build();

        Button addTaskButton =findViewById(R.id.button3);
        addTaskButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {


                EditText title = findViewById(R.id.textInputEditText);
                EditText description = findViewById(R.id.textInputEditText2);

                String titleStr = title.getText().toString();
                String descriptionStr = description.getText().toString();
                Task task = new Task(titleStr, descriptionStr);

                database.taskDao().addTask(task);


                Toast text = Toast.makeText(getApplicationContext(), "Submitted", Toast.LENGTH_SHORT);
                text.show();

                AddTask.this.finish();

            }

         });
    }


}
