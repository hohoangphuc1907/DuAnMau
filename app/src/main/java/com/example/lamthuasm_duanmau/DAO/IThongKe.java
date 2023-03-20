package com.example.lamthuasm_duanmau.DAO;

import com.example.lamthuasm_duanmau.Model.Top;

import java.util.List;

public interface IThongKe {
    List<Top> getTop();
    int getDoanhThu(String tuNgay,String denNgay);
}
