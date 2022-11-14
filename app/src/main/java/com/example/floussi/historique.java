package com.example.floussi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class historique extends AppCompatActivity {

    // define the global variable
    TextView cost,when;
    EditText expensesU,costU,whenU;
    TableLayout table,tableLayout;
    Button  update ,deletebutton;
    TextView balance;
    int idtoworkwith;
    String type;

    private SharedPreferences mPreferences;
    public static final String balanceDB ="90000";
    public static final String shared_STRING ="mainShared";
    // Add button Move previous activity
    Button previous_button;
    int i;
    TextView expenses;

    private AppDataBase dataBase;

    public void add (DepenseClass d){
        dataBase.depenseDAO().insertDepense(d);
    }
    public void updateD (DepenseClass d){
        dataBase.depenseDAO().updateDepense(d);
    }

    public List<DepenseClass> diplayD (){
        return dataBase.depenseDAO().getDepense();
    }

    public void deleteD (DepenseClass d){
        dataBase.depenseDAO().Delete(d);
    }

    public void dataLoad (){


        tableLayout = findViewById(R.id.tablelay);
        TableRow tableRow;
        TextView textView;

        for (int i = 0; i < 1; i++) {
            tableRow = new TableRow(getApplicationContext());
            textView = new TextView(getApplicationContext());
            textView.setText("Depense");
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            textView.setPadding(50, 50, 70, 20);
            tableRow.addView(textView);

            textView = new TextView(getApplicationContext());
            textView.setText("Date");
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            textView.setPadding(70, 50, 70, 20);
            tableRow.addView(textView);

            textView = new TextView(getApplicationContext());
            textView.setText("Prix");
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            textView.setPadding(70, 50, 70, 20);
            tableRow.addView(textView);

            textView = new TextView(getApplicationContext());
            textView.setText("Charge");
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            textView.setPadding(70, 50, 20, 20);
            tableRow.addView(textView);



            tableLayout.addView(tableRow);

        }



        for (int i = 0; i < diplayD().size(); i++) {
            tableRow = new TableRow(getApplicationContext());

            textView = new TextView(getApplicationContext());
            textView.setText(diplayD().get(i).getDepense());
            textView.setPadding(50, 50, 70, 20);
            tableRow.addView(textView);

            Date date = diplayD().get(i).getDateDepense();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            String strDate = dateFormat.format(date);

            textView = new TextView(getApplicationContext());
            textView.setText(strDate);
            textView.setPadding(70, 50, 70, 20);
            tableRow.addView(textView);

            textView = new TextView(getApplicationContext());
            textView.setText(String.valueOf(diplayD().get(i).getPrix()));
            textView.setPadding(70, 50, 70, 20);
            tableRow.addView(textView);

            textView = new TextView(getApplicationContext());
            textView.setText(diplayD().get(i).getType());
            textView.setPadding(70, 50, 20, 20);
            tableRow.addView(textView);



            tableRow.setOnClickListener(v -> {

                tableLayout.setVisibility(View.GONE);
                expenses.setVisibility(View.VISIBLE);
                whenU.setVisibility(View.VISIBLE);
                costU.setVisibility(View.VISIBLE);
                deletebutton.setVisibility(View.VISIBLE);
                update.setVisibility(View.VISIBLE);
                idtoworkwith = tableLayout.indexOfChild(v)-1;
                type = diplayD().get(idtoworkwith).getType();


                expenses.setText(diplayD().get(idtoworkwith).getDepense());
                tableLayout.removeAllViews();

                //    Toast.makeText(this,"jawek behi " +  tableLayout.indexOfChild(v) ,Toast.LENGTH_LONG).show();

            });

            tableLayout.addView(tableRow);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);


        dataBase = AppDataBase.getInstance(this);
        balance =  findViewById(R.id.balanceH);
        costU = findViewById(R.id.costU);
        whenU = findViewById(R.id.whenU);
        expenses = findViewById(R.id.expensesU);
        deletebutton = findViewById(R.id.DeleteButton);
        update = (Button) findViewById(R.id.update);

        expenses.setVisibility(View.GONE);
        whenU.setVisibility(View.GONE);
        costU.setVisibility(View.GONE);
        deletebutton.setVisibility(View.GONE);
        update.setVisibility(View.GONE);




      //  balance =  findViewById(R.id.balance);
        mPreferences = getSharedPreferences(shared_STRING, MODE_PRIVATE);
        balance.setText(mPreferences.getString(balanceDB,"6"));



        // In question1 get the TextView use by findViewById()
        // In TextView set question Answer for message
    //    expenses.setText(diplayD().toString());

     //   TableLayout tableLayout = new TableLayout(getApplicationContext());
        dataLoad();

        // Add_button add clicklistener
        deletebutton.setOnClickListener(v -> {

           // deleteD(diplayD().get(idtoworkwith));
            Toast.makeText(this,"Deleted successfully",Toast.LENGTH_LONG).show();

            tableLayout.setVisibility(View.VISIBLE);
            expenses.setVisibility(View.GONE);
            whenU.setVisibility(View.GONE);
            costU.setVisibility(View.GONE);
            deletebutton.setVisibility(View.GONE);
            update.setVisibility(View.GONE);

/*
            float f = Float.valueOf(mPreferences.getString(balanceDB,"6")) - Float.valueOf(cost.getText().toString());
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putString(balanceDB,String.valueOf(f));
            editor.commit();*/

            //   Toast.makeText(this,"ChargeFix:" + mPreferences.getString(balanceDB,"") + " Saved" ,Toast.LENGTH_LONG).show();


        });

        update.setOnClickListener(v -> {

            DepenseClass d = diplayD().get(idtoworkwith);
            d.setDepense(expenses.getText().toString());
            Toast.makeText(this, d.getType() ,Toast.LENGTH_LONG).show();
            updateD(d);
            dataLoad();

     /*       if (type == "chargeVariable"){
                DepenseClass d = diplayD().get(idtoworkwith);
                d.setDepense(expenses.getText().toString());
                //new DepenseClass(expenses.getText().toString(),Float.valueOf(cost.getText().toString()),0,Calendar.getInstance().getTime(),"Variable");
                updateD(d);
                //    balanceint = balanceint - Float.valueOf(cost.getText().toString());

                Toast.makeText(this,"ChargeVariable:" + expenses.getText().toString() + " Saved" ,Toast.LENGTH_LONG).show();
            }

        /*    else {
                DepenseClass d = new DepenseClass(expenses.getText().toString(),Float.valueOf(cost.getText().toString()),0,Calendar.getInstance().getTime(),"Fix");
                updateD(d);
                Toast.makeText(this,"ChargeFix:" + expenses.getText().toString() + " Saved" ,Toast.LENGTH_LONG).show();
                // balanceint = balanceint - Float.valueOf(cost.getText().toString());
            }*/

        /*    float f = Float.valueOf(mPreferences.getString(balanceDB,"6")) - Float.valueOf(cost.getText().toString());
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putString(balanceDB,String.valueOf(f));
            editor.commit();*/

            // deleteD(diplayD().get(idtoworkwith));
            Toast.makeText(this,"Updated successfully",Toast.LENGTH_LONG).show();

            tableLayout.setVisibility(View.VISIBLE);
            expenses.setVisibility(View.GONE);
            whenU.setVisibility(View.GONE);
            costU.setVisibility(View.GONE);
            deletebutton.setVisibility(View.GONE);
            update.setVisibility(View.GONE);

/*
            float f = Float.valueOf(mPreferences.getString(balanceDB,"6")) - Float.valueOf(cost.getText().toString());
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putString(balanceDB,String.valueOf(f));
            editor.commit();*/

            //   Toast.makeText(this,"ChargeFix:" + mPreferences.getString(balanceDB,"") + " Saved" ,Toast.LENGTH_LONG).show();


        });

    }
}

