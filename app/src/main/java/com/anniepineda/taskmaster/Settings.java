package com.anniepineda.taskmaster;

//Used class codeReview and lesson for reference//

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Set;

public class Settings extends AppCompatActivity{

    private static final String TAG = "anniepineda.settings";

    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_settings);


        Button saveUsername = findViewById(R.id.button4);
        saveUsername.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                TextInputEditText newUsername = findViewById(R.id.username);
                String username = newUsername.getText().toString();
                Log.w(TAG, username);
                SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = p.edit();
                editor.putString("username", username);
                editor.apply();
                Intent newUsernameIntent = new Intent(Settings.this, MainActivity.class);
                Settings.this.startActivity(newUsernameIntent);

            }
        });

    }


}


