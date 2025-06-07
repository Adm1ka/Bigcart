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
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Auth1_login extends AppCompatActivity{

    public ImageView backarr;
    public TextView signup;
    public EditText password;
    public Button login;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.auth1_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.banner1), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        backarr = findViewById(R.id.backarr);
        signup=findViewById(R.id.signup);
        password = findViewById(R.id.password);
        login = findViewById(R.id.log);
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
        signup.setOnClickListener(v -> startActivity(new Intent(this, Auth1_create.class)));
        login.setOnClickListener(v -> startActivity(new Intent(this, Home.class)));
    }
}
