package com.example.dat153.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.GeneralLocation;
import androidx.test.espresso.action.GeneralSwipeAction;
import androidx.test.espresso.action.Press;
import androidx.test.espresso.action.Swipe;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.GrantPermissionRule;

import com.example.dat153.R;
import com.example.dat153.ViewModels.QuestionViewModel;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddDeleteQuestionTest {
    private static final String TAG = "NewQuestionActivityTest";

    @Rule
    public IntentsTestRule<DatabaseActivity> mActivityTestRule = new IntentsTestRule<>(DatabaseActivity.class);


    @Before
    public void stubCameraIntent() {
        Instrumentation.ActivityResult result = createImageCaptureActivityResultStub();

        // Stub the Intent.
        intending(hasAction(MediaStore.ACTION_IMAGE_CAPTURE)).respondWith(result);
    }


    @Test
    public void testAddQuestion() throws InterruptedException {
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



        assertTrue(sizeBefore == mActivityTestRule.getActivity().getAdapter().getCurrentList().size());


    }



    private Instrumentation.ActivityResult createImageCaptureActivityResultStub() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("data", BitmapFactory.decodeResource(mActivityTestRule.getActivity().getResources(), R.drawable.vie2));


        Intent resultData = new Intent();
        resultData.putExtras(bundle);

        return new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);

    }


}
