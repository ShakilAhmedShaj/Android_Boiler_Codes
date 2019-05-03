package com.shajt3ch.room_03;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MyDao {
    @Insert
    public void addUser(User user);

    @Query("select * from User")
    public List<User> getUsers();

    @Delete
    public void deletUsers(User user);

    @Update
    public void updateUsers(User user);

    @Query("SELECT * FROM User WHERE id LIKE :id")
    public abstract List<User> findUsersByName(int id);
}
