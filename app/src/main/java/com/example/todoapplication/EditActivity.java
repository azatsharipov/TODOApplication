package com.example.todoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {
    private EditText etHeader, etDoing;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent intent = getIntent();
        String header = intent.getStringExtra("HEADER");
        String doing = intent.getStringExtra("DOING");
        position = intent.getIntExtra("POSITION", 0);

        etHeader = findViewById(R.id.edit_header);
        etHeader.setText(header);

        etDoing = findViewById(R.id.edit_doing);
        etDoing.setText(doing);
        
        TextWatcher onEdited = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                saveData();
            }
        };

        etHeader.addTextChangedListener(onEdited);
        etDoing.addTextChangedListener(onEdited);
    }

    void saveData() {
        SharedPreferences sp = getSharedPreferences("doings", MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString("DOINGS_HEADER" + String.valueOf(position), etHeader.getText().toString());
        ed.putString("DOINGS_TEXT" + String.valueOf(position), etDoing.getText().toString());
        ed.commit();

    }
}
