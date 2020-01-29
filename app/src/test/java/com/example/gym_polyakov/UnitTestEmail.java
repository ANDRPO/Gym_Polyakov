package com.example.gym_polyakov;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTestEmail {
    //Unit тест для проверки метода, который проверяет наличие знака '@' в ведённом E-mail
    @Test
    public void email_correct() {
        SignUp signUp = new SignUp();
        //Проверка случая, когда знак '@' отсутствует
        assertTrue(signUp.check_email("qweqwe"));
        //Проверка случая, когда знак '@' присутствует
        assertFalse(signUp.check_email("qwe@qwe"));
    }
}