package com.example.book_search;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BookSearchQuery extends AppCompatActivity {

    EditText etInput;
    Button submitQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search_query);

        etInput = (EditText) findViewById(R.id.input);
        submitQuery = (Button) findViewById(R.id.submitQuery);

        submitQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String year = etInput.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("SubmitQueryHelp", response);
                            JSONObject jsonResponse = new JSONObject(response);

                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                JSONArray titles = jsonResponse.getJSONArray("titles");
                                JSONArray authors = jsonResponse.getJSONArray("authors");
                                JSONArray genres = jsonResponse.getJSONArray("genres");
                                JSONArray conditions = jsonResponse.getJSONArray("conditions");
                                JSONArray prices = jsonResponse.getJSONArray("prices");

                                ArrayList<String> title = new ArrayList<String>();
                                ArrayList<String> author = new ArrayList<String>();
                                ArrayList<String> genre = new ArrayList<String>();
                                ArrayList<String> book_cond = new ArrayList<String>();
                                ArrayList<String> price = new ArrayList<String>();

                                //all json arrays should all be the same length
                                for(int i = 0; i < titles.length(); i++) {
                                    title.add(titles.getString(i));
                                    author.add(authors.getString(i));
                                    genre.add(genres.getString(i));
                                    book_cond.add(conditions.getString(i));
                                    price.add(prices.getString(i));
                                }

                                Intent intent = new Intent(BookSearchQuery.this, PageBook.class);

                                intent.putStringArrayListExtra("title", title);
                                intent.putStringArrayListExtra("book_cond",book_cond);
                                intent.putStringArrayListExtra("author", author);
                                intent.putStringArrayListExtra("price", price);
                                intent.putStringArrayListExtra("genre", genre);

                                BookSearchQuery.this.startActivity(intent);
                            } else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(BookSearchQuery.this);
                                builder.setMessage("Search Failed").setNegativeButton("Retry", null).create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                QueryRequest queryRequest = new QueryRequest(year,getString(R.string.url) + "query1.php", responseListener);
                RequestQueue queue = Volley.newRequestQueue(BookSearchQuery.this);
                queue.add(queryRequest);
            }

        });




    }
}
