package com.anniepineda.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amazonaws.amplify.generated.graphql.ListTasksQuery;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.SignOutOptions;
import com.amazonaws.mobile.client.UserState;
import com.amazonaws.mobile.client.UserStateDetails;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
import com.anniepineda.taskmaster.models.Task;
import com.anniepineda.taskmaster.models.TaskmasterDB;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Nonnull;

public class MainActivity extends AppCompatActivity implements AllTaskRecyclerViewAdapter.OnTaskSelectedListener {
    private AWSAppSyncClient mAWSAppSyncClient;

    private static final String TAG = "anniepineda.main";
    private RecyclerView recyclerView;
    private AllTaskRecyclerViewAdapter taskAdapter;
    //holds everything for recycler view
    private List<Task> tasks;
    private TaskmasterDB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//connecting to AWS (amplify lab) using client to return tasks to recycler view
        mAWSAppSyncClient = AWSAppSyncClient.builder()
                .context(getApplicationContext())
                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
                .build();

        //recycler view tasks
        this.tasks = new LinkedList<>();

//        this.database = Room.databaseBuilder(getApplicationContext(), TaskmasterDB.class,"tasks")
//                .allowMainThreadQueries().build();

//        this.database.taskDao().deleteAll();

        //returns the list of tasks to recycler view
//        this.tasks.addAll(this.database.taskDao().getAll());


        //Cognito
        AWSMobileClient.getInstance().initialize(getApplicationContext(), new Callback<UserStateDetails>() {

                    @Override
                    public void onResult(UserStateDetails userStateDetails) {
                        Log.i("INIT", "onResult: " + userStateDetails.getUserState());
                        if(userStateDetails.getUserState().equals(UserState.SIGNED_OUT)){
                            AWSMobileClient.getInstance().showSignIn(MainActivity.this, new Callback<UserStateDetails>() {
                                @Override
                                public void onResult(UserStateDetails result) {
                                    Log.d(TAG, "onResult: " + result.getUserState());
                                }

                                @Override
                                public void onError(Exception e) {
                                    Log.e(TAG, "onError: ", e);
                                }
                            });
                        }
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e("INIT", "Initialization error.", e);
                    }
                }
        );


        //recycler view set-up
        this.recyclerView = findViewById(R.id.taskRecycler);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.taskAdapter = new AllTaskRecyclerViewAdapter(this.tasks,this);
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

        // LOG OUT BUTTON
        Button logOutButton = findViewById(R.id.logout);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AWSMobileClient.getInstance().signOut(SignOutOptions.builder().signOutGlobally(true).build(), new Callback<Void>() {
                    @Override
                    public void onResult(final Void result) {
                        AWSMobileClient.getInstance().showSignIn(MainActivity.this, new Callback<UserStateDetails>() {
                            @Override
                            public void onResult(UserStateDetails result) {
                                Log.d(TAG, "onResult: " + result.getUserState());
                            }

                            @Override
                            public void onError(Exception e) {
                                Log.d(TAG, "onError", e);
                            }
                        });
                        Log.d(TAG, "sign out");
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e(TAG, "sign out error", e);
                    }
                });
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

        runQuery();

//        this.tasks.clear();
//        this.tasks.addAll(this.database.taskDao().getAll());
//        //triggers recycler view to update tasks/itself
//        this.taskAdapter.notifyDataSetChanged();
    }

    //this method defines what happens when a task is clicked in the recycler view
    @Override
    public void onTaskSelected(Task task){
        Log.i(TAG, "task title clicked " +task.getTitle());
        Intent goToDetail = new Intent(this, TaskDetail.class);
        goToDetail.putExtra("taskTitle",task.getTitle());
        goToDetail.putExtra("taskDescription",task.getDescription());
        goToDetail.putExtra("taskState", task.getState());
        startActivity(goToDetail);

    }


    public void runQuery(){
        mAWSAppSyncClient.query(ListTasksQuery.builder().build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(todosCallback);
    }

    private GraphQLCall.Callback<ListTasksQuery.Data> todosCallback = new GraphQLCall.Callback<ListTasksQuery.Data>() {
        @Override
        public void onResponse(@Nonnull Response<ListTasksQuery.Data> response) {
            Log.i("Results", response.data().listTasks().items().toString());
            tasks.clear();
            for(ListTasksQuery.Item task: response.data().listTasks().items()) {
                Task newTask = new Task(task.title(), task.description());
                tasks.add(newTask);
            }
            Handler handlerForMainThread = new Handler(Looper.getMainLooper()){
                @Override
                public void handleMessage(Message inputMessage){
                    taskAdapter.notifyDataSetChanged();
                }
            };

            handlerForMainThread.obtainMessage().sendToTarget();

        }

        @Override
        public void onFailure(@Nonnull ApolloException e) {
            Log.e("ERROR", e.toString());
        }
    };




}


