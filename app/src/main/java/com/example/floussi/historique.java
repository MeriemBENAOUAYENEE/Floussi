package com.example.floussi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
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
    EditText expensesU,costU,whenU;
    TableLayout tableLayout;
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
    TextView dateText;

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
            textView.setText("Expenses");
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            textView.setTextColor(Color.parseColor("#84a9fb"));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
            textView.setPadding(20, 0, 60, 0);
            tableRow.addView(textView);

            textView = new TextView(getApplicationContext());
            textView.setText("Date");
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            textView.setTextColor(Color.parseColor("#84a9fb"));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
            textView.setPadding(80, 0, 40, 0);
            tableRow.addView(textView);

            textView = new TextView(getApplicationContext());
            textView.setText("Cost");
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            textView.setTextColor(Color.parseColor("#84a9fb"));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
            textView.setPadding(40, 0, 40, 0);
            tableRow.addView(textView);

            textView = new TextView(getApplicationContext());
            textView.setText("Type");
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            textView.setTextColor(Color.parseColor("#84a9fb"));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
            textView.setPadding(60, 0, 20, 0);
            tableRow.addView(textView);



            tableLayout.addView(tableRow);

        }



        for (int i = 0; i < diplayD().size(); i++) {
            tableRow = new TableRow(getApplicationContext());

            textView = new TextView(getApplicationContext());
            textView.setText(diplayD().get(i).getDepense());
            textView.setPadding(50, 50, 40, 20);
            tableRow.addView(textView);

            Date date = diplayD().get(i).getDateDepense();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = dateFormat.format(date);

            textView = new TextView(getApplicationContext());
            textView.setText(strDate);
            textView.setPadding(70, 50, 40, 20);
            tableRow.addView(textView);

            textView = new TextView(getApplicationContext());
            textView.setText(String.valueOf(diplayD().get(i).getPrix()));
            textView.setPadding(70, 50, 40, 20);
            tableRow.addView(textView);

            textView = new TextView(getApplicationContext());
            textView.setText(diplayD().get(i).getType());
            if (diplayD().get(i).getType().equals("Fix"))
            {
                textView.setTextColor(Color.parseColor("#f6cac1"));
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
            }
            else
            {
                textView.setTextColor(Color.parseColor("#b1a7eb"));
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
            }
            textView.setPadding(70, 50, 20, 20);
            tableRow.addView(textView);



            tableRow.setOnClickListener(v -> {

                tableLayout.setVisibility(View.GONE);
                expensesU.setVisibility(View.VISIBLE);
                whenU.setVisibility(View.VISIBLE);
                costU.setVisibility(View.VISIBLE);
                deletebutton.setVisibility(View.VISIBLE);
                update.setVisibility(View.VISIBLE);
                changedate.setVisibility(View.VISIBLE);
                idtoworkwith = tableLayout.indexOfChild(v)-1;
                type = diplayD().get(idtoworkwith).getType();


                expensesU.setText(diplayD().get(idtoworkwith).getDepense());
                costU.setText(String.valueOf(diplayD().get(idtoworkwith).getPrix()));
                whenU.setText(String.valueOf(diplayD().get(idtoworkwith).getDateDepense()));
                tableLayout.removeAllViews();

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
        expensesU = findViewById(R.id.expensesU);
        deletebutton = findViewById(R.id.DeleteButton);
        update = (Button) findViewById(R.id.update);



        expensesU.setVisibility(View.GONE);
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



            //alert
            // Create the object of AlertDialog Builder class
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            // Set the message show for the Alert time
            builder.setMessage("Do you really want to delete ?");

            // Set Alert Title
            builder.setTitle("Delete !");

            // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
            builder.setCancelable(false);

            // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
            builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                float f = Float.valueOf(mPreferences.getString(balanceDB,"6")) + Float.valueOf(diplayD().get(idtoworkwith).getPrix());
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putString(balanceDB,String.valueOf(f));
                editor.commit();

                deleteD(diplayD().get(idtoworkwith));
                Toast.makeText(this,"Deleted successfully",Toast.LENGTH_LONG).show();

                tableLayout.setVisibility(View.VISIBLE);
                expensesU.setVisibility(View.GONE);
                whenU.setVisibility(View.GONE);
                costU.setVisibility(View.GONE);
                deletebutton.setVisibility(View.GONE);
                update.setVisibility(View.GONE);

                //   Toast.makeText(this,"ChargeFix:" + mPreferences.getString(balanceDB,"") + " Saved" ,Toast.LENGTH_LONG).show();
                dataLoad();
            });

            // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
            builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {

            });

            // Create the Alert dialog
            AlertDialog alertDialog = builder.create();
            // Show the Alert Dialog box
            alertDialog.show();

        });

        update.setOnClickListener(v -> {


            if (type.equals("Variable"))
            {
                float f = Float.valueOf(mPreferences.getString(balanceDB,"6")) - Float.valueOf(costU.getText().toString()) + diplayD().get(idtoworkwith).getPrix();
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
                    dataLoad();

                    tableLayout.setVisibility(View.VISIBLE);
                    expensesU.setVisibility(View.GONE);
                    whenU.setVisibility(View.GONE);
                    costU.setVisibility(View.GONE);
                    deletebutton.setVisibility(View.GONE);
                    update.setVisibility(View.GONE);
                });

                // Create the Alert dialog
                AlertDialog alertDialog = builder.create();
                // Show the Alert Dialog box
                alertDialog.show();          }
            else
            {
                //alert
                // Create the object of AlertDialog Builder class
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                // Set the message show for the Alert time
                builder.setMessage("Your Expenses is modified Successfully .. We did not change your Balance because this is a fixed Expenses");

                // Set Alert Title
                builder.setTitle("$$");

                // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
                builder.setCancelable(false);

                // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
                builder.setPositiveButton("ok", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dataLoad();

                    tableLayout.setVisibility(View.VISIBLE);
                    expensesU.setVisibility(View.GONE);
                    whenU.setVisibility(View.GONE);
                    costU.setVisibility(View.GONE);
                    deletebutton.setVisibility(View.GONE);
                    update.setVisibility(View.GONE);
                });

                // Create the Alert dialog
                AlertDialog alertDialog = builder.create();
                // Show the Alert Dialog box
                alertDialog.show();
            }


            balance.setText(mPreferences.getString(balanceDB,""));

            DepenseClass d = diplayD().get(idtoworkwith);
            d.setDateDepense(calendarr.getTime());
            d.setPrix(Float.valueOf(costU.getText().toString()));
            d.setDepense(expensesU.getText().toString());


            updateD(d);




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
            expensesU.setVisibility(View.GONE);
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
            expensesU.setVisibility(View.VISIBLE);
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

