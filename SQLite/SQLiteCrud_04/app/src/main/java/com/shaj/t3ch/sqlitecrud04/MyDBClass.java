package com.shaj.t3ch.sqlitecrud04;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBClass extends SQLiteOpenHelper {

    private static final String DB_NAME = "user_list_database.db";
    private static final int VERSION_CODE = 1;

    private static final String TABLE_NAME = "user";
    private static final String COL_1 = "id";
    private static final String COL_2 = "name";
    private static final String COL_3 = "gender";
    private static final String COL_4 = "address";

    public MyDBClass(Context context) {
        super(context, DB_NAME, null, VERSION_CODE);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE  " + TABLE_NAME + " (  " + COL_1 + " INTEGER PRIMARY KEY ,  " + COL_2 + " TEXT , " + COL_3 + " TEXT , " + COL_4 + " TEXT ) ";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);

        onCreate(db);   // Create table again
    }

    // Code for insert data into database
    public long insertUser(String id, String name, String gender, String address) {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_1, id);
        values.put(COL_2, name);
        values.put(COL_3, gender);
        values.put(COL_4, address);
        long result = database.insert(TABLE_NAME, null, values);

        return result;
    }

    // Code for view all data
    public Cursor getUsers() {
        SQLiteDatabase database = getReadableDatabase();

        String sql = "SELECT * FROM " + TABLE_NAME;
        Cursor users = database.rawQuery(sql, null);
        return users;
    }

    // Code for delete one user
    public int deleteUser(String id) {

        SQLiteDatabase database = getWritableDatabase();
        int result = database.delete(TABLE_NAME, COL_1 + " = ? ", new String[]{id});
        return result;
    }

    // Code for update user
    public int updateUser(String id, String name, String gender, String address) {

        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2, name);
        values.put(COL_3, gender);
        values.put(COL_4, address);

        int result = database.update(TABLE_NAME, values, COL_1 + " = ? ", new String[]{id});
        return result;
    }
}
