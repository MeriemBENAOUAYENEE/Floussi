package com.example.floussi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class updateDelete extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    // define the global variable
    EditText expenses,cost,when;
    // Add button Move to next Activity and previous Activity
    Button chargeVariable, chargeFix, update ,deletebutton,getCostMonth;
    private AppDataBase dataBase;
    Spinner spinner;
    Float costMonth;
    int intmonth;

    TextView balance,costMonthText;


    private SharedPreferences mPreferences;
    public static final String balanceDB ="90000";
    public static final String shared_STRING ="mainShared";

    public void add (DepenseClass d){
        dataBase.depenseDAO().insertDepense(d);
    }



    public List<DepenseClass> diplay (){
        return dataBase.depenseDAO().getDepense();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        intmonth=position;
        // Showing selected spinner item
    //    Toast.makeText(parent.getContext(), "Selected: " + position, Toast.LENGTH_LONG).show();



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        dataBase = AppDataBase.getInstance(this);
        mPreferences = getSharedPreferences(shared_STRING, MODE_PRIVATE);


        costMonthText = findViewById(R.id.CostMonth);
        spinner = (Spinner) findViewById(R.id.spinner);
        getCostMonth = findViewById(R.id.getCostMonth);
        balance =  findViewById(R.id.balanceU);


        balance.setText(mPreferences.getString(balanceDB,"6"));
        spinner.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        // Create a DateFormatSymbols instance
        DateFormatSymbols dfs = new DateFormatSymbols();

        // DateFormatSymbols instance has a method by name
        // getMonths() which returns back an array of
        // months name
        String[] categories = dfs.getMonths();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);



        getCostMonth.setOnClickListener(v -> {
            List<DepenseClass> listD = diplay();
            costMonth = (float) 0;
            for (int i = 0; i < diplay().size(); i++) {

                Calendar calendar = Calendar.getInstance();


                calendar.setTime(diplay().get(i).getDateDepense());

                if (calendar.get(Calendar.MONTH) == intmonth) {
                    costMonth = costMonth + diplay().get(i).getPrix();
                }
            }

            if (costMonth>100)
                costMonthText.setTextColor(Color.parseColor("#C47D88"));
            else
                costMonthText.setTextColor(Color.parseColor("#9ED0B9"));

            costMonthText.setText("this month you spent " + String.valueOf(costMonth));
        });


    }




    }


