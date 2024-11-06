package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MapFragment extends Fragment {

    private EditText searchBar;
    private ImageView imageView, imageView2, imageView3, imageView4, imageView5, location;
    private FrameLayout popupContainer;
    private View backgroundView; // Thêm biến để ánh xạ view mờ

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
        popupContainer = view.findViewById(R.id.popup_container);
        backgroundView = view.findViewById(R.id.popup_background); // Ánh xạ view mờ

        // Thiết lập listener cho background view để ẩn popup
        backgroundView.setOnClickListener(v -> hidePopup());

        // Sự kiện cho thanh tìm kiếm
        searchBar.setOnClickListener(v -> {
            String searchText = searchBar.getText().toString();
            Toast.makeText(getContext(), "Tìm kiếm: " + searchText, Toast.LENGTH_SHORT).show();
        });

        // Xử lý sự kiện khi nhấn vào các hình ảnh và hiển thị popup thông tin
        imageView.setOnClickListener(v -> showPotholeInfoPopup(imageView, "Thủ Đức", "Phát hiện bởi: Tuấn", R.drawable.sample_pothole, "Đã sửa"));
        imageView2.setOnClickListener(v -> showPotholeInfoPopup(imageView2, "Quận 1", "Phát hiện bởi: Minh", R.drawable.sample_pothole, "Chưa sửa"));
        imageView3.setOnClickListener(v -> showPotholeInfoPopup(imageView3, "Bình Thạnh", "Phát hiện bởi: Lan", R.drawable.sample_pothole, "Đang sửa"));
        imageView4.setOnClickListener(v -> showPotholeInfoPopup(imageView4, "Gò Vấp", "Phát hiện bởi: An", R.drawable.sample_pothole, "Đã sửa"));
        imageView5.setOnClickListener(v -> showPotholeInfoPopup(imageView5, "Quận 3", "Phát hiện bởi: Hải", R.drawable.sample_pothole, "Đã báo cáo"));

        return view;
    }

    private void hidePopup() {
        popupContainer.setVisibility(View.GONE);
        backgroundView.setVisibility(View.GONE); // Ẩn view mờ
    }

    private void showPotholeInfoPopup(View anchorView, String location, String detectedBy, int imageResId, String statusText) {
        // Xóa popup cũ nếu có
        popupContainer.removeAllViews();

        // Tạo fragment mới và thêm vào container
        MapPopupFragment popupFragment = MapPopupFragment.newInstance(location, detectedBy, imageResId, statusText);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.popup_container, popupFragment);
        transaction.commit();

        // Đặt vị trí cho popupContainer
        int[] locationOnScreen = new int[2];
        anchorView.getLocationOnScreen(locationOnScreen);

        // Lấy kích thước của popupContainer
        popupContainer.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        int popupWidth = popupContainer.getMeasuredWidth();
        int popupHeight = popupContainer.getMeasuredHeight();

        // Tính toán vị trí cho popup
        int x = locationOnScreen[0] + (anchorView.getWidth() - popupWidth) / 2;
        int y = locationOnScreen[1] - popupHeight - 20;

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        );
        params.leftMargin = x;
        params.topMargin = y;
        popupContainer.setLayoutParams(params);
        popupContainer.requestLayout(); // Yêu cầu cập nhật layout

        // Hiện background và popup
        backgroundView.setVisibility(View.VISIBLE);
        popupContainer.setVisibility(View.VISIBLE);
    }

}
