package com.example.bigcart;

import static androidx.navigation.fragment.FragmentKt.findNavController;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.bigcart.Adapters.ProductAdapter;
import com.example.bigcart.Models.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Home extends AppCompatActivity {
    public ImageView veg, fruits, drinks, grocery, oil, household, babycare, nextarr;
    public EditText search;
    public RecyclerView prodrecv;
    public  BottomNavigationView bottomNavigationView;
    @SuppressLint("MissingInflatedId")
@Override
protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.home);
        getAllProducts();
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.banner1), (v, insets) -> {
        Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        return insets;
    });
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.nav_home) {
                    Intent intent = new Intent(Home.this, Home.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.nav_profile) {
                    Intent intent = new Intent(Home.this, Profile.class);
                    startActivity(intent);
                    return true;}
                else if (itemId == R.id.nav_order) {
                    Intent intent = new Intent(Home.this, MyOrder.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.nav_cart) {
                    Intent intent = new Intent(Home.this, Cart.class);
                    startActivity(intent);
                    return true;
                } else {
                    return false;
                }
            }
        });

        prodrecv = findViewById(R.id.prodrecv);
        search=findViewById(R.id.search);
        nextarr=findViewById(R.id.nextarr);
        veg=findViewById(R.id.veg);
        fruits=findViewById(R.id.fruits);
        drinks=findViewById(R.id.drinks);
        grocery=findViewById(R.id.grocery);
        oil=findViewById(R.id.oil);
        household=findViewById(R.id.household);
        babycare=findViewById(R.id.babycare);

        veg.setOnClickListener(v -> startActivity(new Intent(this, Vegetables.class)));
        fruits.setOnClickListener(v -> startActivity(new Intent(this, Fruits.class)));
        drinks.setOnClickListener(v -> startActivity(new Intent(this, Beverages.class)));
        grocery.setOnClickListener(v -> startActivity(new Intent(this, Grocery.class)));
        oil.setOnClickListener(v -> startActivity(new Intent(this, EdibleOil.class)));
        household.setOnClickListener(v -> startActivity(new Intent(this, Household.class)));
        babycare.setOnClickListener(v -> startActivity(new Intent(this, Babycare.class)));
        nextarr.setOnClickListener(v -> startActivity(new Intent(this, Category.class)));
    }
    private void getAllProducts() {
        SupabaseClient supabaseClient = new SupabaseClient();
        supabaseClient.fetchAllProducts(new SupabaseClient.SBC_Callback() {
            @Override
            public void onFailure(IOException e) {
                runOnUiThread(() -> {
                    Log.e("getAllProducts:onFailure", e.getLocalizedMessage());
                });
            }

            @Override
            public void onResponse(String responseBody) {
                runOnUiThread(() -> {
                    Log.e("getAllProducts:onResponse", responseBody);
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Product>>() {
                    }.getType();
                    List<Product> productList = gson.fromJson(responseBody, type);
                    ProductAdapter productAdapter = new ProductAdapter(getApplicationContext(), productList);
                    prodrecv.setAdapter(productAdapter);
                    prodrecv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                });
            }
        });
    }
}
