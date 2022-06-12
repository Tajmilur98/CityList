package com.example.citylist;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.Espresso.onView;

import static org.hamcrest.CoreMatchers.anything;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testAppName() {
        onView(withText("CityList")).check(matches(isDisplayed())); //Check the name on the screen
    }

    @Test
    public void testAddCity(){
        onView(withId(R.id.button_add)).perform(click()); //Click add button to add a city to the list
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton")); //Type a city name
        onView(withId(R.id.button_confirm)).perform(click()); //Confirm the city name and add to the list
        onView(withText("Edmonton")).check(matches(isDisplayed())); //Check the name on the screen
    }

    @Test
    public void testClearCity(){
        onView(withId(R.id.button_add)).perform(click()); //Click add button to add a city to the list
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton")); //Type a city name
        onView(withId(R.id.button_confirm)).perform(click()); //Confirm the city name and add to the list
        //Add another city to the list
        onView(withId(R.id.button_add)).perform(click()); //Click add button to add a city to the list
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Toronto")); //Type a city name
        onView(withId(R.id.button_confirm)).perform(click()); //Confirm the city name and add to the list
        //Clear the list
        onView(withId(R.id.button_clear)).perform(click());
        onView(withText("Edmonton")).check(doesNotExist());

    }
    @Test
    public void testListView(){
        onView(withId(R.id.button_add)).perform(click()); //Click add button to add a city to the list
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton")); //Type a city name
        onView(withId(R.id.button_confirm)).perform(click()); //Confirm the city name and add to the list

        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).
                check(matches((withText("Edmonton")))); //Check the content on the list - no content in this case
    }

    @Test
    public void testListViewClickAndBack(){
        onView(withId(R.id.button_add)).perform(click()); //Click add button to add a city to the list
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton")); //Type a city name
        onView(withId(R.id.button_confirm)).perform(click()); //Confirm the city name and add to the list

        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click()); //Check the content on the list - no content in this case
        Espresso.pressBack(); //Back button
    }
    @Test
    public void testFull(){
        onView(withId(R.id.button_add)).perform(click());// clicking add button
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("ABC"));// typing in edittext
        Espresso.pressBack();
        onView(withId(R.id.button_confirm)).perform(click());// confirming
        onView(withText("ABC")).check(matches(isDisplayed()));// abc displayed or not checking

        onView(withId(R.id.button_add)).perform(click());// clicking add button
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("DEF"));// typing in edittext
        Espresso.pressBack();
        onView(withId(R.id.button_confirm)).perform(click());// confirming
        onView(withText("DEF")).check(matches(isDisplayed()));// abc displayed or not checking

        onView(withId(R.id.button_add)).perform(click());// clicking add button
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("HIJ"));// typing in edittext
        Espresso.pressBack();
        onView(withId(R.id.button_confirm)).perform(click());// confirming
        onView(withText("HIJ")).check(matches(isDisplayed()));// abc displayed or not checking

        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click()); // clicking position 0 of list
        onView(withId(R.id.show)).check(matches(isDisplayed()));// second activity viewed or not

        onView(withText("ABC")).check(matches(isDisplayed()));// abc viewed in second activity or not

        onView(withId(R.id.back)).perform(click()); // pressing back button
        onView(withId(R.id.main)).check(matches(isDisplayed()));// MainActivity displayed or not
    }
}