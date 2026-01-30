package com.example.eecs4443lab1.ui;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eecs4443lab1.R;
import com.example.eecs4443lab1.data.UserStore;
import static com.example.eecs4443lab1.util.Validators.*;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameField, passwordField;
    private TextView incorrectLoginText;
    private CheckBox rememberCheckbox;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "login_prefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_USERNAME = "username";
    private boolean passwordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // skip login if remembered
        if (sharedPreferences.getBoolean(KEY_REMEMBER, false)) {
            String username = sharedPreferences.getString(KEY_USERNAME, "");
            Toast.makeText(this, getString(R.string.toast_login_success, username), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
            finish();
            return;
        }

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        UserStore userStore = new UserStore(this);

        TextView createAccLabel = findViewById(R.id.create_acc_label);
        createAccLabel.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // attribute fields
        usernameField = findViewById(R.id.username_text_field);
        passwordField = findViewById(R.id.password_text_field);
        incorrectLoginText = findViewById(R.id.incorrect_login_text);
        rememberCheckbox = findViewById(R.id.remember_checkbox);
        Button loginBtn = findViewById(R.id.login_btn);
        Button cancelBtn = findViewById(R.id.cancel_btn);
        ImageButton passwordVisibilityBtn = findViewById(R.id.password_visibility_button);

        incorrectLoginText.setVisibility(View.INVISIBLE);

        loginBtn.setOnClickListener(v -> {
            String username = usernameField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();

            if (isEmpty(username) || isEmpty(password)) {
                incorrectLoginText.setText(R.string.incorrect_login);
                incorrectLoginText.setVisibility(View.VISIBLE);
                return;
            }

            if (userStore.isValidUser(username, password)) {
                incorrectLoginText.setVisibility(View.INVISIBLE);

                if (rememberCheckbox.isChecked()) {
                    sharedPreferences.edit()
                            .putBoolean(KEY_REMEMBER, true)
                            .putString(KEY_USERNAME, username)
                            .apply();
                } else {
                    sharedPreferences.edit()
                            .putBoolean(KEY_REMEMBER, false)
                            .remove(KEY_USERNAME)
                            .apply();
                }

                // did it work?
                Toast.makeText(this, getString(R.string.toast_login_success, username), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
                finish();
            } else {
                // well if it didn't work
                incorrectLoginText.setText(R.string.incorrect_login);
                incorrectLoginText.setVisibility(View.VISIBLE);
            }
        });

        // cancel button logic
        cancelBtn.setOnClickListener( v -> {
            usernameField.setText("");
            passwordField.setText("");
            incorrectLoginText.setVisibility(View.INVISIBLE);
            // uncheck remember me if cancelled? we can decide later LOL - Kevin 01302026
            rememberCheckbox.setChecked(false);
        });

        // password viz logic
        passwordVisibilityBtn.setOnClickListener(v -> {
            passwordVisible = !passwordVisible;
            if (passwordVisible) {
                passwordField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                passwordVisibilityBtn.setImageResource(R.drawable.baseline_visibility_off_24); // eye closed
            } else {
                passwordField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                passwordVisibilityBtn.setImageResource(R.drawable.baseline_visibility_24); // eye open
            }
            passwordField.setSelection(passwordField.getText().length());
        });
    }
}