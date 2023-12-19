package com.example.notesapp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.notesapp.MyAdepter.MyAdapter;
import com.example.notesapp.Notes;
import com.example.notesapp.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

@RunWith(MockitoJUnitRunner.class)
public class MyAdapterTest {

    @Mock
    private Context mockContext;

    @Mock
    private RealmResults<Notes> mockNotesList;

    @Mock
    private MyAdapter.MyViewHolder mockViewHolder;

    @Mock
    private View mockView;

    private MyAdapter myAdapter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        myAdapter = new MyAdapter(mockContext, mockNotesList);
    }

    @Test
    public void testOnCreateViewHolder() {
        ViewGroup mockParent = mock(ViewGroup.class);
        LayoutInflater mockInflater = mock(LayoutInflater.class);

        when(mockContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).thenReturn(mockInflater);
        when(mockInflater.inflate(R.layout.item_view, mockParent, false)).thenReturn(mockView);

        MyAdapter.MyViewHolder viewHolder = myAdapter.onCreateViewHolder(mockParent, 0);

        assertEquals(viewHolder.itemView, mockView);
    }

    @Test
    public void testOnBindViewHolder() {
        int position = 0;

        Notes mockNotes = mock(Notes.class);
        when(mockNotesList.get(position)).thenReturn(mockNotes);

        myAdapter.onBindViewHolder(mockViewHolder, position);

        // Add your verification steps here, for example:
        verify(mockViewHolder).titleOutput.setText(mockNotes.getTitle());
        verify(mockViewHolder).descriptionOutput.setText(mockNotes.getDescription());
        // ... and so on
    }

    // Add more tests for other methods if needed
}