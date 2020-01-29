package com.example.gym_polyakov;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestUserName {

    private String check_username;

    @Rule
    public ActivityTestRule<SignUp> activityRule
            = new ActivityTestRule<>(SignUp.class);

    @Before
    public void initValidString() {
        //Создаём строковую переменную
        check_username = "username123_user_QWE";

    }

    //Функция username_test протестирует корректный ввод имени пользователя
    @Test
    public void username_test() {
        //Проверка корректного ввода в поле имени пользователя
        onView(withId(R.id.login_signup)).perform(typeText(check_username), closeSoftKeyboard()).check(matches(withText(check_username)));
    }
}
