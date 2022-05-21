package com.example.book_search;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class HomepageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_book_search);
        TextView tvBookTitle = findViewById(R.id.tvBookTitle);

        Intent intent = getIntent();
        final ArrayList<String> bookTitle = intent.getStringArrayListExtra("Title");
        final ArrayList<String> ISBN = intent.getStringArrayListExtra("ISBN");
        final ArrayList<String> Book_Cond = intent.getStringArrayListExtra("Book_Cond");
        final ArrayList<String> Type = intent.getStringArrayListExtra("Types");
        final ArrayList<String> Order_Num = intent.getStringArrayListExtra("Order_Num");
        final ArrayList<String> Quantity = intent.getStringArrayListExtra("Quantity");
        final ArrayList<String> Shipping_Method = intent.getStringArrayListExtra("Shipping_Method");
        final ArrayList<String> Shipping_Addr = intent.getStringArrayListExtra("Shipping_Addr");

        String books = new String("");

        for(int i = 0; i < bookTitle.size(); i++)
        {
            books += "Title: " + bookTitle.get(i)
                    +"\nISBN: " + ISBN.get(i)
                    +"  |  Condition: " + Book_Cond.get(i)
                    +"\nType: " + Type.get(i)
                    +"  |  Order Number: " + Order_Num.get(i)
                    +"\nQuantity: " + Quantity.get(i)
                    +"  |  Shipping Method: " + Shipping_Method.get(i)
                    +"\nShipping Address: " + Shipping_Addr.get(i) + "\n\n"
                    + System.getProperty("line.separator");
        }

        tvBookTitle.setText(books.toString());
    }
}