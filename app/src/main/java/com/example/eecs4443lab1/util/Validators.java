package com.example.eecs4443lab1.util;

public class Validators {
    public static boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static boolean isValidCredentials(String username, String password) {
        // hardcoded for now, will change if expected to be dynamic - Kevin 01302026
        return "admin".equals(username) && "test1234!".equals(password);
    }
}