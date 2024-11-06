package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin;
    private TextView tvRegister;
    private TextView tvForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        // Thiết lập sự kiện cho nút đăng nhập
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        // Thiết lập sự kiện cho tv quên mật khẩu
        tvForgotPassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intentForgotPassword=new Intent(LoginActivity.this,ForgotPasswordActivity.class);
                startActivity(intentForgotPassword);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intentMain = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intentMain);
                finish();
            }
        });

        setLoginTextWithSpannable();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.roadguard_logo));
        }

    }

    private void setLoginTextWithSpannable() {
        // Thiết lập SpannableString cho TextView tvRegister
        String text = getString(R.string.register_prompt);
        SpannableString spannableString = new SpannableString(text);

        int startIndex = text.indexOf("Đăng ký");
        int endIndex = startIndex + "Đăng ký".length();

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                // Xử lý sự kiện khi nhấn vào "Đăng ký"
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.parseColor("#304FFE")); // Đặt màu xanh cho từ "Đăng ký"
                ds.setUnderlineText(false); // Không gạch chân
            }
        };

        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvRegister.setText(spannableString);
        tvRegister.setMovementMethod(LinkMovementMethod.getInstance()); // Cho phép bấm vào link
    }

    private void loginUser() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Kiểm tra thông tin đăng nhập
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter both fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Thực hiện đăng nhập (thực hiện logic xác thực ở đây)
        if (username.equals("test@example.com") && password.equals("password")) {
            // Đăng nhập thành công, chuyển sang MainActivity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Đóng LoginActivity
        } else {
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
        }
    }

}

