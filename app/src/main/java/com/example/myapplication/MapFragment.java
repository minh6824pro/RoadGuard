package com.example.myapplication;

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

public class MapFragment extends Fragment {

    private EditText searchBar;
    private ImageView imageView, imageView2, imageView3, imageView4, imageView5, location;
    private TextView locationText, detectedByText;
    private Button statusButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_map, container, false);

        // Ánh xạ các view từ XML
        searchBar = view.findViewById(R.id.search_bar);
        imageView = view.findViewById(R.id.imageView);
        imageView2 = view.findViewById(R.id.imageView2);
        imageView3 = view.findViewById(R.id.imageView3);
        imageView4 = view.findViewById(R.id.imageView4);
        imageView5 = view.findViewById(R.id.imageView5);
        location = view.findViewById(R.id.location);
        locationText = view.findViewById(R.id.locationText);
        detectedByText = view.findViewById(R.id.detectedByText);
        statusButton = view.findViewById(R.id.statusButton);

        // Sự kiện cho thanh tìm kiếm
        searchBar.setOnClickListener(v -> {
            String searchText = searchBar.getText().toString();
            Toast.makeText(getContext(), "Tìm kiếm: " + searchText, Toast.LENGTH_SHORT).show();
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

        return view;
    }

    // Phương thức tiện ích để hiển thị thông báo
    private void showToast(@NonNull String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
