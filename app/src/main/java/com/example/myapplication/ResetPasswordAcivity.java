package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResetPasswordAcivity extends AppCompatActivity {

    private EditText etPasswordInput;
    private EditText etConfirmPasswordInput;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password); // Make sure this matches your layout file name

        // Initialize UI elements
        ImageView backButton = findViewById(R.id.back_button);
        TextView tvWelcomeBack = findViewById(R.id.tvWelcomeBack);
        TextView tvDescription = findViewById(R.id.tvDescription);
        etPasswordInput = findViewById(R.id.etPasswordInput);
        etConfirmPasswordInput = findViewById(R.id.etConfirmPasswordInput);
        btnRegister = findViewById(R.id.btnRegister);

        // Back button click listener
        backButton.setOnClickListener(v -> finish());


        // Register button click listener
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleRegistration();

            }
        });
    }

    private void handleRegistration() {
        String password = etPasswordInput.getText().toString().trim();
        String confirmPassword = etConfirmPasswordInput.getText().toString().trim();

        // Simple validation
        if (password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Hãy điền đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Mật khẩu không khớp.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "Mật khẩu phải có ít nhất 6 kí tự.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Registration logic (e.g., send data to server)
        // For now, we'll just display a success message
        Toast.makeText(this, "Đổi mật khẩu thành công.", Toast.LENGTH_SHORT).show();
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result", "some data");
        setResult(RESULT_OK, resultIntent);
        finish();
        // Optionally navigate to another activity
    }
}
