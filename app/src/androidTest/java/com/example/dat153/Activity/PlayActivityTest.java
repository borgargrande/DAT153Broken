package com.example.dat153.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.dat153.Entity.Campus;
import com.example.dat153.Entity.Question;
import com.example.dat153.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

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
        if (mActivityTestRule.getActivity().getCurrentQuiz().size() < 2) {
            Question q1 = new Question(Campus.FØRDE);
            q1.setImage(scaledBM(BitmapFactory.decodeResource(mActivityTestRule.getActivity().getResources(), R.drawable.vie_kantine)));

            Question q2 = new Question(Campus.BERGEN);
            q2.setImage(scaledBM(BitmapFactory.decodeResource(mActivityTestRule.getActivity().getResources(), R.drawable.bergen1)));

            List<Question> questions = new ArrayList<>();
            questions.add(q1);
            questions.add(q2);

            mActivityTestRule.getActivity().runOnUiThread(() -> mActivityTestRule.getActivity().setupQuiz(questions));


        }
    }


    @Test
    public void testPlay() {

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

    private Bitmap scaledBM(Bitmap bm) {

        int width = bm.getWidth();
        int height = bm.getHeight();

        int maxWidth = 500;

        int maxHeight = 500;

        if (width > height) {
            // landscape
            float ratio = (float) width / maxWidth;
            width = maxWidth;
            height = (int) (height / ratio);
        } else if (height > width) {
            // portrait
            float ratio = (float) height / maxHeight;
            height = maxHeight;
            width = (int) (width / ratio);
        } else {
            // square
            height = maxHeight;
            width = maxWidth;
        }


        bm = Bitmap.createScaledBitmap(bm, width, height, true);
        return bm;
    }

}




