package com.example.floussi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.List;

public class historique extends AppCompatActivity {

    // define the global variable
    TextView cost,when;
    TableLayout table;
    // Add button Move previous activity
    Button previous_button;
    int i;
    TextView expenses;

    private AppDataBase dataBase;

    public void add (DepenseClass d){
        dataBase.depenseDAO().insertDepense(d);
    }

    public List<DepenseClass> diplayD (){
        return dataBase.depenseDAO().getDepense();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);


        dataBase = AppDataBase.getInstance(this);

        table = findViewById(R.id.Table);



        // by ID we can use each component which id is assign in xml
        // file use findViewById() to get the Button and textview.
        previous_button = (Button) findViewById(R.id.third_activity_previous_button);
     //   expenses = findViewById(R.id.expensesHistory);
        cost = findViewById(R.id.CostHistory);
        when = findViewById(R.id.DateHistory);


        // In question1 get the TextView use by findViewById()
        // In TextView set question Answer for message
    //    expenses.setText(diplayD().toString());

        TableLayout tableLayout = new TableLayout(getApplicationContext());
        TableRow tableRow;
        TextView textView;

        for (int i = 0; i < 1; i++) {
            tableRow = new TableRow(getApplicationContext());

            textView = new TextView(getApplicationContext());
            textView.setText("Expenses");
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            textView.setPadding(100, 50, 100, 20);
            tableRow.addView(textView);

            textView = new TextView(getApplicationContext());
            textView.setText("Date");
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            textView.setPadding(100, 50, 100, 20);
            tableRow.addView(textView);

            textView = new TextView(getApplicationContext());
            textView.setText("Cost");
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            textView.setPadding(100, 50, 100, 20);
            tableRow.addView(textView);



            tableLayout.addView(tableRow);
        }

        for (int i = 0; i < diplayD().size(); i++) {
            tableRow = new TableRow(getApplicationContext());

            textView = new TextView(getApplicationContext());
            textView.setText(diplayD().get(i).getDepense());
            textView.setPadding(100, 50, 100, 20);
            tableRow.addView(textView);

            textView = new TextView(getApplicationContext());
            textView.setText(diplayD().get(i).getDateDepense());
            textView.setPadding(100, 50, 100, 20);
            tableRow.addView(textView);

            textView = new TextView(getApplicationContext());
            textView.setText(diplayD().get(i).getDateDepense());
            textView.setPadding(100, 50, 100, 20);
            tableRow.addView(textView);



            tableLayout.addView(tableRow);
        }
        setContentView(tableLayout);

        // Add_button add clicklistener
        previous_button.setOnClickListener(v -> {
            // Intents are objects of the android.content.Intent type. Your code can send them to the Android system defining
            // the components you are targeting. Intent to start an activity called SecondActivity with the following code:
            Intent intent = new Intent(historique.this, depense.class);
            // start the activity connect to the specified class
            startActivity(intent);
        });
    }
}

