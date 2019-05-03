package com.shajt3ch.room_03;

import android.arch.persistence.room.Room;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;
    public static MyAppDatabase myAppDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //myAppDatabase = Room.databaseBuilder(getApplicationContext(),MyAppDatabase.class,"UserDb").build();
        //creating db have to be done in seperate thread, we can achieve it by AscyncTask or allow to run in main thread by
        //.allowMainThreadQueries()
        myAppDatabase = Room.databaseBuilder(getApplicationContext(),MyAppDatabase.class,"UserDb").allowMainThreadQueries().build();

        fragmentManager = getSupportFragmentManager();

        if (findViewById(R.id.fragment_container)!=null)
        {
            if (savedInstanceState!=null)
                return;

            fragmentManager.beginTransaction().add(R.id.fragment_container,new HomeFragment()).commit();
        }
    }
}
