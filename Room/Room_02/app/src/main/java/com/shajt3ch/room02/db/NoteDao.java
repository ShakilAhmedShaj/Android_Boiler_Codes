package com.shajt3ch.room02.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.shajt3ch.room02.model.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    public void insert(Note note);

    @Query("SELECT * FROM Notes")
    public LiveData<List<Note>> getAllNotes();
}
