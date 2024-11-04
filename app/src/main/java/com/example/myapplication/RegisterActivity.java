package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;

public class RegisterActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPassword, etConfirmPassword;
    private Button btnRegister;
    private TextView tvLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Ánh xạ các view
        etName = findViewById(R.id.etNameInput);
        etEmail = findViewById(R.id.etEmailInput);
        etPassword = findViewById(R.id.etPasswordInput);
        etConfirmPassword = findViewById(R.id.etConfirmPasswordInput);
        btnRegister = findViewById(R.id.btnRegister);
        tvLogin = findViewById(R.id.tvLogin);


        // Xử lý khi nhấn nút đăng ký
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        // Đặt Spannable cho phần "Đăng nhập" trong TextView
        setLoginTextWithSpannable();



    }

    private void registerUser() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        // Kiểm tra tính hợp lệ của thông tin đăng ký
        if (TextUtils.isEmpty(name)) {
            etName.setError("Vui lòng nhập tên của bạn");
            return;
        }

        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Vui lòng nhập email");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Vui lòng nhập mật khẩu");
            return;
        }

        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError("Mật khẩu xác nhận không khớp");
            return;
        }

        // Hiển thị thông báo đăng ký thành công và chuyển sang màn hình đăng nhập
        Toast.makeText(this, "Đã gửi mã xác nhận", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RegisterActivity.this, VerificationRegisterActivity.class);
        activityResultLauncher.launch(intent);
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
