package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    private static final String TAG = "BookActivity";

    private TextView bookName, authorName, description, pageNo;
    private ImageView bookImage;
    private Button btnWantToRead, btnCurReading, btnAlreadyRead;

    private Book incomingBook;
    private Util util;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        overridePendingTransition(R.anim.in, R.anim.out);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initWidgets();

        Intent intent = getIntent();
        int id = intent.getIntExtra("bookId", 0);
        util = new Util();
        ArrayList<Book> books = util.getAllBooks();
        for (Book b : books){
            if(b.getId() == id){
                incomingBook = b;
                bookName.setText(b.getName());
                authorName.setText(b.getAuthor());
                description.setText(b.getDescription());
                pageNo.setText("Pages :" + b.getPages());

                Glide.with(this)
                        .asBitmap()
                        .load(b.getImageUrl())
                        .into(bookImage);

            }
        }

        btnCurReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCurReadingTapped();
            }
        });

        btnAlreadyRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAlreadyReadTapped();
            }
        });

        btnWantToRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnWantToReadTapped();
            }
        });

    }

    private void btnCurReadingTapped(){
        Log.d(TAG, "btnCurReadingTapped: started");

        ArrayList<Book> currentlyReadingBooks = Util.getCurrentlyReadBooks();


        if (currentlyReadingBooks.contains(incomingBook)){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage(" You Already Added this book to your Currently Reading List");

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setCancelable(false); // then user can'nt close dialog without clicking on "Cancel" or "Ok" button;
            builder.create().show();
        }else {

            ArrayList<Book> wantToReadBooks = util.getWantToReadBooks();

            if (wantToReadBooks.contains(incomingBook)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setMessage("Are you going to start reading this book?");

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        util.removeWantToReadBooks(incomingBook);
                        util.addCurrentlyReadingBook(incomingBook);
                        Toast.makeText(BookActivity.this, "The Book" + incomingBook.getName() + "Added to your currently Reading Books", Toast.LENGTH_LONG).show();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setCancelable(false); // then user can'nt close dialog without clicking on "Cancel" or "Ok" button;
                builder.create().show();
            } else {
                ArrayList<Book> alreadyReadBooks = util.getAlreadyReadBooks();
                if (alreadyReadBooks.contains(incomingBook)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);

                    builder.setMessage("Do you want to read this book again ?");

                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (util.addCurrentlyReadingBook(incomingBook)) {
                                Toast.makeText(BookActivity.this, "The Book" + incomingBook.getName() + "Added to your currently Reading Books", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.setCancelable(false); // then user can'nt close dialog without clicking on "Cancle" or "Ok" button;
                    builder.create().show();
                }else{
                    util.addCurrentlyReadingBook(incomingBook);
                    Toast.makeText(this, "The Book" + incomingBook.getName() + "Added to your currently Reading Books", Toast.LENGTH_LONG).show();

                }
            }
        }

    }

    private void btnAlreadyReadTapped() {
        Log.d(TAG, "btnAlreadyReadTapped: started");

        ArrayList<Book> alreadyReadBooks = Util.getAlreadyReadBooks();



        if (alreadyReadBooks.contains(incomingBook)) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage(" You Already Added this book to your Already Read List");

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setCancelable(false); // then user can'nt close dialog without clicking on "Cancle" or "Ok" button;
            builder.create().show();
        } else {
            ArrayList<Book> currentlyReadingBooks = util.getCurrentlyReadBooks();
            if(currentlyReadingBooks.contains(incomingBook)){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setMessage(" Have you finished reading this book? ");

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        util.removeCurrentlyReadingBooks(incomingBook);
                        util.addAlreadyReadBooks(incomingBook);
                        Toast.makeText(BookActivity.this, "The Book" + incomingBook.getName() + "Added to your Want To Read Books", Toast.LENGTH_LONG).show();
                    }
                });

                builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setCancelable(false); // then user can'nt close dialog without clicking on "Cancle" or "Ok" button;
                builder.create().show();

            }else {
                util.addAlreadyReadBooks(incomingBook);
                Toast.makeText(this, "The Book" + incomingBook.getName() + "Added to your Already Read Books", Toast.LENGTH_LONG).show();
            }

        }
    }
    private void btnWantToReadTapped() {
        Log.d(TAG, "btnWantToReadTapped: started");

        ArrayList<Book> wantToReadBooks = Util.getWantToReadBooks();



        if (wantToReadBooks.contains(incomingBook)) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage(" You Already Added this book to your Want To Read List");

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setCancelable(false); // then user can'nt close dialog without clicking on "Cancel" or "Ok" button;
            builder.create().show();
        } else {

            ArrayList<Book> alreadyReadBooks = util.getAlreadyReadBooks();

            if(alreadyReadBooks.contains(incomingBook)){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("You Already Read this book");
                builder.setTitle("Error");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setCancelable(true);
                builder.create().show();

            }else{
                ArrayList<Book> currentlyReadingBooks = util.getCurrentlyReadBooks();

                if(currentlyReadingBooks.contains(incomingBook)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("you currently reading this book");
                    builder.setTitle("Error");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.setCancelable(false);
                    builder.create().show();


                }else{

                    util.addWantToReadBooks(incomingBook);
                    Toast.makeText(this, "The Book" + incomingBook.getName() + "Added to your Want To Read Books", Toast.LENGTH_LONG).show();

                }
            }



        }

    }


    private void initWidgets() {

        bookImage = (ImageView) findViewById(R.id.bookImage);
        bookName = (TextView) findViewById(R.id.bookName);
        authorName = (TextView) findViewById(R.id.authorName);
        description = (TextView) findViewById(R.id.bookDesc);
        btnAlreadyRead = (Button) findViewById(R.id.btnAlreadyRead);
        btnCurReading = (Button) findViewById(R.id.btnCurReading);
        btnWantToRead = (Button) findViewById(R.id.btnWantToRead);
        pageNo  = (TextView) findViewById(R.id.bookPages);


    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.close_in, R.anim.close_out);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                super.onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
