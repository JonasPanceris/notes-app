package com.example.notesapp;

import android.widget.EditText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.notesapp.AddNoteActivity;
import com.example.notesapp.MainActivity;
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
import io.realm.RealmResults;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AddNoteActivityTest {

    @Mock
    private Realm mockRealm;

    @Mock
    private RealmResults<Notes> mockNotesList;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void testSaveButtonClicked() {
        ActivityScenario<AddNoteActivity> scenario = ActivityScenario.launch(AddNoteActivity.class);

        EditText mockTitleInput = Mockito.mock(EditText.class);
        EditText mockDescriptionInput = Mockito.mock(EditText.class);
        MaterialButton mockSaveBtn = Mockito.mock(MaterialButton.class);

        Mockito.when(scenario.getActivity().findViewById(R.id.titleInput)).thenReturn(mockTitleInput);
        Mockito.when(scenario.getActivity().findViewById(R.id.descriptionInput)).thenReturn(mockDescriptionInput);
        Mockito.when(scenario.getActivity().findViewById(R.id.saveBtn)).thenReturn(mockSaveBtn);

        Mockito.when(mockTitleInput.getText()).thenReturn("Test Title");
        Mockito.when(mockDescriptionInput.getText()).thenReturn("Test Description");

        Mockito.when(mockRealm.createObject(Notes.class)).thenReturn(new Notes());

        Espresso.onView(ViewMatchers.withId(R.id.saveBtn))
                .perform(ViewActions.click());

        // Add your verification steps here, for example:
        Mockito.verify(mockRealm).beginTransaction();
        Mockito.verify(mockRealm.createObject(Notes.class)).setTitle("Test Title");
        Mockito.verify(mockRealm.createObject(Notes.class)).setDescription("Test Description");
        Mockito.verify(mockRealm.createObject(Notes.class)).setCreatedTime(Mockito.anyLong());
        Mockito.verify(mockRealm).commitTransaction();

        // You can add more verifications based on your code logic

        scenario.close();
    }
}