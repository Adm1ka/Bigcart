package com.example.bigcart;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Splash3 extends AppCompatActivity {
    public Button back1, point1, point2;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.banner3), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        back1 = findViewById(R.id.back1);
        point1 = findViewById(R.id.point1);
        point2 = findViewById(R.id.point2);
        back1.setOnClickListener(v -> startActivity(new Intent(this, Splash2.class)));
        point1.setOnClickListener(v -> startActivity(new Intent(this, Splash1.class)));
        point2.setOnClickListener(v -> startActivity(new Intent(this, Splash2.class)));
    }
}
