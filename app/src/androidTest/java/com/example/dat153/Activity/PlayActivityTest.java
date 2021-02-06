package com.example.dat153.Activity;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.dat153.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertTrue;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class PlayActivityTest {
    @Rule
    public ActivityTestRule<PlayActivity> mActivityTestRule = new ActivityTestRule<>(PlayActivity.class);


    @Before
    public void setup() {

    }


    @Test
    public void checkIntentToDataset() {

        switch (mActivityTestRule.getActivity().getCurrentQuestion().getCampus()) {
            case FØRDE:
                onView(withId(R.id.radioButton6)).perform(click());
                break;
            case SOGNDAL:
                onView(withId(R.id.radioButton7)).perform(click());
                break;
            case BERGEN:
                onView(withId(R.id.radioButton8)).perform(click());
                break;
            case STORD:
                onView(withId(R.id.radioButton9)).perform(click());
                break;
            case HAUGESUND:
                onView(withId(R.id.radioButton10)).perform(click());
                break;
        }
        int score = mActivityTestRule.getActivity().getScore();
        assertTrue(score > 0);


        onView(withId(R.id.radioButton10)).perform(click());


        assertTrue(mActivityTestRule.getActivity().getScore() == score);


    }


}




