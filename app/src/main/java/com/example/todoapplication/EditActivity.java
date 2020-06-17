package com.example.todoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent intent = getIntent();
        String header = intent.getStringExtra("HEADER");
        String doing = intent.getStringExtra("DOING");

        EditText etHeader = findViewById(R.id.edit_header);
        etHeader.setText(header);

        EditText etDoing = findViewById(R.id.edit_doing);
        etDoing.setText(doing);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
