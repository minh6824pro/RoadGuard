package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class VerificationRegisterActivity extends AppCompatActivity {
    private TextView tvCountdownTimer;
    private EditText input1, input2, input3, input4;
    private CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_reset_password);

        // Initialize views
        ImageView backButton = findViewById(R.id.back_button);
        TextView tvWelcomeBack = findViewById(R.id.tvWelcomeBack);
        TextView tvDescription = findViewById(R.id.tvDescription);
        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);
        input3 = findViewById(R.id.input3);
        input4 = findViewById(R.id.input4);
        Button btnSend = findViewById(R.id.btnSend);
        TextView tvSendCode = findViewById(R.id.tvSendCode);
        String email = getIntent().getStringExtra("email");
        String formattedSubtitle = getString(R.string.input_code_subtitle, email);
        tvDescription.setText(formattedSubtitle);

        
        tvCountdownTimer = findViewById(R.id.tvCountdownTimer);

        // Set up the back button
        backButton.setOnClickListener(v -> finish());
        
        // Set up the register button
        btnSend.setOnClickListener(v -> {
            String code = input1.getText().toString() + input2.getText().toString()
                    + input3.getText().toString() + input4.getText().toString();

            if (code.length() == 4) {

                Toast.makeText(this, "Đăng ký thành công." + code, Toast.LENGTH_SHORT).show();
                Intent resultIntent = new Intent();
                resultIntent.putExtra("result", "some data");
                setResult(RESULT_OK, resultIntent);
                finish();
                // Handle verification code logic here
            } else {
                Toast.makeText(this, "Vui lòng nhập đầy đủ mã xác thực", Toast.LENGTH_SHORT).show();
            }
        });

        // Handle the login link click
        tvSendCode.setOnClickListener(v -> {
            // Code to handle the "Not received" link
            Toast.makeText(this, "Đã gửi lại mã", Toast.LENGTH_SHORT).show();
            if (countDownTimer != null) {
                countDownTimer.cancel(); // Hủy bộ đếm hiện tại
            }
            tvCountdownTimer.setText("01:00"); // Đặt lại TextView về 1:00
            startCountdownTimer(); // Bắt đầu lại bộ đếm
        });

        startCountdownTimer();
        setupTextWatchers();

    }

    private void setupTextWatchers() {
        input1.addTextChangedListener(new GenericTextWatcher(input1, null, input2));
        input2.addTextChangedListener(new GenericTextWatcher(input2, input1, input3));
        input3.addTextChangedListener(new GenericTextWatcher(input3, input2, input4));
        input4.addTextChangedListener(new GenericTextWatcher(input4, input3, null));
    }

    private void startCountdownTimer() {
        countDownTimer=new CountDownTimer(60000, 1000) { // 1 minute countdown with 1-second intervals

            public void onTick(long millisUntilFinished) {
                // Update the TextView with the remaining time
                int secondsRemaining = (int) (millisUntilFinished / 1000);
                tvCountdownTimer.setText(String.format("00:%02d", secondsRemaining));
            }

            public void onFinish() {
                // Countdown is finished, handle the event here
                tvCountdownTimer.setText("00:00");
                finish();
                // Optionally, enable resend button or prompt the user
            }
        }.start();
    }
    private class GenericTextWatcher implements TextWatcher, View.OnKeyListener {

        private EditText currentEditText;
        private EditText previousEditText;
        private EditText nextEditText;

        public GenericTextWatcher(EditText currentEditText, EditText previousEditText, EditText nextEditText) {
            this.currentEditText = currentEditText;
            this.previousEditText = previousEditText;
            this.nextEditText = nextEditText;
            this.currentEditText.setOnKeyListener(this);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // No action needed
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Move to next EditText if current has input and nextEditText is not null
            if (s.length() == 1 && nextEditText != null) {
                nextEditText.requestFocus();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            // No action needed
        }

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            // Check if the key event was a backspace and the field is empty
            if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
                if (currentEditText.getText().toString().isEmpty() && previousEditText != null) {
                    previousEditText.requestFocus();
                    previousEditText.setText(""); // Clear the previous EditText
                }
            }
            return false;
        }
    }

    // MainActivity.java

}