package com.example.shwethavasudevan.miniproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shwethavasudevan.helper.SqLiteHelper;
import com.example.shwethavasudevan.model.User;

public class MainActivity extends AppCompatActivity {

    SqLiteHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button registerButton = findViewById(R.id.registerbtn);
        Button loginButton = findViewById(R.id.loginbtn);

        helper = new SqLiteHelper(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()) {
                    String username = ((EditText)findViewById(R.id.userntxt)).getText().toString();
                    String password = ((EditText)findViewById(R.id.passwtxt)).getText().toString();

                    User currentUser = helper.authenticateUser(new User(username,password,null,null,null));
                    if(currentUser != null) {
                        Toast.makeText(getApplicationContext(),"Welcome, " + username,Toast.LENGTH_SHORT);
                        Intent fruitsIntent = new Intent(getApplicationContext(),FruitsActivity.class);
                        startActivity(fruitsIntent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Incorrect username or password",Toast.LENGTH_SHORT);
                    }
                }

            }
        });
    }

    public boolean validate() {

        boolean valid = true;
        String username = ((EditText)findViewById(R.id.userntxt)).getText().toString();
        String password = ((EditText)findViewById(R.id.passwtxt)).getText().toString();

        if(username.isEmpty() && password.isEmpty()) {
            Toast.makeText(getApplicationContext(),"Please enter a valid username and password",Toast.LENGTH_SHORT).show();
            valid = false;
        }

        if(username.isEmpty()) {
            Toast.makeText(getApplicationContext(),"Enter username",Toast.LENGTH_SHORT).show();
            valid = false;
        }
        if (password.isEmpty()) {
            Toast.makeText(getApplicationContext(),"Enter a valid password",Toast.LENGTH_SHORT).show();
            valid = false;
        }

        return true;
    }

}
