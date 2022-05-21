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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Query3 extends AppCompatActivity {

    EditText etInputName;
    EditText etInputPass;
    Button submitQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publisher_login_query);

        etInputName = (EditText) findViewById(R.id.nameinput);
        etInputPass = (EditText) findViewById(R.id.passinput);
        submitQuery = (Button) findViewById(R.id.submitQuery);

        submitQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String user = etInputName.getText().toString();
                String pass = etInputPass.getText().toString();

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
                                JSONArray isbns = jsonResponse.getJSONArray("isbns");
                                JSONArray conditions = jsonResponse.getJSONArray("conditions");
                                JSONArray types = jsonResponse.getJSONArray("types");
                                JSONArray prices = jsonResponse.getJSONArray("prices");
                                JSONArray trade_vals = jsonResponse.getJSONArray("trade_vals");
                                JSONArray profits = jsonResponse.getJSONArray("profits");
                                JSONArray on_wishlists = jsonResponse.getJSONArray("on_wishlists");

                                ArrayList<String> title = new ArrayList<String>();
                                ArrayList<String> author = new ArrayList<String>();
                                ArrayList<String> genre = new ArrayList<String>();
                                ArrayList<String> isbn = new ArrayList<String>();
                                ArrayList<String> book_cond = new ArrayList<String>();
                                ArrayList<String> type = new ArrayList<String>();
                                ArrayList<String> price = new ArrayList<String>();
                                ArrayList<String> trade_val = new ArrayList<String>();
                                ArrayList<String> profit = new ArrayList<String>();
                                ArrayList<String> on_wishlist = new ArrayList<String>();

                                //all json arrays should all be the same length
                                for(int i = 0; i < titles.length(); i++) {
                                    title.add(titles.getString(i));
                                    author.add(authors.getString(i));
                                    genre.add(genres.getString(i));
                                    isbn.add(isbns.getString(i));
                                    book_cond.add(conditions.getString(i));
                                    type.add(types.getString(i));
                                    price.add(prices.getString(i));
                                    trade_val.add(trade_vals.getString(i));
                                    profit.add(profits.getString(i));
                                    on_wishlist.add(on_wishlists.getString(i));
                                }

                                Intent intent = new Intent(Query3.this, PageQuery3.class);

                                intent.putStringArrayListExtra("title", title);
                                intent.putStringArrayListExtra("author", author);
                                intent.putStringArrayListExtra("genre", genre);
                                intent.putStringArrayListExtra("isbn", isbn);
                                intent.putStringArrayListExtra("book_cond",book_cond);
                                intent.putStringArrayListExtra("type", type);
                                intent.putStringArrayListExtra("price", price);
                                intent.putStringArrayListExtra("trade_val", trade_val);
                                intent.putStringArrayListExtra("profit", profit);
                                intent.putStringArrayListExtra("on_wishlist", on_wishlist);

                                Query3.this.startActivity(intent);
                            } else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(Query3.this);
                                builder.setMessage("Search Failed").setNegativeButton("Retry", null).create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                QueryRequest queryRequest = new QueryRequest(user, pass,getString(R.string.url) + "analytics.php", responseListener);
                RequestQueue queue = Volley.newRequestQueue(Query3.this);
                queue.add(queryRequest);
            }

        });

    }
}
