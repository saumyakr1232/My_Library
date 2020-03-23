package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class WantToActivity extends AppCompatActivity {
    private static final String TAG = "WantToActivity";

    private RecyclerView recyclerView;
    private BooksRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_to);

        recyclerView = (RecyclerView) findViewById(R.id.recView);
    }
}
