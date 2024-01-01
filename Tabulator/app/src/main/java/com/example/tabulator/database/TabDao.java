package com.example.tabulator.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tabulator.models.Tab;

import java.util.List;

@Dao
public interface TabDao {

    @Insert
    public long insert(Tab newTab);

    @Query("SELECT * FROM tab")
    public List<Tab> getAll();

    @Query("SELECT * FROM tab WHERE id = :id LIMIT 1")
    public Tab findById(long id);

    @Update
    public void update(Tab newTab);

    @Delete
    public void delete(Tab newTab);

}
