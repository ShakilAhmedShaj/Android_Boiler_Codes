package com.shajt3ch.room_04;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


@Dao
public interface ItemDao {

    @Insert
    void insertItem(Item item);

    // Order by prorioty?
    @Query("SELECT * FROM item_table")
    LiveData<List<Item>> getAllItems();

    @Query("DELETE FROM item_table")
    void deleteAllItems();

    @Delete
    void deleteItem(Item item);

    @Update
    void updateItem(Item item);

}
