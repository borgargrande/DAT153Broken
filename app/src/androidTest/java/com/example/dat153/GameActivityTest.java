package com.example.dat153;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.dat153.Utils.Picture;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertTrue;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class GameActivityTest {


    @Rule
    public ActivityTestRule<GameActivity> mActivityTestRule = new ActivityTestRule<>(GameActivity.class);

    @Test
    public void  scoreTest (){
        Picture picture = mActivityTestRule.getActivity().getCurrentPicture();

        int score = mActivityTestRule.getActivity().getKorrekte();

        assertTrue(score == 0);

        onView(withId(R.id.bildeNavn)).perform(typeText(picture.getTitle()), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.answerButton)).perform(click());
        score = mActivityTestRule.getActivity().getKorrekte();
        assertTrue(score == 1);



    }

}
