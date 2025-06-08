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

public class AboutMe extends AppCompatActivity {
    public ImageView backarr;
    public EditText fullname, email, phone, curpass, pass, confpass;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.aboutme);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.banner1), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        backarr=findViewById(R.id.backarr);
        fullname=findViewById(R.id.fullname);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        curpass=findViewById(R.id.curpass);
        pass=findViewById(R.id.pass);
        confpass=findViewById(R.id.confpass);
        Drawable eye = ContextCompat.getDrawable(this, R.drawable.eye);
        Drawable eyehide = ContextCompat.getDrawable(this, R.drawable.eyehide);
        PasswordVisibilityToggle passwordVisibilityToggle = new PasswordVisibilityToggle(pass, eye, eyehide);
        pass.setOnTouchListener(passwordVisibilityToggle);
        backarr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
