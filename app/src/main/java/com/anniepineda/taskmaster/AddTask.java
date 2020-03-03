package com.anniepineda.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amazonaws.amplify.generated.graphql.CreateTaskMutation;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.anniepineda.taskmaster.models.Task;
import com.anniepineda.taskmaster.models.TaskmasterDB;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.google.android.material.textfield.TextInputEditText;

import javax.annotation.Nonnull;

import type.CreateTaskInput;

public class AddTask extends AppCompatActivity {

    private TaskmasterDB database;

    static String TAG = "ap.addTask";
    private AWSAppSyncClient mAWSAppSyncClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        mAWSAppSyncClient = AWSAppSyncClient.builder()
                .context(getApplicationContext())
                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
                .build();

        this.database = Room.databaseBuilder(getApplicationContext(), TaskmasterDB.class,"tasks")
                .allowMainThreadQueries().build();

        Button addTaskButton =findViewById(R.id.button3);
        addTaskButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                createTask();
//                EditText title = findViewById(R.id.textInputEditText);
//                EditText description = findViewById(R.id.textInputEditText2);
//
//                String titleStr = title.getText().toString();
//                String descriptionStr = description.getText().toString();
//                Task task = new Task(titleStr, descriptionStr);
//                database.taskDao().addTask(task);


                Toast text = Toast.makeText(getApplicationContext(), "Submitted", Toast.LENGTH_SHORT);
                text.show();

//                AddTask.this.finish();

            }

         });
    }

    public void createTask(){

        TextInputEditText taskNameEditText = findViewById(R.id.textInputEditText);
        String taskName = taskNameEditText.getText().toString();
        EditText description = findViewById(R.id.textInputEditText2);
        String descriptionStr = description.getText().toString();
        CreateTaskInput input = CreateTaskInput.builder()
                .title(taskName)
                .description(descriptionStr)
                .status("In Progress")
                .build();
        mAWSAppSyncClient.mutate(CreateTaskMutation.builder().input(input).build())
                .enqueue(new GraphQLCall.Callback<CreateTaskMutation.Data>() {
                    @Override
                    public void onResponse(@Nonnull Response<CreateTaskMutation.Data> response) {
                        Log.i(TAG,response.data().toString());
                        AddTask.this.startActivity(new Intent(AddTask.this, MainActivity.class));
                    }

                    @Override
                    public void onFailure(@Nonnull ApolloException e) {
                        Log.i(TAG,"Error!");

                    }
                });

    }


}
