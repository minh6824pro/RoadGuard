package com.example.myapplication;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.model.HistoryItem;

import java.util.Arrays;
import java.util.List;

public class HistoryFragment extends Fragment {

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<HistoryItem> itemList = Arrays.asList(
                new HistoryItem("Bạn đã gặp ổ gà", "8:00-08/03/2024", "Thủ Đức", "10.8497° N, 106.7570° E", "Lớn", "Phương Tuấn"),
                new HistoryItem("Bạn đã phát hiện ổ gà", "8:00-08/03/2024", "Thủ Đức", "10.8497° N, 106.7570° E", "Lớn", "Phương Tuấn"),
                new HistoryItem("Bạn đã phát hiện ổ gà", "8:00-08/03/2024", "Thủ Đức", "10.8497° N, 106.7570° E", "Lớn", "Phương Tuấn"),
                new HistoryItem("Bạn đã phát hiện ổ gà", "8:00-08/03/2024", "Thủ Đức", "10.8497° N, 106.7570° E", "Lớn", "Phương Tuấn"),
                new HistoryItem("Bạn đã phát hiện ổ gà", "8:00-08/03/2024", "Thủ Đức", "10.8497° N, 106.7570° E", "Lớn", "Phương Tuấn"),
                new HistoryItem("Bạn đã phát hiện ổ gà", "8:00-08/03/2024", "Thủ Đức", "10.8497° N, 106.7570° E", "Lớn", "Phương Tuấn"),
                new HistoryItem("Bạn đã phát hiện ổ gà", "8:00-08/03/2024", "Thủ Đức", "10.8497° N, 106.7570° E", "Lớn", "Phương Tuấn")




        );

        HistoryItemAdapter adapter = new HistoryItemAdapter(itemList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}