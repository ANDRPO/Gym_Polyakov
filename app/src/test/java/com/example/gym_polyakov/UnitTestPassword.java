package com.example.gym_polyakov;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UnitTestPassword {
    //Unit тест для проверки метода, который проверяет совпадение паролей при регистрации
    @Test
    public void Passwrod_correct() {
        SignUp signUp = new SignUp();
        //Проверка случая, когда пароли совпадают
        assertFalse(signUp.check_password("qweasd_qwe1Q","qweasd_qwe1Q"));
        //Проверка случая, когда пароли не совпадают
        assertTrue(signUp.check_password("qweasd_qw", "qwe_asd11qwe"));
    }
}
