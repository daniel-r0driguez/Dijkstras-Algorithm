package com.example.cs003b_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onRegisterClick(View view)
    {
        EditText firstNameInput = findViewById(R.id.firstNameTextInput);
        EditText lastNameInput = findViewById(R.id.lastNameTextInput);
        EditText emailInput = findViewById(R.id.emailTextInput);

        TextView firstNameTxt = findViewById(R.id.firstNameText);
        TextView lastNameTxt = findViewById(R.id.lastNameText);
        TextView emailTxt = findViewById(R.id.emailText);

        CharSequence txt = "First Name: " + firstNameInput.getText().toString();
        firstNameTxt.setText(txt);
        txt = "Last Name: " + lastNameInput.getText().toString();
        lastNameTxt.setText(txt);
        txt = "Email: " + emailInput.getText().toString();
        emailTxt.setText(txt);
    }
}