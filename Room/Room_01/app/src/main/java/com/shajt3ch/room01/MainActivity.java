package com.shajt3ch.room01;


import android.arch.persistence.room.Room;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.shajt3ch.room01.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    public static MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        myDatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "userdb").allowMainThreadQueries().build();

        binding.btnSave.setOnClickListener(view -> {
            User user = new User(binding.etName.getText().toString(), binding.etEmail.getText().toString(), binding.etPass.getText().toString());
            myDatabase.myDatabaseDao().insertUserData(user);

            Toast.makeText(MainActivity.this, "data save successfully", Toast.LENGTH_SHORT).show();

        });

        binding.btnView.setOnClickListener(view -> {

            binding.tvViewData.setText(myDatabase.myDatabaseDao().getData().toString());

        });
    }
}
