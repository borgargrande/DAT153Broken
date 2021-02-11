package com.example.dat153;


import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;


import com.example.dat153.activities.Game;
import com.example.dat153.database.GameObject;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.greaterThan;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestGame {

    @Rule
    public ActivityTestRule<Game> gameTestRule = new ActivityTestRule<Game>(Game.class);

    @Test
    public void checkScore() throws InterruptedException {

        int score = gameTestRule.getActivity().getScore();

        GameObject currentGameObject = gameTestRule.getActivity().getCurrentGameObject();
        int oldScore = score;

        onView(withId(R.id.guessEditText)).perform(typeText(currentGameObject.getName()), closeSoftKeyboard());

        Thread.sleep(1000);

        onView(withId(R.id.guessButton)).perform(click());

        score = gameTestRule.getActivity().getScore();


        assertThat("score", score, greaterThan(oldScore));

    }
}
