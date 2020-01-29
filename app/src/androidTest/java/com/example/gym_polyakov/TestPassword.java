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

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestPassword {
    private String check_pass;

    @Rule
    public ActivityTestRule<SignUp> activityRule
            = new ActivityTestRule<>(SignUp.class);

    @Before
    public void initValidString() {
        //Создаём строковую переменную
        check_pass = "MaxQWe_asd@nd8932";
    }

    //Функция password_test протестирует корректный ввод пароля в первое и второе поле
    @Test
    public void password_test() {
        //Проверка корректного ввода в первое поле
        onView(withId(R.id.password_signup_first)).perform(typeText(check_pass), closeSoftKeyboard()).check(matches(withText(check_pass)));
        //Проверка корректного ввода во второе поле
        onView(withId(R.id.password_signup_second)).perform(typeText(check_pass), closeSoftKeyboard()).check(matches(withText(check_pass)));
    }
}
