package com.example.eecs4443lab1;

import com.example.eecs4443lab1.util.Validators;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilsUnitTest {

    @Test
    public void testIsEmpty_withNull() {
        assertTrue(Validators.isEmpty(null));
    }

    @Test
    public void testIsEmpty_withEmptyString() {
        assertTrue(Validators.isEmpty(""));
    }

    @Test
    public void testIsEmpty_withWhitespace() {
        assertTrue(Validators.isEmpty("   "));
    }

    @Test
    public void testIsEmpty_withNonEmptyString() {
        assertFalse(Validators.isEmpty("admin"));
    }

    @Test
    public void testIsValidCredentials_withCorrectCredentials() {
        assertTrue(Validators.isValidCredentials("admin", "test1234!"));
    }

    @Test
    public void testIsValidCredentials_withIncorrectUsername() {
        assertFalse(Validators.isValidCredentials("user", "test1234!"));
    }

    @Test
    public void testIsValidCredentials_withIncorrectPassword() {
        assertFalse(Validators.isValidCredentials("admin", "wrongpass"));
    }

    @Test
    public void testIsValidCredentials_withBothIncorrect() {
        assertFalse(Validators.isValidCredentials("user", "wrongpass"));
    }
}
