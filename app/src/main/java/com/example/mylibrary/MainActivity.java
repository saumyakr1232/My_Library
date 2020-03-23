package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnSeeAll,    btnCurrentReading, btnWantToRead, btnAlreadyRead, btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidget();
        setOnClickListeners();
    }

    private void initWidget(){
        btnAbout = (Button)findViewById(R.id.btnAbout);
        btnAlreadyRead = (Button)findViewById(R.id.btnAlreadyRead);
        btnCurrentReading = (Button) findViewById(R.id.btnCurrent);
        btnSeeAll = (Button) findViewById(R.id.btnAllBooks);
        btnWantToRead = (Button)findViewById(R.id.btnWantTo);
    }

    private void setOnClickListeners(){
        btnSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllBooksActivity.class);
                startActivity(intent);
            }
        });
    }
}
