package com.example.floussi;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {DepenseClass.class}, version = 1, exportSchema = false)

public abstract class AppDataBase extends RoomDatabase {

    //var
    private static AppDataBase instance;
    private static String DATABASE_NAME = "DataBase";
    public abstract DepenseDAO depenseDAO();




    public static AppDataBase getInstance(Context context){
       if (instance == null){
           // Using this fallback is almost certainly a bad idea
      /*     instance = Room.databaseBuilder(context, AppDataBase.class, DATABASE_NAME)
                   .fallbackToDestructiveMigration()
                   .build();*/
             instance = Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class, DATABASE_NAME).allowMainThreadQueries().build();
        }
        return instance;
    }
}
