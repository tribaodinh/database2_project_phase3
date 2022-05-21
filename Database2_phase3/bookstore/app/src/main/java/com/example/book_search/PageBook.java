package com.example.book_search;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PageBook extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_book_search);

        TextView tvBookTitle = findViewById(R.id.tvBookTitle);


        Intent intent = getIntent();
        final ArrayList<String> bookTitle = intent.getStringArrayListExtra("title");
        final ArrayList<String> bookCond = intent.getStringArrayListExtra("book_cond");
        final ArrayList<String> author = intent.getStringArrayListExtra("author");
        final ArrayList<String> genre = intent.getStringArrayListExtra("genre");
        final ArrayList<String> price = intent.getStringArrayListExtra("price");

        String books = new String("");

        for(int i = 0; i < bookTitle.size(); i++)
        {
             books += bookTitle.get(i) + " | " + bookCond.get(i) + " | " + author.get(i) + " | " + price.get(i) + System.getProperty("line.separator");
        }

        tvBookTitle.setText(books);
    }
}