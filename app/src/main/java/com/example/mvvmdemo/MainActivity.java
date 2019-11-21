package com.example.mvvmdemo;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements NoteListAdapter.OnDeleteClickListener {

    public static final int NEW_NOTE_ACTIVITY_REQ_CODE = 1;
    public static final int UPDATE_NOTE_ACTIVITY_REQ_CODE = 1;
    private String TAG = this.getClass().getSimpleName();
    private TextView mRandomTextView;
    private Button mFetchButton;
    private NoteViewModel mNoteViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final NoteListAdapter noteListAdapter = new NoteListAdapter(this, (NoteListAdapter.OnDeleteClickListener) this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(noteListAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();

                Intent intent = new Intent(MainActivity.this,NewNoteActivity.class);
                startActivityForResult(intent,NEW_NOTE_ACTIVITY_REQ_CODE);
            }
        });


//        mRandomTextView = findViewById(R.id.random_text_view);
//        mFetchButton = findViewById(R.id.button_random);
      //  MainActivityViewModel myData = new MainActivityViewModel();
      //  final MainActivityViewModel myData = ViewModelProviders.of(this).get(MainActivityViewModel.class);
     //   LiveData<String> mRandomNumber = myData.getmRandomNumber();
      //  mRandomNumber.observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//                mRandomTextView.setText(s);
//            }
//        });

        mNoteViewModel.getnAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {

                noteListAdapter.setNotes(notes);
            }
        });

//        mFetchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                myData.createRandomNumber();
//            }
//        });

        Log.i(TAG, "onCreate: Random Number generated and set");

        mNoteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode == NEW_NOTE_ACTIVITY_REQ_CODE && resultCode == RESULT_OK ){

            final String note_id = UUID.randomUUID().toString();
            Note note = new Note(note_id,data.getStringExtra(NewNoteActivity.NOTE_ADDED));
            mNoteViewModel.insert(note);

        } else if(requestCode == UPDATE_NOTE_ACTIVITY_REQ_CODE && resultCode == RESULT_OK){

            // code to update the note
            Note note = new Note(
                    data.getStringExtra(UpdateNoteActivity.NOTE_ID),
                    data.getStringExtra(UpdateNoteActivity.UPDATED_NOTE));

            mNoteViewModel.update(note);

        } else {

        }
    }

    @Override
    public void OnDeleteClickListener(Note nNote) {
        mNoteViewModel.delete(nNote);
    }
}
