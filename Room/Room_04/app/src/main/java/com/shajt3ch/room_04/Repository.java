package com.shajt3ch.room_04;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class Repository {
    private ItemDao itemDao;
    private LiveData<List<Item>> allItems;

    public Repository(Application app) {
        ItemDatabase db = ItemDatabase.getInstance(app);
        itemDao = db.itemDao();
        allItems = itemDao.getAllItems();
    }

    /**
     * Methods
     * @param item
     */
    public void insertItem(Item item) {
        new InsertItemAsyncTask(itemDao).execute(item);
    }

    public void update(Item item) {
        new UpdateItemAsyncTask(itemDao).execute(item);
    }

    public void delete(Item item) {
        new DeleteAllItemAsyncTask(itemDao).execute(item);
    }

    public void deleteAllItems() {
        new DeleteAllItemAsyncTask(itemDao).execute();
    }

    public LiveData<List<Item>> getAllItems() {
        return allItems;
    }

    /**
     *  Async Methods
      */
    // Insert one item
    private static class InsertItemAsyncTask extends AsyncTask<Item, Void, Void> {
        private ItemDao itemDao;

        InsertItemAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected Void doInBackground(Item... items) {
            itemDao.insertItem(items[0]);
            return null;
        }
    }

    // Update one item
    private static class UpdateItemAsyncTask extends AsyncTask<Item, Void, Void> {
        private ItemDao itemDao;

        private UpdateItemAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected Void doInBackground(Item... items) {
            itemDao.updateItem(items[0]);

            return null;
        }
    }

    // Delete one item
    private static class DeleteItemAsyncTask extends AsyncTask<Item, Void, Void> {
        private ItemDao itemDao;

        private DeleteItemAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected Void doInBackground(Item... items) {
            itemDao.deleteItem(items[0]);

            return null;
        }
    }

    // Delete all items
    private static class DeleteAllItemAsyncTask extends AsyncTask<Item, Void, Void> {
        private ItemDao itemDao;

        private DeleteAllItemAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected Void doInBackground(Item... items) {
            itemDao.deleteAllItems();

            return null;
        }
    }
}
