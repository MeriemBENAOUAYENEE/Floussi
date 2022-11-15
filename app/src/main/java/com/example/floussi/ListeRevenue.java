package com.example.floussi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ListeRevenue extends AppCompatActivity {
    ListView ls;
    Helper h=new Helper(ListeRevenue.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_revenue);
        ls=findViewById(R.id.lst);

        Cursor c=h.getAllRevenue();

        SimpleCursorAdapter adapter=new SimpleCursorAdapter(ListeRevenue.this,R.layout.item,c,
                new String[]{c.getColumnName(0),c.getColumnName(1),c.getColumnName(2)},
                new int[]{R.id.id,R.id.nom1,R.id.prix1},1);
        ls.setAdapter(adapter);

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                TextView t=view.findViewById(R.id.id);
                Intent x=new Intent(ListeRevenue.this,DetailRevenue.class);
                x.putExtra("id",t.getText().toString());
                startActivity(x);



            }
        });
        Button bs;

        bs=findViewById(R.id.is);

        bs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListeRevenue.this,AddRevenue.class);
                startActivity(intent);
            }
        });


    }






    }
