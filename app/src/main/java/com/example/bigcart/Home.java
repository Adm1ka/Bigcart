package com.example.bigcart;

import static androidx.navigation.fragment.FragmentKt.findNavController;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {
    public ImageView veg, fruits, drinks, grocery, oil, household, babycare, nextarr;
    public EditText search;
    public  BottomNavigationView bottomNavigationView;
    @SuppressLint("MissingInflatedId")
@Override
protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.home);
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
        search=findViewById(R.id.search);
        nextarr=findViewById(R.id.nextarr);
        veg=findViewById(R.id.veg);
        fruits=findViewById(R.id.fruits);
        drinks=findViewById(R.id.drinks);
        grocery=findViewById(R.id.grocery);
        oil=findViewById(R.id.oil);
        household=findViewById(R.id.household);
        babycare=findViewById(R.id.babycare);


//        veg.setOnClickListener(v -> startActivity(new Intent(this, Vegetables.class)));
//        fruits.setOnClickListener(v -> startActivity(new Intent(this, Fruits.class)));
//        drinks.setOnClickListener(v -> startActivity(new Intent(this, Beverages.class)));
//        grocery.setOnClickListener(v -> startActivity(new Intent(this, Grocery.class)));
//        oil.setOnClickListener(v -> startActivity(new Intent(this, Edible oil.class)));
//        household.setOnClickListener(v -> startActivity(new Intent(this, Household.class)));
//        babycare.setOnClickListener(v -> startActivity(new Intent(this, Babycare.class)));
//        nextarr.setOnClickListener(v -> startActivity(new Intent(this, Categories.class)));
    }
}
