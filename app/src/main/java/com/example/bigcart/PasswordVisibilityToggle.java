package com.example.bigcart;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class PasswordVisibilityToggle implements View.OnTouchListener {
    private EditText password;
    private Drawable eye;
    private Drawable eyehide;
    private boolean passwordVisible = false;
    public PasswordVisibilityToggle(EditText password, Drawable eyeDrawable, Drawable eyeSlashDrawable) {
        this.password = password;
        this.eye = eyeDrawable;
        this.eyehide = eyeSlashDrawable;
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (event.getRawX() >= (password.getRight() - password.getCompoundDrawables()[2].getBounds().width())) {
                passwordVisible = !passwordVisible;
                if (passwordVisible) {
                    password.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, eyehide, null);
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    password.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, eye, null);
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                password.setSelection(password.getText().length());
                return true;
            }
        }
        return false;
    }
}
