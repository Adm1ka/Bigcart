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
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Category extends AppCompatActivity {
    public ImageView backarr;
    public Button veg, fruits, drinks, grocery, oil, household, babycare;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.category);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.banner1), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        backarr = findViewById(R.id.backarr);
        veg = findViewById(R.id.veg);
        fruits = findViewById(R.id.fruits);
        drinks = findViewById(R.id.drinks);
        grocery = findViewById(R.id.grocery);
        oil = findViewById(R.id.oil);
        household = findViewById(R.id.household);
        babycare = findViewById(R.id.babycare);
        backarr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        veg.setOnClickListener(v -> startActivity(new Intent(this, Vegetables.class)));
        fruits.setOnClickListener(v -> startActivity(new Intent(this, Fruits.class)));
        drinks.setOnClickListener(v -> startActivity(new Intent(this, Beverages.class)));
        grocery.setOnClickListener(v -> startActivity(new Intent(this, Grocery.class)));
        oil.setOnClickListener(v -> startActivity(new Intent(this, EdibleOil.class)));
        household.setOnClickListener(v -> startActivity(new Intent(this, Household.class)));
        babycare.setOnClickListener(v -> startActivity(new Intent(this, Babycare.class)));
    }
}
