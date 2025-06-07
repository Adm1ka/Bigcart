package com.example.bigcart;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class Home extends AppCompatActivity {
    public ImageView profile, fav, cart, veg, fruits, drinks, grocery, oil, household, babycare, nextarr;
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
        profile=findViewById(R.id.profile);
        nextarr=findViewById(R.id.nextarr);
        fav=findViewById(R.id.fav);
        cart=findViewById(R.id.cart);
        veg=findViewById(R.id.veg);
        fruits=findViewById(R.id.fruits);
        drinks=findViewById(R.id.drinks);
        grocery=findViewById(R.id.grocery);
        oil=findViewById(R.id.oil);
        household=findViewById(R.id.household);
        babycare=findViewById(R.id.babycare);

//        profile.setOnClickListener(v -> startActivity(new Intent(this, Profile.class)));
//        fav.setOnClickListener(v -> startActivity(new Intent(this, Favorite.class)));
//        cart.setOnClickListener(v -> startActivity(new Intent(this, Cart.class)));
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
