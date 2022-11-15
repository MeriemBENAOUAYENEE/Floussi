package com.example.floussi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AddRevenue extends AppCompatActivity {

    EditText nom, prix;
    Button b;
    Helper h = new Helper(AddRevenue.this);
    Button r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        nom=findViewById(R.id.nom2);
        prix=findViewById(R.id.prix2);
        b = findViewById(R.id.save);
        r = findViewById(R.id.retour);

        b.setOnClickListener((view) -> {
            Revenue r = new Revenue(nom.getText().toString(), Double.parseDouble(prix.getText().toString()));

            h.insertRevenue(r);
            Intent i = new Intent(getApplicationContext(), ListeRevenue.class);
            startActivity(i);

        });
        r.setOnClickListener((view) -> {
            finish();
            Intent i = new Intent(getApplicationContext(), ListeRevenue.class);
            startActivity(i);
        });
    }
}



