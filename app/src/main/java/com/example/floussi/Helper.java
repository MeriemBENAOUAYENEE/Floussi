package com.example.floussi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Helper extends SQLiteOpenHelper {
    public Helper(@Nullable Context context) {

        super(context, "floussi", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

      db.execSQL("CREATE TABLE revenue(_id INTEGER PRIMARY KEY,nom TEXT,prix REAL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS revenue");
        onCreate(db);

    }

    public void insertRevenue(Revenue r){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put("nom",r.getNom() );
        cv.put("prix",r.getPrix() );

        db.insert("revenue",null,cv);
        db.close();

    }

public void updateRevenue(Revenue r){

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put("nom",r.getNom() );
        cv.put("prix",r.getPrix());

        db.update("revenue",cv,"_id=?",new String[]{String.valueOf(r.getId())});
        db.close();

    }
    public void deleteRevenue(int id){

        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("revenue","_id=?",new String[]{String.valueOf(id)});
        db.close();


    }

public Cursor getAllRevenue(){


        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM revenue",null);
        return c;

}

public Revenue getOneRevenue(int id){

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c =db.query("revenue",new String[]{"_id","nom","prix"},"_id=?",
                new String[]{String.valueOf(id)},null,null,null);
        c.moveToFirst();
        Revenue r=new Revenue(c.getInt(0),c.getString(1),c.getDouble(2));
        return r;


}


}
