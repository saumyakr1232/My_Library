package com.example.mylibrary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Button btnSeeAll,    btnCurrentReading, btnWantToRead, btnAlreadyRead, btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        overridePendingTransition(R.anim.in, R.anim.out);

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
        btnAlreadyRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AlreadyReadActivity.class);
                startActivity(intent);
            }
        });

        btnWantToRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WantToActivity.class);
                startActivity(intent);
            }
        });

        btnCurrentReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CurrentlyReadingActivity.class );
                startActivity(intent);
            }
        });
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aboutTapped();
            }
        });
    }

    private void aboutTapped(){
        Log.d(TAG, "aboutTapped: started");

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("About My Library App")
                .setMessage("Build and published by lol\n" +
                        "\n" +
                        "If you want to hire me or \n" +
                        "if you want to check my other works\n" +
                        "take a look at:\n"+
                        "lol.org")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent= new Intent(MainActivity.this, MyWebViewActivity.class);
                        intent.putExtra("url", "http://www.youtube.com");
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.create().show();


    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.close_in, R.anim.close_out);
    }
}
