package com.example.dat153;


import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.matcher.ViewMatchers.hasImeAction;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

@LargeTest
@RunWith(AndroidJUnit4.class)


public class DatasetActivityTest {
    @Rule
    public IntentsTestRule<DatasetActivity> mActivityTestRule = new IntentsTestRule<>(DatasetActivity.class);

    @Before
    public void StubCameraIntent() {
        Instrumentation.ActivityResult result = createImageCaptureActivityResultStub();
        intending(hasAction(MediaStore.ACTION_IMAGE_CAPTURE)).respondWith(result);
    }

    @Test
    public void TestAddPic() {
        onView(withId(R.id.buttonLeggTilBilde)).perform(click());
        int sizeBefore = mActivityTestRule.getActivity().getSize();
        int sizeAfter = sizeBefore;

        onView(withId(R.id.nameEditText)).perform(typeText("TEst input"), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.buttonfinnbilde)).perform(click());

        onView(withId(R.id.buttonLagre)).perform(click());

        sizeAfter = mActivityTestRule.getActivity().getSize();

      assertThat("Size", sizeAfter, greaterThan(sizeBefore));



    }

    private Instrumentation.ActivityResult createImageCaptureActivityResultStub() {
        // Put the drawable in a bundle.
        Bundle bundle = new Bundle();
        bundle.putParcelable("data", BitmapFactory.decodeResource(
                mActivityTestRule.getActivity().getResources(), R.drawable.mario));
        // Create the Intent that will include the bundle.
        Intent resultData = new Intent();
        resultData.putExtras(bundle);
        // Create the ActivityResult with the Intent.
        return new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);
    }
}
