package com.example.book_search;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PageQuery3 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_book_search);

        TextView tvBookTitle = findViewById(R.id.tvBookTitle);

        Intent intent = getIntent();
        final ArrayList<String> bookTitle = intent.getStringArrayListExtra("title");
        final ArrayList<String> author = intent.getStringArrayListExtra("author");
        final ArrayList<String> genre = intent.getStringArrayListExtra("genre");
        final ArrayList<String> isbn = intent.getStringArrayListExtra("isbn");
        final ArrayList<String> bookCond = intent.getStringArrayListExtra("book_cond");
        final ArrayList<String> type = intent.getStringArrayListExtra("type");
        final ArrayList<String> price = intent.getStringArrayListExtra("price");
        final ArrayList<String> trade_val = intent.getStringArrayListExtra("trade_val");
        final ArrayList<String> profit = intent.getStringArrayListExtra("profit");
        final ArrayList<String> on_wishlist = intent.getStringArrayListExtra("on_wishlist");

        String books = new String("");

        for(int i = 0; i < bookTitle.size(); i++)
        {
            books += "Title: " + bookTitle.get(i)
                    +"  |  Author: " + author.get(i)
                    +"\nISBN: " + isbn.get(i)
                    +"  |  Condition: " + bookCond.get(i)
                    +"\nType: " + type.get(i)
                    +"  |  Price: " + price.get(i)
                    +"\nTrade Value: " + trade_val.get(i)
                    +"  |  Profit: " + profit.get(i)
                    +"\nWishlists: " + on_wishlist.get(i) + "\n\n"
                    + System.getProperty("line.separator");
        }

        tvBookTitle.setText(books);
    }
}