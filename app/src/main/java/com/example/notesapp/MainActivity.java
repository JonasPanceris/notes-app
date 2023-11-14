package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButton addNoteBut = findViewById(R.id.addNewNoteBtn);

        addNoteBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AddNoteActivity.class);
                startActivity(i);
            }
        });

//        Realm.init(getApplicationContext());
//        Realm realm = Realm.getDefaultInstance();
//
//        RealmResults<Notes> = notesList = realm.where(Notes.class).FindAllSorted("createdTime",Sort.DESCENDING);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        MyAdepter myAdepter = new MyAdepter(getApplicationContext(),notesList);
//        recyclerView.setAdapter(myAdepter);
//
//        notesList.addChanges(new RealmCangeListener<RealmResults<Notes>>(){
//
//            @Override
//            public void onChange(RealmResults<Notes> notes){
//                myAdepter.notifyDataSetChanged();
//            }
//
//
//        });

    }
}