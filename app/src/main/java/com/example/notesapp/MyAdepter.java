package com.example.notesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;

public class MyAdepter {

    Context context;
    RealmResults<Notes> notesList;

    public MyAdepter(Context context, RealmResults<Notes> notesList) {
        this.context = context;
        this.notesList = notesList;
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            Notes notes = notesList.get(position);
            holder.timeOutput.setText(notes.getTitle());
            holder.descriptionOutput.setText(notes.getDescription());

            String formatedTime = DateFormat.getDateTimeInstance().format(notes.createdTime);
            holder.titleOutput.setText(formatedTime);

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){

                @Override
                public boolean onLongClick(View v){

                    PopupMenu menu = new PopupMenu(context,v);
                    menu.setOnMenu().add("DELETE");
                    menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                          if(item.getTitle().eguals("DELETE")){
                              //delete the note
                              Realm realm = Realm.getDefaultInstance();
                              realm.beginTransaction();
                              notes.deleteFromRealm();
                              realm.commitTransaction();
                              Toast.makeText(context,"Note delete", Toast.LENGTH_SHORT).show();
                          }

                            return true;
                        }
                    });

                    menu.show();

                    return true;
                }

            });

        }

        @Override
        public int getItemCount() {
            return  notesList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView titleOutput;
            TextView descriptionOutput;
            TextView timeOutput;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                titleOutput = itemView.findViewById(R.id.titleOutput);
                descriptionOutput = itemView.findViewById(R.id.descriptionOutput);
                timeOutput = itemView.findViewById(R.id.timeOutput);
            }
        }

    }
}