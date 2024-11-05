package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    private ImageView backButton;
    private TextView accountTitle, userNameTextView;
    private EditText etNameInput, etEmailInput, etPasswordInput;
    private Button btnSave;

    // Tên của file SharedPreferences
    private static final String PREFS_NAME = "UserProfilePrefs";
    private static final String KEY_USER_NAME = "userName";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Ánh xạ các view
        backButton = view.findViewById(R.id.back_button);
        accountTitle = view.findViewById(R.id.tai_khoan);
        userNameTextView = view.findViewById(R.id.user_name);
        etNameInput = view.findViewById(R.id.etNameInput);
        etEmailInput = view.findViewById(R.id.etEmailInput);
        etPasswordInput = view.findViewById(R.id.etPasswordInput);
        btnSave = view.findViewById(R.id.btnRegister);

        // Tải tên người dùng từ SharedPreferences
        loadUserProfile();

        // Thiết lập sự kiện cho nút back
        backButton.setOnClickListener(v -> {
            getActivity().onBackPressed();
        });

        // Sự kiện cho nút lưu
        btnSave.setOnClickListener(v -> saveProfile());

        return view;
    }

    private void loadUserProfile() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String userName = sharedPreferences.getString(KEY_USER_NAME, getString(R.string.name_profile));
        userNameTextView.setText(userName);
    }

    private void saveProfile() {
        String name = etNameInput.getText().toString().trim();
        String email = etEmailInput.getText().toString().trim();
        String password = etPasswordInput.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(getActivity(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Cập nhật giá trị của TextView với tên mới
        userNameTextView.setText(name);

        // Lưu tên người dùng vào SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_NAME, name);
        editor.apply();

        // Thông báo thành công
        Toast.makeText(getActivity(), "Profile saved successfully!", Toast.LENGTH_SHORT).show();
    }
}
