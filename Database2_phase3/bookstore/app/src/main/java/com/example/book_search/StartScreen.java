package com.example.book_search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StartScreen extends AppCompatActivity {
    Button guestSearch;
    Button customerHist;
    Button thirdQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        guestSearch = (Button) findViewById(R.id.guest_search);
        customerHist = (Button) findViewById(R.id.customer_order_history);
        thirdQuery = (Button) findViewById(R.id.third_query_here);

        guestSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartScreen.this, BookSearchQuery.class);
                StartScreen.this.startActivity(intent);
            }
        });

        customerHist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartScreen.this, CustHist.class);
                StartScreen.this.startActivity(intent);
            }
        });

        thirdQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartScreen.this, Query3.class);
                StartScreen.this.startActivity(intent);
            }
        });
    }





}
