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
public class TestEmail {

    private String check_email;

    @Rule
    public ActivityTestRule<SignUp> activityRule
            = new ActivityTestRule<>(SignUp.class);

    @Before
    public void initValidString() {
        //Создаём строковую переменную
        check_email = "email_E@asd.ru";
    }

    //Функция password_test протестирует корректный ввод пароля в первое и второе поле
    @Test
    public void email_test() {
        //Проверка корректного ввода в поле электронной почты
        onView(withId(R.id.mail_signup)).perform(typeText(check_email), closeSoftKeyboard()).check(matches(withText(check_email)));
    }
}
