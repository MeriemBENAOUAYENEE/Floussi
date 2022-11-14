package com.example.floussi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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
    Button  update ,deletebutton,changedate,getDatebutton;
    Calendar calendarr = Calendar.getInstance();
    TextView balance;
    int idtoworkwith;
    private DatePicker datePicker;
    String type;

    private SharedPreferences mPreferences;
    public static final String balanceDB ="90000";
    public static final String shared_STRING ="mainShared";

    private void datePickerChange(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Log.d("Date", "Year=" + year + " Month=" + (month + 1) + " day=" + dayOfMonth);
        dateText.setText(dayOfMonth +"-" + (month + 1) + "-" + year);
    }
    // Add button Move previous activity
    Button previous_button;
    int i;
    TextView expenses,dateText;

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

        changedate.setVisibility(View.GONE);
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
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
                changedate.setVisibility(View.VISIBLE);
                idtoworkwith = tableLayout.indexOfChild(v)-1;
                type = diplayD().get(idtoworkwith).getType();


                expenses.setText(diplayD().get(idtoworkwith).getDepense());
                costU.setText(String.valueOf(diplayD().get(idtoworkwith).getPrix()));
                whenU.setText(String.valueOf(diplayD().get(idtoworkwith).getDateDepense()));
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

        datePicker = (DatePicker) this.findViewById(R.id.datePickerU);
        dateText= findViewById(R.id.TxtGetDateU);
        changedate = findViewById(R.id.changedateU);
        getDatebutton = findViewById(R.id.GetDateU);
        datePicker.setVisibility(View.GONE);
        dateText.setVisibility(View.GONE);
        getDatebutton.setVisibility(View.GONE);




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
            dataLoad();

        });

        update.setOnClickListener(v -> {

            float f = Float.valueOf(mPreferences.getString(balanceDB,"6")) - Float.valueOf(costU.getText().toString()) + diplayD().get(idtoworkwith).getPrix();
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putString(balanceDB,String.valueOf(f));
            editor.commit();
            balance.setText(mPreferences.getString(balanceDB,""));

            DepenseClass d = diplayD().get(idtoworkwith);
            d.setDateDepense(calendarr.getTime());
            d.setPrix(Float.valueOf(costU.getText().toString()));
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



            Toast.makeText(this,"Updated successfully" + f,Toast.LENGTH_LONG).show();


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




            changedate.setVisibility(View.GONE);

            tableLayout.setVisibility(View.GONE);
            expenses.setVisibility(View.GONE);
            whenU.setVisibility(View.GONE);
            costU.setVisibility(View.GONE);
            deletebutton.setVisibility(View.GONE);
            update.setVisibility(View.GONE);
            datePicker.setVisibility(View.VISIBLE);
            dateText.setVisibility(View.VISIBLE);
            getDatebutton.setVisibility(View.VISIBLE);

        });

        getDatebutton.setOnClickListener(v -> {
            datePicker.setVisibility(View.GONE);
            dateText.setVisibility(View.GONE);
            getDatebutton.setVisibility(View.GONE);



            tableLayout.setVisibility(View.GONE);
            expenses.setVisibility(View.VISIBLE);
            changedate.setVisibility(View.VISIBLE);
            costU.setVisibility(View.VISIBLE);
            deletebutton.setVisibility(View.VISIBLE);
            update.setVisibility(View.VISIBLE);
            whenU.setVisibility(View.VISIBLE);
            int yearr = datePicker.getYear();
            int monthh = datePicker.getMonth(); // 0 - 11
            int dayy = datePicker.getDayOfMonth();

            calendarr.set(yearr, monthh, dayy);

            whenU.setText(String.valueOf(calendarr.getTime()));
        });


    }
}

