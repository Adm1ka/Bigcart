package com.example.bigcart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class Splash2 extends AppCompatActivity {
    public Button next1, back1, point1, point3, get_started;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.banner2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        next1 = findViewById(R.id.next1);
        back1 = findViewById(R.id.back1);
        point1 = findViewById(R.id.point1);
        point3 = findViewById(R.id.point3);
        get_started = findViewById(R.id.get_started);
        next1.setOnClickListener(v -> startActivity(new Intent(this, Splash3.class)));
        back1.setOnClickListener(v -> startActivity(new Intent(this, Splash1.class)));
        point1.setOnClickListener(v -> startActivity(new Intent(this, Splash1.class)));
        point3.setOnClickListener(v -> startActivity(new Intent(this, Splash3.class)));
        get_started.setOnClickListener(v -> startActivity(new Intent(this, Auth1_welcome.class)));
    }
}
