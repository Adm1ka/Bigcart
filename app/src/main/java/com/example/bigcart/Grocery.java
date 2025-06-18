package com.example.bigcart;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bigcart.Adapters.DrinksAdapter;
import com.example.bigcart.Adapters.GroceryAdapter;
import com.example.bigcart.Models.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Grocery extends AppCompatActivity {
    public ImageView backarr, filter;
    public RecyclerView groceryrecv;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.grocery);
        getGrocery();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.banner1), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        groceryrecv=findViewById(R.id.groceryrecv);
        backarr = findViewById(R.id.backarr);
        filter = findViewById(R.id.filter);
        backarr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        filter.setOnClickListener(v -> startActivity(new Intent(this, Filter.class)));
    }
    private void getGrocery() {
        SupabaseClient supabaseClient = new SupabaseClient();
        supabaseClient.fetchGrocery(new SupabaseClient.SBC_Callback() {
            @Override
            public void onFailure(IOException e) {
                runOnUiThread(() -> {
                    Log.e("getGrocery:onFailure", e.getLocalizedMessage());
                });
            }

            @Override
            public void onResponse(String responseBody) {
                runOnUiThread(() -> {
                    Log.e("getGrocery:onResponse", responseBody);
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<com.example.bigcart.Models.Product>>() {
                    }.getType();
                    List<Product> productList = gson.fromJson(responseBody, type);
                    GroceryAdapter groceryAdapter = new GroceryAdapter(getApplicationContext(), productList);
                    groceryrecv.setAdapter(groceryAdapter);
                    groceryrecv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                });
            }
        });
    }
}
