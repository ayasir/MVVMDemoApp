package com.example.mvvmdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UpdateNoteActivity extends AppCompatActivity {

    public static final String NOTE_ID = "note_id";
    public static final String UPDATED_NOTE = "note_text";
    private EditText etNote;
    private Bundle bundle;
    private String noteId;
    private LiveData<Note> note;

    private UpdateNoteViewModel noteModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);

        etNote = findViewById(R.id.etNote);
        noteModel = ViewModelProviders.of(this).get(UpdateNoteViewModel.class);

        if(bundle != null){
            noteId = bundle.getString("note_id");
        }

        noteModel = ViewModelProviders.of(this).get(UpdateNoteViewModel.class);
        note = noteModel.getNote(noteId);
        note.observe(this, new Observer<Note>() {
            @Override
            public void onChanged(Note note) {
                etNote.setText(note.getNote());
            }
        });
    }

    public void updateNote(View view) {

        String updateNote = etNote.getText().toString();
        Intent resultIntent = new Intent();
        resultIntent.putExtra(NOTE_ID,noteId);
        resultIntent.putExtra(UPDATED_NOTE,updateNote);
        setResult(RESULT_OK,resultIntent);
        finish();
        
    }

    public void cancelUpdate(View view) {
        finish();
    }
}
