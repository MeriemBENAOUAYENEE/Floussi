package com.example.floussi;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DepenseDAO {

    @Insert
    public void insertDepense(DepenseClass d);

    @Update
    public void updateDepense(DepenseClass d);

    @Query("SELECT * FROM DepensesTable")
    public List<DepenseClass> getDepense();

    @Query("DELETE FROM DepensesTable")
    public void DeleteAll();

    @Delete
    public void Delete(DepenseClass d);


}
