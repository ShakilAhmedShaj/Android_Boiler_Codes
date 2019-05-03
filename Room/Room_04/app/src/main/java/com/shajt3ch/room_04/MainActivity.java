package com.shajt3ch.room_04;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewModel mainVM;

    // View reference variables
    private EditText insertEditText, editTextID, editTextValue;
    private RecyclerView recView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Instantiate reference variables to the view
        insertEditText = findViewById(R.id.insertEditText);
        editTextID = findViewById(R.id.editTextID);
        editTextValue = findViewById(R.id.editTextValue);


        // Setup the RecyclerView
        recView = findViewById(R.id.recView);
        recView.setLayoutManager(new LinearLayoutManager(this));
        final RecViewAdapter adapter = new RecViewAdapter();
        recView.setAdapter(adapter);

        // Create the ViewModel reference and use it to get
        mainVM = ViewModelProviders.of(this).get(ViewModel.class);
        mainVM.getAllItems().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(@Nullable List<Item> items) {
                adapter.setItems(items);
            }
        });
    }

    // Add Button
    public void addButton(View view) {
        String itemStr = insertEditText.getText().toString();
        Item item = new Item(itemStr);
        mainVM.insertItem(item);
        Toast.makeText(this, "Insert Item", Toast.LENGTH_SHORT).show();
    }

    // Update Button
    public void updateButton(View view) {
        int itemId = Integer.parseInt(editTextID.getText().toString());
        String itemValue = editTextValue.getText().toString();
        mainVM.updateItem(new Item(itemId, itemValue));
    }

    // Delete Button
    public void deleteButton(View view) {
        mainVM.deleteAllItems();
    }
}
