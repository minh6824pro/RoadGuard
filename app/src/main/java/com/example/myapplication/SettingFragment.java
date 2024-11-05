package com.example.myapplication;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SettingFragment extends Fragment {
    private boolean isExpanded = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        LinearLayout languageSelector = view.findViewById(R.id.language_selector);
        TextView textVietnamese = view.findViewById(R.id.text_vietnamese);
        TextView textEnglish = view.findViewById(R.id.text_english);
        TextView textJapanese = view.findViewById(R.id.text_japanese);
        ImageView iconArrow = view.findViewById(R.id.icon_arrow);

        languageSelector.setOnClickListener(v -> {
            isExpanded = !isExpanded;

            if (isExpanded) {
                textVietnamese.setVisibility(View.VISIBLE);
                textEnglish.setVisibility(View.VISIBLE);
                textJapanese.setVisibility(View.VISIBLE);
                iconArrow.setImageResource(R.drawable.ic_arrow_down);
            } else {
                textVietnamese.setVisibility(View.GONE);
                textEnglish.setVisibility(View.GONE);
                textJapanese.setVisibility(View.GONE);
                iconArrow.setImageResource(R.drawable.ic_arrow_side);
            }
        });

        LinearLayout accountSettings = view.findViewById(R.id.account_settings);
        accountSettings.setOnClickListener(v -> {
            // Replace the current fragment with ProfileFragment
            Fragment profileFragment = new ProfileFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();

            // Thêm hiệu ứng chuyển cảnh
            transaction.setCustomAnimations(
                    R.anim.slide_in_right,  // Fragment mới vào từ bên phải
                    R.anim.slide_out_left,  // Fragment hiện tại ra bên trái
                    R.anim.slide_in_left,   // Hiệu ứng khi quay lại
                    R.anim.slide_out_right   // Hiệu ứng khi fragment mới ra khỏi backstack
            );

            transaction.replace(R.id.frame_layout, profileFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });


        return view;
    }
}
