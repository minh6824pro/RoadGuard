package com.example.myapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.HistoryItemAdapter;
import com.example.myapplication.model.HistoryItem;
import java.util.Arrays;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<HistoryItem> itemList = Arrays.asList(
                new HistoryItem("Bạn đã gặp ổ gà", "8:00-08/03/2024", "Thủ Đức", "10.8497° N, 106.7570° E", "Lớn", "Phương Tuấn"),
                new HistoryItem("Bạn đã phát hiện ổ gà", "8:00-08/03/2024", "Thủ Đức", "10.8497° N, 106.7570° E", "Lớn", "Phương Tuấn")
                );


        HistoryItemAdapter adapter = new HistoryItemAdapter(itemList);
        recyclerView.setAdapter(adapter);
    }
}
