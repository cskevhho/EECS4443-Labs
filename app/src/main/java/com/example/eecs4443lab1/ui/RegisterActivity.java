package com.example.eecs4443lab1.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.eecs4443lab1.R;
import com.example.eecs4443lab1.data.UserStore;

import static com.example.eecs4443lab1.util.Validators.isEmpty;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameField, passwordField, passwordAgainField;
    private ImageButton passwordVisibilityBtn, passwordAgainVisibilityBtn;
    private boolean passwordVisible = false, passwordAgainVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameField = findViewById(R.id.username_text_field);
        passwordField = findViewById(R.id.password_text_field);
        passwordAgainField = findViewById(R.id.passwordAgain_text_field);
        passwordVisibilityBtn = findViewById(R.id.password_visibility_button);
        passwordAgainVisibilityBtn = findViewById(R.id.passwordAgain_visibility_button);
        Button registerBtn = findViewById(R.id.register_btn);
        Button cancelBtn = findViewById(R.id.cancel_btn);
        TextView signinLabel = findViewById(R.id.signin_label);

        UserStore userStore = new UserStore(this);

        passwordVisibilityBtn.setOnClickListener(v -> {
            passwordVisible = !passwordVisible;
            if (passwordVisible) {
                passwordField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                passwordVisibilityBtn.setImageResource(R.drawable.baseline_visibility_off_24);
            } else {
                passwordField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                passwordVisibilityBtn.setImageResource(R.drawable.baseline_visibility_24);
            }
            passwordField.setSelection(passwordField.getText().length());
        });

        passwordAgainVisibilityBtn.setOnClickListener(v -> {
            passwordAgainVisible = !passwordAgainVisible;
            if (passwordAgainVisible) {
                passwordAgainField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                passwordAgainVisibilityBtn.setImageResource(R.drawable.baseline_visibility_off_24);
            } else {
                passwordAgainField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                passwordAgainVisibilityBtn.setImageResource(R.drawable.baseline_visibility_24);
            }
            passwordAgainField.setSelection(passwordAgainField.getText().length());
        });

        registerBtn.setOnClickListener(v -> {
            String username = usernameField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();
            String passwordAgain = passwordAgainField.getText().toString().trim();

            if (isEmpty(username) || isEmpty(password) || isEmpty(passwordAgain)) {
                Toast.makeText(this, getString(R.string.toast_fill_in_fields), Toast.LENGTH_SHORT).show();
                return;
            }
            if (!password.equals(passwordAgain)) {
                Toast.makeText(this, getString(R.string.toast_password_mismatch), Toast.LENGTH_SHORT).show();
                return;
            }
            if (userStore.userExists(username)) {
                Toast.makeText(this, getString(R.string.toast_username_taken), Toast.LENGTH_SHORT).show();
                return;
            }

            userStore.addUser(username, password);
            Toast.makeText(this, getString(R.string.toast_registration_success), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        cancelBtn.setOnClickListener(v -> {
            usernameField.setText("");
            passwordField.setText("");
            passwordAgainField.setText("");
        });

        signinLabel.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}