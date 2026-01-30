package com.example.eecs4443lab1.ui;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.eecs4443lab1.R;

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        String username = getIntent().getStringExtra("username");
        TextView welcomeText = findViewById(R.id.welcome_text);
        if (username != null) {
            welcomeText.setText(getString(R.string.welcome_text, username));
        }
    }
}