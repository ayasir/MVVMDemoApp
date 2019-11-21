package com.example.mvvmdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {

    public interface OnDeleteClickListener{
        void OnDeleteClickListener(Note nNote);
    }
private Context mCtx;
private List<Note> mNotes;
private OnDeleteClickListener onDeleteClickListener;

    public NoteListAdapter(Context mCtx, OnDeleteClickListener listener){
        this.mCtx = mCtx;
        mNotes = new ArrayList<>();
        onDeleteClickListener = listener;
    }
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mCtx).inflate(R.layout.list_item,
                parent,false);

        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {

        if(mNotes != null){
            Note note = mNotes.get(position);
            holder.setData(note.getNote(),position);
            holder.setListeners();
        } else {
            holder.noteTextView.setText(R.string.no_note);
        }

    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public void setNotes(List<Note> notes){
        mNotes = notes;
        notifyDataSetChanged();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {


        private TextView noteTextView;
        private int nPosition;
        private ImageView imgDelete, imgUpdate;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            noteTextView = itemView.findViewById(R.id.txtNote);
            imgDelete = itemView.findViewById(R.id.ivRowDelete);
            imgUpdate = itemView.findViewById(R.id.ivRowEdit);
        }

        public void setData(String note, int position) {
            noteTextView.setText(note);
            nPosition = position;
        }

        public void setListeners() {

            imgUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(mCtx,UpdateNoteActivity.class);
                    intent.putExtra("note_id",mNotes.get(nPosition).getId());
                    ((Activity)mCtx).startActivityForResult(intent,MainActivity.UPDATE_NOTE_ACTIVITY_REQ_CODE);
                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
