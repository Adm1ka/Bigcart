package com.example.bigcart;

import androidx.appcompat.app.AppCompatActivity;
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

public class Product extends AppCompatActivity {
    public ImageView backarr, imgprod;
    public TextView cost, prodname, prodweight, descript;
    public Button remove, add, addcart;
    private TextView textquant;
    private int quantity = 0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.imgprod), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        backarr = findViewById(R.id.backarr);
        imgprod = findViewById(R.id.imgprod);
        addcart = findViewById(R.id.addcart);
        cost = findViewById(R.id.cost);
        prodname = findViewById(R.id.prodname);
        prodweight = findViewById(R.id.prodweight);
        descript = findViewById(R.id.descript);
        textquant = findViewById(R.id.textquant);
        textquant.setText(String.valueOf(quantity));
        add = findViewById(R.id.add);
        remove = findViewById(R.id.remove);
        backarr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity > 0) {
                    quantity--;
                    textquant.setText(String.valueOf(quantity));
                }
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;
                textquant.setText(String.valueOf(quantity));
            }
        });
    }
}
