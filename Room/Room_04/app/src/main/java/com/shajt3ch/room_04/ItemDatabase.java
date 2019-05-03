package com.shajt3ch.room_04;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = Item.class, version = 6, exportSchema = false)
public abstract class ItemDatabase extends RoomDatabase {

    // singleton
    private static ItemDatabase instance;

    // Method for using Dao
    public abstract ItemDao itemDao();

    public static synchronized ItemDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ItemDatabase.class, "item_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }


    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ItemDao itemDao;

        private PopulateDbAsyncTask(ItemDatabase db) {
            itemDao = db.itemDao();
        }


        @Override
        protected Void doInBackground(Void... voids) {
            itemDao.insertItem(new Item(1, "This is a value"));
            itemDao.insertItem(new Item(2, "This is another value"));
            itemDao.insertItem(new Item(3, "This is the third value"));
            return null;
        }
    }
}
