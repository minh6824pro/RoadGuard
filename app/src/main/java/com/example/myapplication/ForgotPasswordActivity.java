package com.example.myapplication;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    private ImageView backButton;
    private TextView tvLogin;
    private EditText etEmailInput;
    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password); // Use the appropriate layout file name

        // Initialize UI elements
        backButton = findViewById(R.id.back_button);
        tvLogin = findViewById(R.id.tvLogin);
        etEmailInput = findViewById(R.id.etEmailInput);
        btnSend = findViewById(R.id.btnRegister);

        // Set up back button click listener
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Close the activity
            }
        });

        // Set up login link click listener
        setLoginTextWithSpannable();

        // Set up send button click listener
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = etEmailInput.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    etEmailInput.setError("Vui lòng nhập email");
                    return;
                }
                Toast.makeText(ForgotPasswordActivity.this, "Gửi mã thành công", Toast.LENGTH_SHORT).show();
                Intent verificationIntent = new Intent(ForgotPasswordActivity.this, VerificationResetPasswordActivity.class);
                verificationIntent.putExtra("email",email);
                activityResultLauncher.launch(verificationIntent);

                // Handle the send action (e.g., validate email, send reset link)
                // Example: if (isValidEmail(email)) { ... }
            }
        });

    }

    private void setLoginTextWithSpannable() {
        String text = getString(R.string.already_have_account);
        SpannableString spannableString = new SpannableString(text);

        // Định vị trí của từ "Đăng nhập" trong chuỗi
        int loginStartIndex = text.indexOf("Đăng nhập");
        int loginEndIndex = loginStartIndex + "Đăng nhập".length();

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                // Xử lý sự kiện khi nhấn vào "Đăng nhp"
                finish();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.parseColor("#304FFE")); // Đặt màu xanh cho từ "Đăng nhập"
                ds.setUnderlineText(false); // Không gạch chân
            }
        };

        spannableString.setSpan(clickableSpan, loginStartIndex, loginEndIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvLogin.setText(spannableString);
        tvLogin.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {

                    finish();

                }
            }
    );
}
