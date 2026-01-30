package com.example.eecs4443lab1.data;

import android.content.Context;
import android.content.SharedPreferences;

public class UserStore {
    private static final String PREFS_NAME = "user_store";
    private SharedPreferences prefs;

    public UserStore(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void addUser(String username, String password) {
        prefs.edit().putString(username, password).apply();
    }

    public boolean isValidUser(String username, String password) {
        String storedPassword = prefs.getString(username, null);
        return storedPassword != null && storedPassword.equals(password);
    }

    public boolean userExists(String username) {
        return prefs.contains(username);
    }
}
