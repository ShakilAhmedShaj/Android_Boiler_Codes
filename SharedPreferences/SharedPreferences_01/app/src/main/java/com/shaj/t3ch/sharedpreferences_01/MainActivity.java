package com.shaj.t3ch.sharedpreferences_01;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String PREFERENCE_NAME = "preference_file";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_PASSWORD = "user_password";

    private SharedPreferences preferences;
    private EditText userNameEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initObjects();
    }

    private void initViews() {
        userNameEditText = findViewById(R.id.userNameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
    }

    private void initObjects() {
        preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
    }

    // ---- Button click event ----
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.saveButton:
                // Get user name and password
                String name = userNameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString();

                if (name.isEmpty()) {
                    userNameEditText.setError("Please enter user name");
                    userNameEditText.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    passwordEditText.setError("Please enter password");
                    passwordEditText.requestFocus();
                    return;
                }
                // Save data to preference
                saveData(name, password);
                break;

            case R.id.retrieveButton:
                retrieveData();
                break;

            case R.id.clearButton:
                // Clear input fields
                userNameEditText.setText("");
                passwordEditText.setText("");
                break;

            case R.id.deletePasswordButton:
                deletePassword();
                break;

            case R.id.deleteAllButton:
                deleteAll();
                break;
        }
    }

    // Code for delete all preferences data
    private void deleteAll() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        if (editor.commit()) {
            Toast.makeText(this, "All data deleted", Toast.LENGTH_SHORT).show();
        }
    }

    // Code for remove password
    private void deletePassword() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(KEY_PASSWORD);
        if (editor.commit()) {
            Toast.makeText(this, "Password deleted", Toast.LENGTH_SHORT).show();
        }
    }

    // Code for retrieve data
    private void retrieveData() {
        if (preferences.contains(KEY_USER_NAME)) {
            userNameEditText.setText(preferences.getString(KEY_USER_NAME, ""));
        }
        if (preferences.contains(KEY_PASSWORD)) {
            passwordEditText.setText(preferences.getString(KEY_PASSWORD, ""));
        }
    }

    // Code for save data
    private void saveData(String name, String password) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_USER_NAME, name);
        editor.putString(KEY_PASSWORD, password);
        if (editor.commit()) {
            Toast.makeText(this, "User data saved", Toast.LENGTH_SHORT).show();
        }
    }
}
