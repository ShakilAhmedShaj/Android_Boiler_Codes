package com.shaj.t3ch.sqlitecrud04;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText, genderEditText, addressEditText, idEditText;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    private void initUI() {
        nameEditText = findViewById(R.id.nameEditText);
        genderEditText = findViewById(R.id.genderEditText);
        addressEditText = findViewById(R.id.addressEditText);
        idEditText = findViewById(R.id.idEditText);
        resultTextView = findViewById(R.id.resultTextView);
    }


    // --- Code for insert data
    public void insertButton(View view) {

        String name = nameEditText.getText().toString().trim();
        String gender = genderEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();
        String id = idEditText.getText().toString();

        if (name.isEmpty()) {
            nameEditText.setError("Please enter name");
            nameEditText.requestFocus();
            return;
        }
        if (gender.isEmpty()) {
            genderEditText.setError("Please enter name");
            genderEditText.requestFocus();
            return;
        }
        if (address.isEmpty()) {
            addressEditText.setError("Please enter name");
            addressEditText.requestFocus();
            return;
        }
        if (id.isEmpty()) {
            idEditText.setError("Please enter name");
            idEditText.requestFocus();
            return;
        }

        MyDBClass db = new MyDBClass(this);
        long result = db.insertUser(id, name, gender, address);

        if (result == -1) {

            Toast.makeText(this, "User info does not inserted", Toast.LENGTH_LONG).show();
        } else {

            Toast.makeText(this, "User info inserted", Toast.LENGTH_LONG).show();

            // After inserted, clear all the input fields
            nameEditText.setText("");
            genderEditText.setText("");
            addressEditText.setText("");
            idEditText.setText("");
        }
    }


    // --- Code for view data
    public void viewButton(View view) {

        MyDBClass db = new MyDBClass(this);
        Cursor users = db.getUsers();

        if (users.getCount() > 0) {

            StringBuffer stringBuffer = new StringBuffer();

            while (users.moveToNext()) {

                stringBuffer.append(users.getString(0)).append(". ").append(users.getString(1)).append("\n")
                        .append(users.getString(2)).append(" , ").append(users.getString(3)).append("\n\n");

            }

            resultTextView.setText(stringBuffer);

        } else {
            resultTextView.setText("No data found");
        }
    }


    // ---- Code for update data
    public void updateButton(View view) {

        String name = nameEditText.getText().toString().trim();
        String gender = genderEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();
        String id = idEditText.getText().toString();

        if (name.isEmpty()) {
            nameEditText.setError("Please enter name");
            nameEditText.requestFocus();
            return;
        }
        if (gender.isEmpty()) {
            genderEditText.setError("Please enter name");
            genderEditText.requestFocus();
            return;
        }
        if (address.isEmpty()) {
            addressEditText.setError("Please enter name");
            addressEditText.requestFocus();
            return;
        }
        if (id.isEmpty()) {
            idEditText.setError("Please enter name");
            idEditText.requestFocus();
            return;
        }

        MyDBClass db = new MyDBClass(this);

        int result = db.updateUser(id, name, gender, address);

        if (result == -1) {

            Toast.makeText(this, "Not Updated", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "User info updated", Toast.LENGTH_LONG).show();

            // After updated, clear all the input fields
            nameEditText.setText("");
            genderEditText.setText("");
            addressEditText.setText("");
            idEditText.setText("");
        }
    }


    // --- Code for delete data
    public void deleteButton(View view) {

        String id = idEditText.getText().toString();

        if (id.isEmpty()) {
            idEditText.setError("Please enter id");
            idEditText.requestFocus();
            return;
        }

        MyDBClass db = new MyDBClass(this);
        int result = db.deleteUser(id);

        if (result == -1) {
            Toast.makeText(this, "Not deleted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
            idEditText.setText("");
        }
    }
}
