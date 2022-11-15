package com.example.floussi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.Calendar;


public class depense extends AppCompatActivity  {
    // define the global variable
    EditText expenses,cost;
    Calendar calendarr = Calendar.getInstance();
    Button chargeVariable, chargeFix, save ,retour,getDatebutton,changedate;
    TextView fix,variable,dateText,when,whenDefault;
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
            whenDefault = findViewById(R.id.whenDefault);
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





        balance.setText(mPreferences.getString(balanceDB,"6"));


        // Add_button add clicklistener
        save.setOnClickListener(v -> {

            //   balance.setText(mPreferences.getString(balanceDB,"6"));
            if (type == "chargeVariable"){
                DepenseClass d = new DepenseClass(expenses.getText().toString(),Float.valueOf(cost.getText().toString()),0,calendarr.getTime(), "Variable");
            add(d);


                float f = Float.valueOf(mPreferences.getString(balanceDB,"6")) - Float.valueOf(cost.getText().toString());
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putString(balanceDB,String.valueOf(f));
                editor.commit();

                //alert
                // Create the object of AlertDialog Builder class
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                // Set the message show for the Alert time
                builder.setMessage("Your balance is modified to " + mPreferences.getString(balanceDB,"") + " because it is a variable Expenses");

                // Set Alert Title
                builder.setTitle("$$");

                // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
                builder.setCancelable(false);

                // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
                builder.setPositiveButton("ok", (DialogInterface.OnClickListener) (dialog, which) -> {
                    // Intents are objects of the android.content.Intent type. Your code can send them to the Android system defining
                    // the components you are targeting. Intent to start an activity called SecondActivity with the following code.
                    Intent intent = new Intent(depense.this, MainActivity.class);
                    // start the activity connect to the specified class
                    startActivity(intent);
                });

                // Create the Alert dialog
                AlertDialog alertDialog = builder.create();
                // Show the Alert Dialog box
                alertDialog.show();          }

            else {
                DepenseClass d = new DepenseClass(expenses.getText().toString(),Float.valueOf(cost.getText().toString()),0,calendarr.getTime(),"Fix");
                add(d);
                //alert
                // Create the object of AlertDialog Builder class
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                // Set the message show for the Alert time
                builder.setMessage("Your Expenses is saved .. We did not change your Balance because this is a fixed Expenses");

                // Set Alert Title
                builder.setTitle("$$");

                // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
                builder.setCancelable(false);

                // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
                builder.setPositiveButton("ok", (DialogInterface.OnClickListener) (dialog, which) -> {
                    // Intents are objects of the android.content.Intent type. Your code can send them to the Android system defining
                    // the components you are targeting. Intent to start an activity called SecondActivity with the following code.
                    Intent intent = new Intent(depense.this, MainActivity.class);
                    // start the activity connect to the specified class
                    startActivity(intent);
                });

                // Create the Alert dialog
                AlertDialog alertDialog = builder.create();
                // Show the Alert Dialog box
                alertDialog.show();
                          }


            balance.setText(mPreferences.getString(balanceDB,""));






        });

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
            variable.setVisibility(View.GONE);
            chargeVariable.setVisibility(View.VISIBLE);
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
            whenDefault.setVisibility(View.GONE);
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
            when.setText(String.valueOf(calendarr.getTime()));

            save.setVisibility(View.VISIBLE);
            int yearr = datePicker.getYear();
            int monthh = datePicker.getMonth(); // 0 - 11
            int dayy = datePicker.getDayOfMonth();

            calendarr.set(yearr, monthh, dayy);
        });


    }

}