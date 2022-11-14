package com.example.floussi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class updateDelete extends AppCompatActivity {

    // define the global variable
    EditText expenses,cost,when;
    // Add button Move to next Activity and previous Activity
    Button chargeVariable, chargeFix, update ,deletebutton;
    private AppDataBase dataBase;

    TextView balance;

    private SharedPreferences mPreferences;
    public static final String balanceDB ="90000";
    public static final String shared_STRING ="mainShared";

    public void add (DepenseClass d){
        dataBase.depenseDAO().insertDepense(d);
    }



    public List<DepenseClass> diplay (DepenseClass d){
        return dataBase.depenseDAO().getDepense();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        dataBase = AppDataBase.getInstance(this);




        // by ID we can use each component which id is assign in xml
        // file use findViewById() to get the both Button and textview
        expenses = findViewById(R.id.expensesU);
        deletebutton = findViewById(R.id.DeleteButton);
        update = (Button) findViewById(R.id.update);
        cost = findViewById(R.id.costU);
        when = findViewById(R.id.whenU);

        balance =  findViewById(R.id.balanceU);






        mPreferences = getSharedPreferences(shared_STRING, MODE_PRIVATE);
        //  balance.setText(mPreferences.getString(balanceDB,"6"));





        balance.setText(mPreferences.getString(balanceDB,"6"));


        // Add_button add clicklistener
        update.setOnClickListener(v -> {


        });




    }

}
