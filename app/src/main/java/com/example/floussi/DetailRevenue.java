package com.example.floussi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailRevenue extends AppCompatActivity {

    EditText nom2,prix2;
    Button mod,sup;
    String id;
    Helper h=new Helper(DetailRevenue.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        nom2=findViewById(R.id.nom5);
        prix2=findViewById(R.id.prix5);
        mod=findViewById(R.id.mod1);
        sup=findViewById(R.id.sup1);


        id=getIntent().getStringExtra("id");
        Revenue r=h.getOneRevenue(Integer.parseInt(id));
        nom2.setText(r.getNom());
        prix2.setText(String.valueOf(r.getPrix()));
        mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Revenue re=new Revenue(Integer.parseInt(id),nom2.getText().toString(),
                        Double.parseDouble(prix2.getText().toString()));
                h.updateRevenue(re);
                Intent i=new Intent(DetailRevenue.this,ListeRevenue.class);
                startActivity(i);


            }
        });
        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                h.deleteRevenue(Integer.parseInt(id));
                Intent i=new Intent(DetailRevenue.this,ListeRevenue.class);
                startActivity(i);

            }
        });




    }
}