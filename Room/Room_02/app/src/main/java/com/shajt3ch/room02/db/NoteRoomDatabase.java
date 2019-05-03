package com.shajt3ch.room02.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.shajt3ch.room02.model.Note;

@Database(entities = Note.class, version = 1)
public abstract class NoteRoomDatabase extends RoomDatabase {

    public abstract NoteDao noteDao();

    private static volatile NoteRoomDatabase noteRoomDatabase;

    public static NoteRoomDatabase getDatabase(Context context) {

        if (noteRoomDatabase == null) {
            synchronized (NoteRoomDatabase.class) {
                if (noteRoomDatabase == null) {

                    noteRoomDatabase = Room.databaseBuilder(context.getApplicationContext(), NoteRoomDatabase.class, "note_database").build();
                }
            }
        }

        return noteRoomDatabase;
    }
}
