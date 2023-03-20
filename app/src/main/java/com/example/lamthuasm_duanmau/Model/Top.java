package com.example.lamthuasm_duanmau.Model;

public class Top {
    String TenSach;
    String soLuong;

    public Top(String tenSach, String soLuong) {
        TenSach = tenSach;
        this.soLuong = soLuong;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }
}
