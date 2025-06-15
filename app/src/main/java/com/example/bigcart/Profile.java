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
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profile extends AppCompatActivity{
    public BottomNavigationView bottomNavigationView;
    public TextView aboutme, orders, address, creditcards, transaction, notif, signout;
    public ImageView photo, pict;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.banner1), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        aboutme = findViewById(R.id.aboutme);
        orders = findViewById(R.id.orders);
        address = findViewById(R.id.address);
        creditcards = findViewById(R.id.creditcards);
        transaction = findViewById(R.id.transaction);
        notif = findViewById(R.id.notif);
        signout = findViewById(R.id.signout);
        photo = findViewById(R.id.photo);
        pict = findViewById(R.id.pict);
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
                    return true;}
                else if (itemId == R.id.nav_order) {
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
//        creditcards.setOnClickListener(v -> startActivity(new Intent(this, MyCards.class)));
//        transaction.setOnClickListener(v -> startActivity(new Intent(this, Transaction.class)));
//        notif.setOnClickListener(v -> startActivity(new Intent(this, Notification.class)));
    }
}
