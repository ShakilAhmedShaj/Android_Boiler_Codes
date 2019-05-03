package com.shajt3ch.room_04;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;



@Entity(tableName = "item_table")
public class Item {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "Id")
    int itemId;

    @ColumnInfo(name = "Value")
    String itemValue;


    Item(@NonNull int itemId, String itemValue) {
        this.itemId = itemId;
        this.itemValue = itemValue;
    }

    @Ignore
    Item(@NonNull String itemValue) {
        this.itemValue = itemValue;
    }

    @NonNull
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }
}
