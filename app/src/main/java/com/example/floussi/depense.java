package com.example.floussi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.TypeConverters;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;
import java.util.Calendar;


public class depense extends AppCompatActivity  {
    // define the global variable
    EditText expenses,cost;
    Calendar calendarr = Calendar.getInstance();
    // Add button Move to next Activity and previous Activity
    Button chargeVariable, chargeFix, save ,retour,getDatebutton,changedate;
    TextView fix,variable,dateText,when;
    String type="chargeVariable";

    private AppDataBase dataBase;

    TextView balance;
    private DatePicker datePicker;

    private SharedPreferences mPreferences;
    public static final String balanceDB ="90000";
    public static final String shared_STRING ="mainShared";

    private void datePickerChange(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Log.d("Date", "Year=" + year + " Month=" + (month + 1) + " day=" + dayOfMonth);
        dateText.setText(dayOfMonth +"-" + (month + 1) + "-" + year);
    }

    private void buttonDisplay(){

        if (type == "chargeVariable")
        {
            chargeVariable.setVisibility(View.GONE);
            variable.setVisibility(View.VISIBLE);
            chargeFix.setVisibility(View.VISIBLE);
            fix.setVisibility(View.GONE);
        }
        else{
            chargeVariable.setVisibility(View.VISIBLE);
            variable.setVisibility(View.GONE);
            chargeFix.setVisibility(View.GONE);
            fix.setVisibility(View.VISIBLE);
        }
    }

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
            expenses = findViewById(R.id.expenses);
            chargeVariable = findViewById(R.id.buttonVariable);
            variable =findViewById(R.id.TxtVariable);
            fix =findViewById(R.id.TxtFix);
            retour = findViewById(R.id.retour);
            chargeFix = findViewById(R.id.buttonFix);
            cost = findViewById(R.id.cost);
            when = findViewById(R.id.when);
            save = (Button) findViewById(R.id.save);
            balance =  findViewById(R.id.balanceD);

// date
        datePicker = (DatePicker) this.findViewById(R.id.datePicker);
        dateText= findViewById(R.id.TxtGetDate);
        changedate = findViewById(R.id.changedate);
        getDatebutton = findViewById(R.id.GetDate);
        datePicker.setVisibility(View.GONE);
        dateText.setVisibility(View.GONE);
        getDatebutton.setVisibility(View.GONE);





       





        buttonDisplay();


        mPreferences = getSharedPreferences(shared_STRING, MODE_PRIVATE);
      //  balance.setText(mPreferences.getString(balanceDB,"6"));


        chargeVariable.setOnClickListener(v -> {
            type = "chargeVariable";
            chargeVariable.setVisibility(View.GONE);
            variable.setVisibility(View.VISIBLE);
            chargeFix.setVisibility(View.VISIBLE);
        });

        chargeFix.setOnClickListener(v -> {
            type = "chargeFix";
            chargeFix.setVisibility(View.GONE);
            fix.setVisibility(View.VISIBLE);
            chargeVariable.setVisibility(View.VISIBLE);
        });


        balance.setText(mPreferences.getString(balanceDB,"6"));


        // Add_button add clicklistener
        save.setOnClickListener(v -> {





            //   balance.setText(mPreferences.getString(balanceDB,"6"));
            if (type == "chargeVariable"){
                DepenseClass d = new DepenseClass(expenses.getText().toString(),Float.valueOf(cost.getText().toString()),0,calendarr.getTime(), "Variable");
            add(d);
            //    balanceint = balanceint - Float.valueOf(cost.getText().toString());

                Toast.makeText(this,"ChargeVariable:" + expenses.getText().toString() + " Saved" ,Toast.LENGTH_LONG).show();
            }

            else {
                DepenseClass d = new DepenseClass(expenses.getText().toString(),Float.valueOf(cost.getText().toString()),0,calendarr.getTime(),"Fix");
                add(d);
                Toast.makeText(this,"ChargeFix:" + expenses.getText().toString() + " Saved" ,Toast.LENGTH_LONG).show();
               // balanceint = balanceint - Float.valueOf(cost.getText().toString());
            }

            float f = Float.valueOf(mPreferences.getString(balanceDB,"6")) - Float.valueOf(cost.getText().toString());
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putString(balanceDB,String.valueOf(f));
            editor.commit();
            balance.setText(mPreferences.getString(balanceDB,""));

            // Intents are objects of the android.content.Intent type. Your code can send them to the Android system defining
            // the components you are targeting. Intent to start an activity called SecondActivity with the following code.
            Intent intent = new Intent(depense.this, MainActivity.class);
            // start the activity connect to the specified class
            startActivity(intent);


        });

        retour.setOnClickListener(v -> {
            // Intents are objects of the android.content.Intent type. Your code can send them to the Android system defining
            // the components you are targeting. Intent to start an activity called SecondActivity with the following code.
            Intent intent = new Intent(depense.this, MainActivity.class);
            // start the activity connect to the specified class
            startActivity(intent);
        });

        changedate.setOnClickListener(v -> {

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            int year = calendar.get(Calendar.YEAR);
            int month  = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            datePicker.init( year, month , day , new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                    datePickerChange(  datePicker,   year,   month,   dayOfMonth);
                }
            });

            datePicker.setVisibility(View.VISIBLE);
            dateText.setVisibility(View.VISIBLE);
            getDatebutton.setVisibility(View.VISIBLE);

            chargeVariable.setVisibility(View.GONE);
            variable.setVisibility(View.GONE);
            chargeFix.setVisibility(View.GONE);
            fix.setVisibility(View.GONE);

            expenses.setVisibility(View.GONE);
            changedate.setVisibility(View.GONE);
            retour.setVisibility(View.GONE);
            cost.setVisibility(View.GONE);
            when.setVisibility(View.GONE);
            save.setVisibility(View.GONE);
        });


        getDatebutton.setOnClickListener(v -> {
            datePicker.setVisibility(View.GONE);
            dateText.setVisibility(View.GONE);
            getDatebutton.setVisibility(View.GONE);

            buttonDisplay();


            expenses.setVisibility(View.VISIBLE);
            changedate.setVisibility(View.VISIBLE);
            retour.setVisibility(View.VISIBLE);
            cost.setVisibility(View.VISIBLE);
            when.setVisibility(View.VISIBLE);
            save.setVisibility(View.VISIBLE);
            int yearr = datePicker.getYear();
            int monthh = datePicker.getMonth(); // 0 - 11
            int dayy = datePicker.getDayOfMonth();

            calendarr.set(yearr, monthh, dayy);
        });


    }

}