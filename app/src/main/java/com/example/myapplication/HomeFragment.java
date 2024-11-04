package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private PieChart pieChart;
    private PieChart pieChart1;
    private int defaultStatusBarColor;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        defaultStatusBarColor = requireActivity().getWindow().getStatusBarColor();

        // Khởi tạo BarChart
        BarChart barChart = view.findViewById(R.id.barChart);
        setUpBarChart(barChart);

        pieChart = view.findViewById(R.id.pieChart);
        setUpPieChart();

        pieChart1 = view.findViewById(R.id.pieChart1);
        setUpPieChart1();

        return view;
    }

    private void setUpBarChart(BarChart barChart) {
        ArrayList<BarEntry> values1 = new ArrayList<>();
        values1.add(new BarEntry(0f, 50f));
        values1.add(new BarEntry(1f, 80f));
        values1.add(new BarEntry(2f, 70f));
        values1.add(new BarEntry(3f, 90f));
        values1.add(new BarEntry(4f, 85f));
        values1.add(new BarEntry(5f, 40f));
        values1.add(new BarEntry(6f, 35f));

        ArrayList<BarEntry> values2 = new ArrayList<>();
        values2.add(new BarEntry(0f, 30f));
        values2.add(new BarEntry(1f, 90f));
        values2.add(new BarEntry(2f, 20f));
        values2.add(new BarEntry(3f, 40f));
        values2.add(new BarEntry(4f, 75f));
        values2.add(new BarEntry(5f, 30f));
        values2.add(new BarEntry(6f, 20f));

        BarDataSet set1 = new BarDataSet(values1, "Phát hiện bởi bạn");
        set1.setColor(getResources().getColor(R.color.detectedBy));

        BarDataSet set2 = new BarDataSet(values2, "Tất cả");
        set2.setColor(getResources().getColor(R.color.Light_Background_BgAccent));

        BarData data = new BarData(set1, set2);
        data.setBarWidth(0.25f);

        barChart.setData(data);
        barChart.groupBars(-0.5f, 0.3f, 0.1f);
        barChart.invalidate();

        String[] days = new String[]{"Thứ Hai", "Thứ Ba", "Thứ Tư", "Thứ Năm", "Thứ Sáu", "Thứ Bảy", "Chủ Nhật"};
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(days));
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setCenterAxisLabels(false);
        xAxis.setAxisMinimum(-0.5f);
        xAxis.setAxisMaximum(days.length - 0.5f);

        barChart.getDescription().setEnabled(false);
        barChart.setDragEnabled(true);
        barChart.setExtraBottomOffset(10f);

        Legend legend = barChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
    }

    private void setUpPieChart1() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(70f, "TP.HCM"));
        entries.add(new PieEntry(90f, "Huế"));
        entries.add(new PieEntry(80f, "Đà Nẵng"));
        entries.add(new PieEntry(85f, "Hà Nội"));
        entries.add(new PieEntry(44f,"Cần Thơ"));

        PieDataSet dataSet = new PieDataSet(entries, null);
        dataSet.setColors(
                Color.parseColor("#AB6DC3"),
                Color.parseColor("#0189BB"),
                Color.parseColor("#FF0000"),
                Color.parseColor("#FFA500"),
                Color.parseColor("#07dec8"));
        dataSet.setValueTextSize(12f);
        dataSet.setValueTextColor(Color.BLACK);

        PieData data = new PieData(dataSet);
        pieChart1.setData(data);
        pieChart1.invalidate();

        pieChart1.setDrawHoleEnabled(false);
        pieChart1.getDescription().setEnabled(false);
        pieChart1.setEntryLabelColor(Color.DKGRAY);
        pieChart1.setEntryLabelTextSize(12f);

        Legend legend = pieChart1.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setFormSize(10f);
        legend.setWordWrapEnabled(true);  // Bật tính năng ngắt dòng cho Legend
        legend.setXEntrySpace(20f);       // Khoảng cách giữa các nhãn theo chiều ngang
        legend.setYEntrySpace(10f);       // Khoảng cách giữa các nhãn theo chiều dọc

    }

    private void setUpPieChart() {
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(30, "Ổ gà lớn"));
        pieEntries.add(new PieEntry(50, "Ổ gà nhỏ"));
        pieEntries.add(new PieEntry(20, "Ổ gà trung bình"));

        PieDataSet pieDataSet = new PieDataSet(pieEntries, null);
        pieDataSet.setColors(new int[]{
                Color.parseColor("#FF0000"),
                Color.parseColor("#00E5FF"),
                Color.parseColor("#FFA500")
        });

        pieDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int) value);
            }
        });

        pieDataSet.setValueTextSize(12f);
        pieDataSet.setValueTextColor(Color.BLACK);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
        pieChart.setDescription(null);
        pieChart.setUsePercentValues(true);
        pieChart.setDrawEntryLabels(false);
        pieChart.setHighlightPerTapEnabled(true);

        Legend legend = pieChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setFormSize(10f);
        legend.setWordWrapEnabled(true);  // Bật tính năng ngắt dòng cho Legend
        legend.setXEntrySpace(20f);       // Khoảng cách giữa các nhãn theo chiều ngang
        legend.setYEntrySpace(10f);       // Khoảng cách giữa các nhãn theo chiều dọc
    }
    @Override
    public void onResume() {
        super.onResume();
        // Đặt màu Status Bar thành trong suốt
        requireActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
    }

    @Override
    public void onPause() {
        super.onPause();
        // Khôi phục lại màu Status Bar ban đầu
        requireActivity().getWindow().setStatusBarColor(defaultStatusBarColor);
    }

}
