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
import com.example.bigcart.Adapters.FruitsAdapter;
import com.example.bigcart.Models.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Beverages extends AppCompatActivity {
    public ImageView backarr, filter;
    public RecyclerView drinksrecv;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.beverages);
        getDrinks();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.banner1), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        drinksrecv=findViewById(R.id.drinksrecv);
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
    private void getDrinks() {
        SupabaseClient supabaseClient = new SupabaseClient();
        supabaseClient.fetchDrinks(new SupabaseClient.SBC_Callback() {
            @Override
            public void onFailure(IOException e) {
                runOnUiThread(() -> {
                    Log.e("getDrinks:onFailure", e.getLocalizedMessage());
                });
            }

            @Override
            public void onResponse(String responseBody) {
                runOnUiThread(() -> {
                    Log.e("getDrinks:onResponse", responseBody);
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<com.example.bigcart.Models.Product>>() {
                    }.getType();
                    List<Product> productList = gson.fromJson(responseBody, type);
                    DrinksAdapter drinksAdapter = new DrinksAdapter(getApplicationContext(), productList);
                    drinksrecv.setAdapter(drinksAdapter);
                    drinksrecv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                });
            }
        });
    }
}
