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
import android.widget.ImageView;
import android.widget.TextView;

public class Auth1_welcome extends AppCompatActivity{
public  Button create;
public TextView log;
public ImageView backarr;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.auth1_welcome);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.banner1), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        create = findViewById(R.id.create);
        log = findViewById(R.id.log);
        backarr = findViewById(R.id.backarr);
        backarr.setOnClickListener(v -> startActivity(new Intent(this, Splash1.class)));
        log.setOnClickListener(v -> startActivity(new Intent(this, Auth1_login.class)));
        create.setOnClickListener(v -> startActivity(new Intent(this, Auth1_create.class)));
    }

}


