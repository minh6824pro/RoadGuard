package com.example.myapplication.model;

import android.view.View;

import com.example.myapplication.R;

public class HistoryItem {
    private String title1;
    private String title2;
    private String khuvuc;
    private String toado;
    private String size;
    private String findby;

    public HistoryItem(String title1, String title2, String khuvuc, String toado, String size, String findby) {
        this.title1 = title1;
        this.title2 = title2;
        this.khuvuc = khuvuc;
        this.toado = toado;
        this.size = size;
        this.findby = findby;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getKhuvuc() {
        return khuvuc;
    }

    public void setKhuvuc(String khuvuc) {
        this.khuvuc = khuvuc;
    }

    public String getToado() {
        return toado;
    }

    public void setToado(String toado) {
        this.toado = toado;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFindby() {
        return findby;
    }

    public void setFindby(String findby) {
        this.findby = findby;
    }

}

