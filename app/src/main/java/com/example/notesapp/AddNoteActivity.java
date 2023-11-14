package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        EditText titleInput = findViewById(R.id.titleInput);
        EditText descriptionInput = findViewById(R.id.descriptionInput);
        MainActivity saveBtn = findViewById(R.id.saveBtn);

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        saveBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String title = titleInput.getText().toString();
                String description = descriptionInput.getText().toString();
                long createdTime = System.currentTimeMillis();

                realm.beginTransaction();
                Notes note = realm.createObject(Notes.class);
                note.setTitle(title);
                note.setDescription(description);
                note.setCreatedTime(createdTime);
                realm.commitTransaction();
                Toast.makeText(getApplicationContext(),"Note Save",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}