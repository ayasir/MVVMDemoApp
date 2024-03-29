package com.example.mvvmdemo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    public void insert(Note note);

    @Query("SELECT * FROM notes")
    public LiveData <List<Note>> getAllNotes();

    @Query("SELECT * FROM notes WHERE id =:noteId")
    public LiveData<Note> getNote(String noteId);

    @Update
    public void update(Note note);

    @Delete
    public int delete(Note note);
}
