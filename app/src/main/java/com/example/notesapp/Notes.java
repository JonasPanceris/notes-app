package com.example.notesapp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NotesTest {

    @Test
    public void testSetTitle() {
        Notes notes = new Notes();
        String title = "Test Title";
        notes.setTitle(title);
        assertEquals(title, notes.getTitle());
    }

    @Test
    public void testSetDescription() {
        Notes notes = new Notes();
        String description = "Test Description";
        notes.setDescription(description);
        assertEquals(description, notes.getDescription());
    }

    @Test
    public void testSetCreatedTime() {
        Notes notes = new Notes();
        long createdTime = System.currentTimeMillis();
        notes.setCreatedTime(createdTime);
        assertEquals(createdTime, notes.getCreatedTime());
    }

    // You can add more tests if needed, for example, testing getters
}