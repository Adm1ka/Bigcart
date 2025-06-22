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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bigcart.Models.AuthResponse;
import com.example.bigcart.Models.LoginRequest;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.io.IOException;

public class Auth1_create extends AppCompatActivity{
    public ImageView backarr;
    public Button signup;
    public TextView log;
    public TextInputEditText passwordedit, emailedit, phoneedit;
    public TextInputLayout emailTextInput,passwordTextInputLayout,phoneTextInputLayout;

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
        emailTextInput = findViewById(R.id.emailTextInput);
        passwordTextInputLayout = findViewById(R.id.passwordTextInputLayout);
        phoneTextInputLayout=findViewById(R.id.phoneTextInputLayout);
        backarr = findViewById(R.id.backarr);
        signup = findViewById(R.id.signup);
        log = findViewById(R.id.log);
        emailedit = findViewById(R.id.emailedit);
        phoneedit= findViewById(R.id.phoneedit);
        passwordedit = findViewById(R.id.passwordedit);
        backarr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        log.setOnClickListener(v -> startActivity(new Intent(this, Auth1_login.class)));
//        signup.setOnClickListener(v -> startActivity(new Intent(this, Home.class)));
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInput()) {
                    String email = emailedit.getText().toString().trim();
                    String password = passwordedit.getText().toString();
                    signupUser(email, password);
                }
            }
        });
    }
    private boolean validateInput() {
        String email = emailedit.getText().toString().trim();
        String phone = phoneedit.getText().toString().trim();
        String password = passwordedit.getText().toString().trim();

        // Email Validation
        if (email.isEmpty()) {
            emailTextInput.setError("Email is required");
            return false;
        } else if (!isValidEmail(email)) {
            emailTextInput.setError("Invalid email address");
            return false;
        } else {
            emailTextInput.setError(null);
        }
        if (phone.isEmpty()) {
            phoneTextInputLayout.setError("Phone number is required");
            return false;
        } else if (!isValidPhoneNumber(phone)) {
            phoneTextInputLayout.setError("Invalid phone number");
            return false;
        } else {
            phoneTextInputLayout.setError(null);
        }
        if (password.isEmpty()) {
            passwordTextInputLayout.setError("Password is required");
            return false;
        } else if (password.length() < 6) {
            passwordTextInputLayout.setError("Password must be at least 6 characters");
            return false;
        } else {
            passwordTextInputLayout.setError(null);
        }

        return true;
    }
    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }
    private boolean isValidPhoneNumber(String phone) {
        String phonePattern = "^\\+?[0-9]{10,13}$";
        return phone.matches(phonePattern);
    }
    public void signupUser(String email,String password){
        SupabaseClient supabaseClient = new SupabaseClient();
        LoginRequest loginRequest = new LoginRequest(email,password);
        supabaseClient.registerUser(loginRequest, new SupabaseClient.SBC_Callback() {
            @Override
            public void onFailure(IOException e) {
                runOnUiThread(() -> {
                    Log.e("signupUser:onFailure", e.getLocalizedMessage());
                });
            }
            @Override
            public void onResponse(String responseBody) {
                runOnUiThread(() -> {
                    Log.e("signupUser:onResponse", responseBody);
                    Gson gson = new Gson();
                    AuthResponse auth=gson.fromJson(responseBody, AuthResponse.class);
                    DataBinding.saveBearerToken("Bearer "+auth.getAccess_token());
                    DataBinding.saveUuidUser(auth.getUser().getId());
                    startActivity(new Intent(getApplicationContext(), Home.class));
                    Log.e("signupUser:onResponse", auth.getUser().getId());
                });
            }
        });
    }
}

