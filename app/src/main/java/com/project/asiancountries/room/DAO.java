package com.project.asiancountries.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.project.asiancountries.util.Country;

import java.util.List;
import java.util.zip.CheckedOutputStream;

@Dao
public interface DAO {
    @Insert
    public void countryInsert(Country country);
    @Query("Select * from Country")
    public List<Country> getCountries();
    @Query("Delete FROM Country")
    public void deleteCompleteTable();
}
