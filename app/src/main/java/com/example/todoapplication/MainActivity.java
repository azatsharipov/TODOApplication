package com.example.todoapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    DoAdapter adapter;
    RecyclerView rv;
    ArrayList<Doing> doings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rv = findViewById(R.id.rv);
        loadDoings();
        adapter = new DoAdapter(doings);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, LinearLayout.VERTICAL);
        rv.addItemDecoration(dividerItemDecoration);
        rv.setAdapter(adapter);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doings.add(new Doing("New", ""));
                saveDoings();
                adapter.notifyDataSetChanged();
            }
        });
        FloatingActionButton del = findViewById(R.id.bt_delete);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Integer> selected = adapter.getSelected();
                Collections.sort(selected, Collections.<Integer>reverseOrder());
                for (int i : selected) {
                    doings.remove(i);
                }
                saveDoings();
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        loadDoings();
        adapter.notifyDataSetChanged();
    }

    public void loadDoings() {
        doings.clear();
        SharedPreferences sp = getSharedPreferences("doings", MODE_PRIVATE);
        int size = sp.getInt("SIZE", 0);
        for (int i = 0; i < size; i++) {
            doings.add(new Doing(sp.getString("DOINGS_HEADER" + String.valueOf(i), ""),
                    sp.getString("DOINGS_TEXT" + String.valueOf(i), "")));
        }
    }

    public void saveDoings() {
        SharedPreferences sp = getSharedPreferences("doings", MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putInt("SIZE", doings.size());
        for (int i = 0; i < doings.size(); i++) {
            ed.putString("DOINGS_HEADER" + String.valueOf(i), doings.get(i).getHeader());
            ed.putString("DOINGS_TEXT" + String.valueOf(i), doings.get(i).getText());
        }
        ed.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
