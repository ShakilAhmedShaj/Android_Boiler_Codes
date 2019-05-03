package com.shajt3ch.room_04;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private Repository repo;
    private LiveData<List<Item>> allItems;

    public ViewModel(@NonNull Application application) {
        super(application);

        repo = new Repository(application);
        allItems = repo.getAllItems();
    }

    /**
     * Methods
     * @param item
     */
    public void insertItem(Item item) {
        repo.insertItem(item);
    }

    public void updateItem(Item item) {
        repo.update(item);
    }

    public void deleteItem(Item item) {
        repo.delete(item);
    }

    public void deleteAllItems() {
        repo.deleteAllItems();
    }

    public LiveData<List<Item>> getAllItems() {
        return allItems;
    }
}
