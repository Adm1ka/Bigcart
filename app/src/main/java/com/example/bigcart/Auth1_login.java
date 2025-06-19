package com.example.bigcart;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bigcart.Adapters.OrdersAdapter;
import com.example.bigcart.Models.AuthResponse;
import com.example.bigcart.Models.LoginRequest;
import com.example.bigcart.Models.Order;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Auth1_login extends AppCompatActivity{

    public ImageView backarr;
    public TextView signup, forgot;
    public SwitchCompat remember;
    public TextInputEditText passwordedit, emailedit;
    public Button login;
    public TextInputLayout emailTextInput,passwordTextInputLayout;

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
        emailTextInput = findViewById(R.id.emailTextInput);
        passwordTextInputLayout = findViewById(R.id.passwordTextInputLayout);
        backarr = findViewById(R.id.backarr);
        signup = findViewById(R.id.signup);
        passwordedit = findViewById(R.id.passwordedit);
        emailedit = findViewById(R.id.emailedit);
        forgot = findViewById(R.id.forgot);
        login = findViewById(R.id.log);
        remember = findViewById(R.id.remember);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInput()) {
                    String email = emailedit.getText().toString().trim();
                    String password = passwordedit.getText().toString();
                    loginUser(email, password);
                }
            }
        });
        backarr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        signup.setOnClickListener(v -> startActivity(new Intent(this, Auth1_create.class)));
    }
    private boolean validateInput() {

        String email = emailedit.getText().toString().trim();
        String password = passwordedit.getText().toString().trim();

        if (email.isEmpty()) {
            emailTextInput.setError("Email is required");
            return false;
        } else if (!isValidEmail(email)) {
            emailTextInput.setError("Invalid email address");
            return false;
        } else {
            emailTextInput.setError(null);
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
    public void loginUser(String email, String password) {
        SupabaseClient supabaseClient = new SupabaseClient();
        LoginRequest loginRequest = new LoginRequest(email,password);
        supabaseClient.login(loginRequest, new SupabaseClient.SBC_Callback() {
            @Override
            public void onFailure(IOException e) {
                runOnUiThread(() -> {
                    Log.e("loginUser:onFailure", e.getLocalizedMessage());
                });
            }
            @Override
            public void onResponse(String responseBody) {
                runOnUiThread(() -> {
                    Log.e("loginUser:onResponse", responseBody);
                    Gson gson = new Gson();
                    AuthResponse auth=gson.fromJson(responseBody, AuthResponse.class);
                    DataBinding.saveBearerToken("Bearer "+auth.getAccess_token());
                    DataBinding.saveUuidUser(auth.getUser().getId());
                    startActivity(new Intent(getApplicationContext(), Home.class));
                    Log.e("loginUser:onResponse", auth.getUser().getId());
                });
            }
        });
    }
}
