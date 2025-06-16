package com.example.bigcart;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigcart.Adapters.OrdersAdapter;
import com.example.bigcart.Models.Order;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class MyOrder extends AppCompatActivity {
    public ImageView backarr, filter;
    public RecyclerView orderrecv;
    public BottomNavigationView bottomNavigationView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.myorder);
        getAllOrders();
//        DataBinding.saveBearerToken("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InNpbXR0YXhxZnFzYmprcWh3dHJlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI3MTcsImV4cCI6MjA2NDYwODcxN30.xGy-mo6G9WKYn5y7Xm841lZD5xtvxjztAc_jmucel_E");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.banner1), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        backarr = findViewById(R.id.backarr);
        orderrecv = findViewById(R.id.orderrecv);
        filter = findViewById(R.id.filter);
        backarr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.nav_order) {
                    Intent intent = new Intent(MyOrder.this, MyOrder.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.nav_home) {
                    Intent intent = new Intent(MyOrder.this, Home.class);
                    startActivity(intent);
                    return true;}
                else if (itemId == R.id.nav_profile) {
                    Intent intent = new Intent(MyOrder.this, Profile.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.nav_cart) {
                    Intent intent = new Intent(MyOrder.this, Cart.class);
                    startActivity(intent);
                    return true;
                } else {
                    return false;
                }
            }
        });
    }
    private void getAllOrders(){
        SupabaseClient supabaseClient = new SupabaseClient();
        supabaseClient.fetchAllOrders(new SupabaseClient.SBC_Callback() {
            @Override
            public void onFailure(IOException e) {
                runOnUiThread(()->{
                    Log.e("getAllOrders:onFailure", e.getLocalizedMessage());
                });
            }
            @Override
            public void onResponse(String responseBody) {
                runOnUiThread(()->{
                    Log.e("getAllOrders:onResponse", responseBody);
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Order>>(){}.getType();
                    List<Order> orderList = gson.fromJson(responseBody, type);
                    OrdersAdapter ordersAdapter = new OrdersAdapter(getApplicationContext(),orderList);
                    orderrecv.setAdapter(ordersAdapter);
                    orderrecv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                });
            }
        });
    }
}
