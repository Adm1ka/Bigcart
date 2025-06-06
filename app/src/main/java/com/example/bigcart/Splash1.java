package com.example.bigcart;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.widget.Button;

public class Splash1 extends AppCompatActivity {
    public Button next1, point2, point3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.splash1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.banner1), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        next1 = findViewById(R.id.next1);
        point2 = findViewById(R.id.point2);
        point3 = findViewById(R.id.point3);
        next1.setOnClickListener(v -> startActivity(new Intent(this, Splash2.class)));
        point2.setOnClickListener(v -> startActivity(new Intent(this, Splash2.class)));
        point3.setOnClickListener(v -> startActivity(new Intent(this, Splash3.class)));
    }
}
