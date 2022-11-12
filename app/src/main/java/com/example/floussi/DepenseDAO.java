package com.example.floussi;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DepenseDAO {

    @Insert
    public void insertDepense(DepenseClass d);

    @Query("SELECT * FROM depenseTable")
    public List<DepenseClass> getDepense();
}
