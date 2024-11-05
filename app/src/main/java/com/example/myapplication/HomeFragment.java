package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private PieChart pieChart;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_home, container, false);
    
        
        // Khởi tạo BarChart
        BarChart barChart = view.findViewById(R.id.barChart);

        // Dữ liệu cột đầu tiên cho từng ngày
        ArrayList<BarEntry> values1 = new ArrayList<>();
        values1.add(new BarEntry(0f, 50f)); // Monday
        values1.add(new BarEntry(1f, 80f)); // Tuesday
        values1.add(new BarEntry(2f, 70f)); // Wednesday
        values1.add(new BarEntry(3f, 90f)); // Thursday
        values1.add(new BarEntry(4f, 85f)); // Friday
        values1.add(new BarEntry(5f, 40f)); // Saturday
        values1.add(new BarEntry(6f, 35f)); // Sunday

        // Dữ liệu cột thứ hai cho từng ngày
        ArrayList<BarEntry> values2 = new ArrayList<>();
        values2.add(new BarEntry(0f, 30f)); // Monday
        values2.add(new BarEntry(1f, 90f)); // Tuesday
        values2.add(new BarEntry(2f, 20f)); // Wednesday
        values2.add(new BarEntry(3f, 40f)); // Thursday
        values2.add(new BarEntry(4f, 75f)); // Friday
        values2.add(new BarEntry(5f, 30f)); // Saturday
        values2.add(new BarEntry(5f, 20f)); // Sunday

        // Tạo BarDataSet cho mỗi nhóm cột
        BarDataSet set1 = new BarDataSet(values1, "Phát hiện bởi bạn");
        set1.setColor(getResources().getColor(R.color.detectedBy));

        BarDataSet set2 = new BarDataSet(values2, "Tất cả");
        set2.setColor(getResources().getColor(R.color.Light_Background_BgAccent));

        // Tạo BarData và thêm các BarDataSet vào
        BarData data = new BarData(set1, set2);
        data.setBarWidth(0.25f); // Độ rộng mỗi cột

        // Tùy chỉnh khoảng cách giữa các nhóm cột
        barChart.setData(data);
        barChart.groupBars(-0.5f, 0.3f, 0.1f); // Tham số (startX, groupSpace, barSpace)
        barChart.invalidate();

        // Thiết lập trục X với nhãn
        String[] days = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday","Sunday"};
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(days));
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        barChart.setVisibleXRangeMaximum(7);

        xAxis.setCenterAxisLabels(false);

        xAxis.setAxisMinimum(-0.5f); // Start slightly before the first bar
        xAxis.setAxisMaximum(days.length - 0.5f); // End slightly after the last bar

        // Tùy chọn khác
        barChart.getDescription().setEnabled(false);
        barChart.setDragEnabled(true);
        barChart.setVisibleXRangeMaximum(7); // Số lượng nhãn X tối đa
        barChart.setExtraBottomOffset(10f);


        // Legend center
        Legend legend = barChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);



        
        
        pieChart = view.findViewById(R.id.pieChart);
        setUpPieChart();
        return view;
    }


    private void setUpPieChart() {
        // Tạo danh sách các mục cho biểu đồ
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(30, "Ổ gà lớn"));  // Entry 1
        pieEntries.add(new PieEntry(50, "Ổ gà nhỏ"));  // Entry 2
        pieEntries.add(new PieEntry(20, "Ổ gà trung bình")); // Entry 3

        PieDataSet pieDataSet = new PieDataSet(pieEntries, null);
        pieDataSet.setColors(new int[] {
                Color.parseColor("#FF0000"), // Màu cho "Ổ gà lớn"
                Color.parseColor("#00E5FF"), // Màu cho "Ổ gà nhỏ"
                Color.parseColor("#FFA500")  // Màu cho "Ổ gà trung bình"
        });

        pieDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int) value); // Trả về giá trị thực tế
            }
        });
        // Thiết lập hiển thị tỷ lệ phần trăm với định dạng tùy chỉnh
        pieDataSet.setValueTextSize(12f); // Kích thước chữ hiển thị phần trăm
        pieDataSet.setValueTextColor(Color.BLACK); // Màu chữ hiển thị

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate(); // Cập nhật biểu đồ
        pieChart.setDescription(null); // Ẩn mô tả
        pieChart.setUsePercentValues(true); // Sử dụng giá trị phần trăm
        pieChart.setDrawEntryLabels(false); // Ẩn nhãn mục
        pieChart.setHighlightPerTapEnabled(true); // Kích hoạt tính năng nhấn vào để làm nổi bật
    }




}
