package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class SettingFragment extends Fragment {
    private boolean isExpanded = false;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false); // Thay đổi thành layout của bạn

        LinearLayout languageSelector = view.findViewById(R.id.language_selector);
        TextView textVietnamese = view.findViewById(R.id.text_vietnamese);
        TextView textEnglish = view.findViewById(R.id.text_english);
        TextView textJapanese = view.findViewById(R.id.text_japanese);
        ImageView iconArrow = view.findViewById(R.id.icon_arrow); // Tham chiếu đến ImageView

        languageSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isExpanded = !isExpanded; // Đảo trạng thái hiển thị

                if (isExpanded) {
                    // Hiện các TextView
                    textVietnamese.setVisibility(View.VISIBLE);
                    textEnglish.setVisibility(View.VISIBLE);
                    textJapanese.setVisibility(View.VISIBLE);
                    iconArrow.setImageResource(R.drawable.ic_arrow_down);
                } else {
                    // Ẩn các TextView
                    textVietnamese.setVisibility(View.GONE);
                    textEnglish.setVisibility(View.GONE);
                    textJapanese.setVisibility(View.GONE);
                    iconArrow.setImageResource(R.drawable.ic_arrow_side);
                }
            }
        });

        return view;
    }
}