package com.example.shwethavasudevan.miniproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shwethavasudevan.helper.SqLiteHelper;

public class RegisterActivity extends AppCompatActivity {

    SqLiteHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        String username = ((EditText)findViewById(R.id.userntxt)).getText().toString();
        String password = ((EditText)findViewById(R.id.passwtxt)).getText().toString();
        String conpassword = ((EditText)findViewById(R.id.cpasstxt)).getText().toString();
        String dob = ((EditText)findViewById(R.id.dobtxt)).getText().toString();
        String address = ((EditText)findViewById(R.id.addresstxt)).getText().toString();
        String email = ((EditText)findViewById(R.id.emailtxt)).getText().toString();

        if(password.equals(conpassword)) {
            //TODO
        }

        else {
            Toast.makeText(getApplicationContext(),"'Password' and 'Confirm Password' fields do not match",Toast.LENGTH_SHORT).show();
            
        }

    }
}
