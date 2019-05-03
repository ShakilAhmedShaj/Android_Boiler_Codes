package com.shajt3ch.room02.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.shajt3ch.room02.model.Note;
import com.shajt3ch.room02.db.NoteDao;
import com.shajt3ch.room02.db.NoteRoomDatabase;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteDao noteDao;
    private NoteRoomDatabase noteRoomDatabase;


    public NoteViewModel(@NonNull Application application) {
        super(application);

        noteRoomDatabase = NoteRoomDatabase.getDatabase(application);
        noteDao = noteRoomDatabase.noteDao();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        // View Model Destroyed
    }


    public void insert(Note note) {
        new InsertAsyncTask(noteDao).execute(note);
    }

    public LiveData<List<Note>> getAllNotes() {

        return noteDao.getAllNotes();
    }
}


class InsertAsyncTask extends AsyncTask<Note, Void, Void> {

    NoteDao noteDao;

    public InsertAsyncTask(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    protected Void doInBackground(Note... notes) {

        noteDao.insert(notes[0]);
        return null;
    }
}
