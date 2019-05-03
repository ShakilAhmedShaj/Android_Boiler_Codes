package com.shajt3ch.room01;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MyDatabaseDao {

    @Insert
    void insertUserData(User user);

    @Query("Select * from user")
    List<User> getData();


}
