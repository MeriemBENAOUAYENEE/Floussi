package com.example.floussi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;


public class depense extends AppCompatActivity  {
    // define the global variable
    EditText expenses,cost,when;
    // Add button Move to next Activity and previous Activity
    Button next_button, previous_button, save;
    private AppDataBase dataBase;

    public void add (DepenseClass d){
        dataBase.depenseDAO().insertDepense(d);
    }

    public List<DepenseClass> diplay (DepenseClass d){
        return dataBase.depenseDAO().getDepense();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depense);


        dataBase = AppDataBase.getInstance(this);


            // by ID we can use each component which id is assign in xml
            // file use findViewById() to get the both Button and textview
            next_button = (Button) findViewById(R.id.second_activity_next_button);
            previous_button = (Button) findViewById(R.id.second_activity_previous_button);
            expenses = findViewById(R.id.expenses);
            cost = findViewById(R.id.cost);
            when = findViewById(R.id.when);
            save = (Button) findViewById(R.id.save);


            // Add_button add clicklistener
            next_button.setOnClickListener(v -> {
                // Intents are objects of the android.content.Intent type. Your code can send them to the Android system defining
                // the components you are targeting. Intent to start an activity called ThirdActivity with the following code.
                Intent intent = new Intent(depense.this, historique.class);
                // start the activity connect to the specified class
                startActivity(intent);
            });


        // Add_button add clicklistener
        save.setOnClickListener(v -> {
            DepenseClass d = new DepenseClass(expenses.getText().toString(),Float.valueOf(cost.getText().toString()),0,when.getText().toString());
            add(d);
        });

            // Add_button add clicklistener
            previous_button.setOnClickListener(v -> {
                // Intents are objects of the android.content.Intent type. Your code can send them to the Android system defining
                // the components you are targeting. Intent to start an activity called oneActivity with the following code
                Intent intent = new Intent(depense.this, MainActivity.class);
                // start the activity connect to the specified class
                startActivity(intent);
            });
    }

}