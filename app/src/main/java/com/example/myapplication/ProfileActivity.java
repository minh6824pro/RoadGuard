package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    private EditText searchBar;
    private ImageView imageView, imageView2, imageView3, imageView4, imageView5, location;
    private TextView locationText, detectedByText;
    private Button statusButton;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Ánh xạ các view từ XML
        searchBar = findViewById(R.id.search_bar);
        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        location = findViewById(R.id.location);
        locationText = findViewById(R.id.locationText);
        detectedByText = findViewById(R.id.detectedByText);
        statusButton = findViewById(R.id.statusButton);

        // Sự kiện cho thanh tìm kiếm
        searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = searchBar.getText().toString();
                Toast.makeText(ProfileActivity.this, "Tìm kiếm: " + searchText, Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý sự kiện khi nhấn vào các hình ảnh
        imageView.setOnClickListener(v -> showToast("Đã chọn imageView 1"));
        imageView2.setOnClickListener(v -> showToast("Đã chọn imageView 2"));
        imageView3.setOnClickListener(v -> showToast("Đã chọn imageView 3"));
        imageView4.setOnClickListener(v -> showToast("Đã chọn imageView 4"));
        imageView5.setOnClickListener(v -> showToast("Đã chọn imageView 5"));
        location.setOnClickListener(v -> showToast("Đã chọn vị trí"));

        // Xử lý sự kiện cho nút trạng thái
        statusButton.setOnClickListener(v -> showToast("Trạng thái: Đã sửa"));

        // Thiết lập sự kiện điều hướng cho thanh điều hướng dưới

    }

    // Phương thức tiện ích để hiển thị thông báo
    private void showToast(@NonNull String message) {
        Toast.makeText(ProfileActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
