package com.example.floussi;

import static java.lang.String.valueOf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    // define the global variable

    Button next_Activity_button,historique,stat;

    TextView balance;

    private SharedPreferences mPreferences;
    public static final String balanceDB ="90000";
    public static final String shared_STRING ="mainShared";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = getSharedPreferences(shared_STRING, MODE_PRIVATE);







        // by ID we can use each component which id is assign in xml file

        next_Activity_button = (Button) findViewById(R.id.buttonDepense);
        historique = (Button) findViewById(R.id.buttonHistory);
        balance =  findViewById(R.id.balanceM);
        stat= findViewById(R.id.buttonParam);




        balance.setText(mPreferences.getString(balanceDB,""));




        //Add_button clicklistener
        next_Activity_button.setOnClickListener(v -> {



            // Intents are objects of the android.content.Intent type. Your code can send them to the Android system defining
            // the components you are targeting. Intent to start an activity called SecondActivity with the following code.
            Intent intent = new Intent(MainActivity.this, depense.class);
            // start the activity connect to the specified class
            startActivity(intent);
        });

        historique.setOnClickListener(v -> {
            // Intents are objects of the android.content.Intent type. Your code can send them to the Android system defining
            // the components you are targeting. Intent to start an activity called SecondActivity with the following code.
            Intent intent = new Intent(MainActivity.this, historique.class);
            // start the activity connect to the specified class
            startActivity(intent);
        });

        stat.setOnClickListener(v -> {
            // Intents are objects of the android.content.Intent type. Your code can send them to the Android system defining
            // the components you are targeting. Intent to start an activity called SecondActivity with the following code.
            Intent intent = new Intent(MainActivity.this, updateDelete.class);
            // start the activity connect to the specified class
            startActivity(intent);
        });
    }
}