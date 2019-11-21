package com.example.mvvmdemo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private String TAG = this.getClass().getSimpleName();
    private NoteDao noteDao;
    private NoteRoomDatabase noteRoomDatabase;
    private LiveData<List<Note>> nAllNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);

        noteRoomDatabase = NoteRoomDatabase.getDatabase(application);
        noteDao = noteRoomDatabase.noteDao();
        nAllNotes = noteDao.getAllNotes();
    }

    public void insert(Note note){
        new InsertAsyncTask(noteDao).execute(note);
    }

    public LiveData<List<Note>> getnAllNotes(){
        return nAllNotes;
    }

    public void update(Note note){
        new UpdateAsyncTask(noteDao).execute(note);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public void delete(Note note){
        new DeleteAsyncTask(noteDao).execute(note);
    }

    private class OperationAsyncTask extends AsyncTask<Note, Void, Void>{

         public NoteDao nNoteDao;

        public OperationAsyncTask(NoteDao noteDao) {

            nNoteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            return null;
        }
    }
    private class UpdateAsyncTask extends OperationAsyncTask {



        public UpdateAsyncTask(NoteDao noteDao) {

           super(noteDao);
        }


        @Override
        protected Void doInBackground(Note... notes) {
            nNoteDao.update(notes[0]);
            return null;
        }
    }


    public class InsertAsyncTask extends OperationAsyncTask {


        public InsertAsyncTask(NoteDao noteDao) {
            super(noteDao);
        }


        @Override
        protected Void doInBackground(Note... notes) {
            nNoteDao.insert(notes[0]);
            return null;
        }

    }


    private class DeleteAsyncTask extends OperationAsyncTask{



        public DeleteAsyncTask(NoteDao noteDao) {

           super(noteDao);
        }

        @Override
        protected Void doInBackground(Note... notes) {
            nNoteDao.delete(notes[0]);
            return null;
        }
    }
}
