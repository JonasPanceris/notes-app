package com.example.notesapp;

import android.content.Intent;
import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.notesapp.AddNoteActivity;
import com.example.notesapp.MainActivity;
import com.example.notesapp.MyAdepter;
import com.example.notesapp.Notes;
import com.example.notesapp.R;
import com.google.android.material.button.MaterialButton;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class MainActivityTest {

    @Mock
    private RealmResults<Notes> mockNotesList;

    @Mock
    private MyAdepter mockAdapter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Intents.init();
        mockRealm();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void testAddNoteButton() {
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);

        Espresso.onView(ViewMatchers.withId(R.id.addNewNoteBtn))
                .perform(ViewActions.click());

        Intents.intended(IntentMatchers.hasComponent(AddNoteActivity.class.getName()));

        scenario.close();
    }

    @Test
    public void testRealmDataUpdate() {
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);

        Mockito.verify(mockAdapter).notifyDataSetChanged();

        scenario.close();
    }

    private void mockRealm() {
        Realm.init(InstrumentationRegistry.getInstrumentation().getTargetContext());
        Realm mockRealm = Mockito.mock(Realm.class);
        Mockito.when(Realm.getDefaultInstance()).thenReturn(mockRealm);

        // Mock RealmResults
        mockNotesList = Mockito.mock(RealmResults.class);
        Mockito.when(mockRealm.where(Notes.class).findAllSorted("createdTime", io.realm.Sort.DESCENDING))
                .thenReturn(mockNotesList);

        // Mock RealmChangeListener
        Mockito.doAnswer(invocation -> {
            RealmChangeListener listener = invocation.getArgument(0);
            listener.onChange(mockNotesList);
            return null;
        }).when(mockNotesList).addChangeListener(Mockito.any());
    }
}