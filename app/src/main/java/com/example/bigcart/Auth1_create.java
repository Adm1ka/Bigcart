package com.example.bigcart;

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
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Auth1_create extends AppCompatActivity{
    public ImageView backarr;
    public Button signup;
    public TextView log;
    public EditText password, email, phone;
//    private static final String BASE_URL="https://simttaxqfqsbjkqhwtre.supabase.co/auth/v1/signup";
//    private static final String API_KEY="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InNpbXR0YXhxZnFzYmprcWh3dHJlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwMzI3MTcsImV4cCI6MjA2NDYwODcxN30.xGy-mo6G9WKYn5y7Xm841lZD5xtvxjztAc_jmucel_E";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.auth1_create);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.banner1), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        backarr = findViewById(R.id.backarr);
        signup = findViewById(R.id.signup);
        log = findViewById(R.id.log);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        Drawable eye = ContextCompat.getDrawable(this, R.drawable.eye);
        Drawable eyehide = ContextCompat.getDrawable(this, R.drawable.eyehide);
        PasswordVisibilityToggle passwordVisibilityToggle = new PasswordVisibilityToggle(password, eye, eyehide);
        password.setOnTouchListener(passwordVisibilityToggle);
        backarr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        log.setOnClickListener(v -> startActivity(new Intent(this, Auth1_login.class)));
        signup.setOnClickListener(v -> startActivity(new Intent(this, Home.class)));
    }
}
