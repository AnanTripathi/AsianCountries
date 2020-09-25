package com.project.asiancountries.room;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.project.asiancountries.util.Country;

@Database(entities = Country.class,version = 1,exportSchema = false)
public abstract class CountriesDatabase extends RoomDatabase {
    public abstract DAO dao();

}
