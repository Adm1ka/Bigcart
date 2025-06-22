package com.example.bigcart;
import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import com.bumptech.glide.Glide;
import com.example.bigcart.Models.Users;
import java.io.IOException;


public class Profile extends AppCompatActivity{
    public BottomNavigationView bottomNavigationView;
    public TextView aboutme, orders, address, creditcards, transaction, notif, signout, fullname;
    public ImageView photo, pict, backarr;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.profile);
        getCurrentUser();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.banner1), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        backarr = findViewById(R.id.backarr);
        fullname = findViewById(R.id.fullname);
        aboutme = findViewById(R.id.aboutme);
        orders = findViewById(R.id.orders);
        address = findViewById(R.id.address);
        creditcards = findViewById(R.id.creditcards);
        transaction = findViewById(R.id.transaction);
        notif = findViewById(R.id.notif);
        signout = findViewById(R.id.signout);
        photo = findViewById(R.id.photo);
        pict = findViewById(R.id.pict);
        backarr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.nav_profile) {
                    Intent intent = new Intent(Profile.this, Profile.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.nav_home) {
                    Intent intent = new Intent(Profile.this, Home.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.nav_order) {
                    Intent intent = new Intent(Profile.this, MyOrder.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.nav_cart) {
                    Intent intent = new Intent(Profile.this, Cart.class);
                    startActivity(intent);
                    return true;
                } else {
                    return false;
                }
            }
        });
        aboutme.setOnClickListener(v -> startActivity(new Intent(this, AboutMe.class)));
        orders.setOnClickListener(v -> startActivity(new Intent(this, MyOrder.class)));
        address.setOnClickListener(v -> startActivity(new Intent(this, MyAddress.class)));
    }
        private void getCurrentUser(){
            SupabaseClient supabaseClient = new SupabaseClient();
            supabaseClient.FetchCurrentUser(new SupabaseClient.SBC_Callback() {
                @Override
                public void onFailure(IOException e) {
                    runOnUiThread(() -> {
                        Log.e("getCurrentUser:onFailure", e.getLocalizedMessage());
                    });
                }
                @Override
                public void onResponse(String responseBody) {
                    runOnUiThread(() -> {
                        Log.e("getCurrentUser:onResponse", responseBody);
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<Users>>() {}.getType();
                        List<Users> userlist = gson.fromJson(responseBody, type);
                        String url = "https://simttaxqfqsbjkqhwtre.supabase.co/storage/v1/object/public/avatars/";
                        if (userlist != null && !userlist.isEmpty()){
                            String loggedInUserId = DataBinding.getUuidUser();
                            Users profile = null;
                            for (Users u : userlist) {
                                if (u.getId().equals(loggedInUserId)) {
                                    profile = u;
                                    break;
                                }
                            }
                            String getav = profile.getAvatar_url();
                            Glide.with(Profile.this)
                                    .load(url + getav)
                                    .placeholder(R.drawable.usergrey)
                                    .error(R.drawable.usergrey)
                                    .into(pict);
                            fullname.setText(profile.getFull_name());
                        }
                    });
                }
            });
        }
    private void logoutUser(){
        SupabaseClient supabaseClient = new SupabaseClient();
        supabaseClient.signout(new SupabaseClient.SBC_Callback() {
            @Override
            public void onFailure(IOException e) {
                runOnUiThread(() -> {
                    Log.e("getCurrentUser:onFailure", e.getLocalizedMessage());
                });
            }
            @Override
            public void onResponse(String responseBody) {
                runOnUiThread(() -> {
                    Log.e("getCurrentUser:onResponse", responseBody);
                    startActivity(new Intent(getApplicationContext(), Auth1_login.class));
                });
            }
        });
    }

//        creditcards.setOnClickListener(v -> startActivity(new Intent(this, MyCards.class)));
//        transaction.setOnClickListener(v -> startActivity(new Intent(this, Transaction.class)));
//        notif.setOnClickListener(v -> startActivity(new Intent(this, Notification.class)));
    }
