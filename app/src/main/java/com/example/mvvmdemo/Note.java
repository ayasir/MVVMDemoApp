package com.example.mvvmdemo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {

    @PrimaryKey
    @NonNull
    private String id;

    @NonNull
    @ColumnInfo(name="note")
    private String mNote;

    public Note(String id, @NonNull String mNote) {
        this.id = id;
        this.mNote = mNote;
    }

    public String getId() {
        return id;
    }

    @NonNull
    public String getNote() {
        return mNote;
    }
}
