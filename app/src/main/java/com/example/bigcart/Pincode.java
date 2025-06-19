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
import android.text.TextWatcher;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Pincode extends AppCompatActivity {
    public ImageView backarr;
    public EditText editText1, editText2, editText3, editText4, editText5, editText6;
    public Button start2;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.pincode);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.banner1), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        backarr = findViewById(R.id.backarr);
        start2 = findViewById(R.id.start2);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        editText5 = findViewById(R.id.editText5);
        editText6 = findViewById(R.id.editText6);
        backarr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        setupTextWatchers();
        setupKeyListeners();
    }
    private void setupTextWatchers() {
        editText1.addTextChangedListener(new Pincode.GenericTextWatcher(editText1));
        editText2.addTextChangedListener(new Pincode.GenericTextWatcher(editText2));
        editText3.addTextChangedListener(new Pincode.GenericTextWatcher(editText3));
        editText4.addTextChangedListener(new Pincode.GenericTextWatcher(editText4));
        editText5.addTextChangedListener(new Pincode.GenericTextWatcher(editText5));
        editText6.addTextChangedListener(new Pincode.GenericTextWatcher(editText6));
    }
    private void setupKeyListeners() {
        editText1.setOnKeyListener(new Pincode.GenericKeyListener(null, editText2));
        editText2.setOnKeyListener(new Pincode.GenericKeyListener(editText1, editText3));
        editText3.setOnKeyListener(new Pincode.GenericKeyListener(editText2, editText4));
        editText4.setOnKeyListener(new Pincode.GenericKeyListener(editText3, editText5));
        editText5.setOnKeyListener(new Pincode.GenericKeyListener(editText4, editText6));
        editText6.setOnKeyListener(new Pincode.GenericKeyListener(editText5, null));
    }
    private String getVerificationCode() {
        return editText1.getText().toString() +
                editText2.getText().toString() +
                editText3.getText().toString() +
                editText4.getText().toString() +
                editText5.getText().toString() +
                editText6.getText().toString();
    }
    private class GenericTextWatcher implements TextWatcher {
        private final EditText editText;
        public GenericTextWatcher(EditText editText) {
            this.editText = editText;
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() == 1) {
                View nextFocus = editText.focusSearch(View.FOCUS_RIGHT);
                if (nextFocus != null) {
                    nextFocus.requestFocus();
                }
            }
        }
    }
    private class GenericKeyListener implements View.OnKeyListener {
        private final EditText currentEditText;
        private final EditText nextEditText;
        private final EditText prevEditText;
        public GenericKeyListener(EditText prevEditText, EditText nextEditText) {
            this.currentEditText = (EditText) prevEditText;
            this.nextEditText = (EditText) nextEditText;
            this.prevEditText = prevEditText;
        }
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
                EditText currentEditText = (EditText) v;
                if (currentEditText.getText().toString().isEmpty() && prevEditText != null) {
                    prevEditText.requestFocus();
                    return true;
                }
            }
            return false;
        }
    }
}

