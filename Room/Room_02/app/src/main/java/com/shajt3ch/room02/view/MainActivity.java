package com.shajt3ch.room02.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shajt3ch.room02.model.Note;
import com.shajt3ch.room02.viewmodel.NoteViewModel;
import com.shajt3ch.room02.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NoteViewModel noteViewModel;
    private EditText noteEt;
    private TextView txt;
    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        initViews();
        initListeners();
        getAllNotes();

    }

    private void initViews() {
        noteEt = findViewById(R.id.noteEt);
        saveBtn = findViewById(R.id.saveBtn);
        txt = findViewById(R.id.txt);
    }

    private void initListeners() {
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(noteEt.getText())) {

                    noteViewModel.insert(new Note(noteEt.getText().toString().trim()));
                    Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Enter note", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getAllNotes() {

        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {

                StringBuilder stringBuilder = new StringBuilder();
                for (Note note : notes) {
                    stringBuilder.append(note.noteTxt).append("\n");
                }

                txt.setText(stringBuilder);
            }
        });

    }
}
