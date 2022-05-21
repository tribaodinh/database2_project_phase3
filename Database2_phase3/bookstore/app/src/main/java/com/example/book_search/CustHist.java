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

public class CustHist extends AppCompatActivity {


    Button submitQuery;
    EditText etEmail;
    EditText etPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_query);

        submitQuery = (Button) findViewById(R.id.goHome);

        etEmail = (EditText) findViewById(R.id.editTextTextEmailAddress);
        etPass = (EditText) findViewById(R.id.editTextTextPassword2);

        submitQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //String year = etYear.getText().toString();
                String email = etEmail.getText().toString();
                String PW = etPass.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("SubmitQueryHelp", response);
                            JSONObject jsonResponse = new JSONObject(response);

                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                JSONArray Titles = jsonResponse.getJSONArray("Titles");
                                JSONArray ISBNs = jsonResponse.getJSONArray("ISBN");
                                JSONArray Book_Conds = jsonResponse.getJSONArray("Book_Cond");
                                JSONArray Types = jsonResponse.getJSONArray("Types");
                                JSONArray Order_Nums = jsonResponse.getJSONArray("Order_Num");
                                JSONArray Quantitys = jsonResponse.getJSONArray("Quantity");
                                JSONArray Shipping_Methods = jsonResponse.getJSONArray("Shipping_Method");
                                JSONArray Shipping_Addrs = jsonResponse.getJSONArray("Shipping_Addr");

                                ArrayList<String> Title = new ArrayList<String>();
                                ArrayList<String> ISBN = new ArrayList<String>();
                                ArrayList<String> Book_Cond = new ArrayList<String>();
                                ArrayList<String> Type = new ArrayList<String>();
                                ArrayList<String> Order_Num = new ArrayList<String>();
                                ArrayList<String> Quantity = new ArrayList<String>();
                                ArrayList<String> Shipping_Method = new ArrayList<String>();
                                ArrayList<String> Shipping_Addr = new ArrayList<String>();

                                //all json arrays should all be the same length
                                for(int i = 0; i < Titles.length(); i++) {
                                    Title.add(Titles.getString(i));
                                    ISBN.add(ISBNs.getString(i));
                                    Book_Cond.add(Book_Conds.getString(i));
                                    Type.add(Types.getString(i));
                                    Order_Num.add(Order_Nums.getString(i));
                                    Quantity.add(Quantitys.getString(i));
                                    Shipping_Method.add(Shipping_Methods.getString(i));
                                    Shipping_Addr.add(Shipping_Addrs.getString(i));
                                }

                                Intent intent = new Intent(com.example.book_search.CustHist.this, HomepageActivity.class);

                                intent.putStringArrayListExtra("Title", Title);
                                intent.putStringArrayListExtra("ISBN", ISBN);
                                intent.putStringArrayListExtra("Book_Cond", Book_Cond);
                                intent.putStringArrayListExtra("Types", Type);
                                intent.putStringArrayListExtra("Order_Num",Order_Num);
                                intent.putStringArrayListExtra("Quantity", Quantity);
                                intent.putStringArrayListExtra("Shipping_Method", Shipping_Method);
                                intent.putStringArrayListExtra("Shipping_Addr", Shipping_Addr);


                                //Login.this.startActivity(intention);
                                com.example.book_search.CustHist.this.startActivity(intent);
                            } else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(com.example.book_search.CustHist.this);
                                builder.setMessage("Sign In Failed").setNegativeButton("Retry", null).create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                QueryRequest queryRequest = new QueryRequest(email, PW, getString(R.string.url) + "Query2.php", responseListener);
                RequestQueue queue = Volley.newRequestQueue(com.example.book_search.CustHist.this);
                queue.add(queryRequest);
            }

        });
    }
}
