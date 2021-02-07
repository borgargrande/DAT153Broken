package com.example.dat153.Activity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.dat153.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddDeleteQuestionTest {

    @Rule
    public IntentsTestRule<DatabaseActivity> mActivityTestRule = new IntentsTestRule<>(DatabaseActivity.class);


    @Before
    public void stubCameraIntent() {
        Instrumentation.ActivityResult result = createImageCaptureActivityResultStub();

        // Stub the Intent.
        intending(hasAction(MediaStore.ACTION_IMAGE_CAPTURE)).respondWith(result);
    }


    @Test
    public void testAddQuestion() {
        int sizeBefore = mActivityTestRule.getActivity().getAdapter().getCurrentList().size();

        onView(withId(R.id.newQuestionButton)).perform(click());
        onView(withId(R.id.newQuestionPhoto)).perform(click());
        onView(withId(R.id.newquestionSaveBtn)).perform(click());

        int sizeAfter = mActivityTestRule.getActivity().getAdapter().getCurrentList().size();

        assertTrue(sizeBefore < sizeAfter);
        onView(withId(R.id.personTable))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click())).check(matches(isDisplayed()));
        onView(withId(R.id.personTable))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, swipeRight())).check(matches(isDisplayed()));
        onView(withId(R.id.personTable))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click())).check(matches(isDisplayed()));


        assertEquals(sizeBefore, mActivityTestRule.getActivity().getAdapter().getCurrentList().size());


    }



    private Instrumentation.ActivityResult createImageCaptureActivityResultStub() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("data", BitmapFactory.decodeResource(mActivityTestRule.getActivity().getResources(), R.drawable.vie2));


        Intent resultData = new Intent();
        resultData.putExtras(bundle);

        return new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);

    }


}
