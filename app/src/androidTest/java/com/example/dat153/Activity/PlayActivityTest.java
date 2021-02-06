package com.example.dat153.Activity;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.dat153.R;

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
@LargeTest
@RunWith(AndroidJUnit4.class)
public class PlayActivityTest {
    @Rule
    public ActivityTestRule<PlayActivity> mActivityTestRule = new ActivityTestRule<>(PlayActivity.class);


    @Test
    public void checkCorrectActivity() {
        onView(withId(R.id.playScore));
    }


    @Test
    public void checkIntentToDataset(){
        int score = mActivityTestRule.getActivity().getScore();


    }


}




