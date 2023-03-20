package com.example.lamthuasm_duanmau.DAO;

import com.example.lamthuasm_duanmau.Model.LoaiSach;
import com.example.lamthuasm_duanmau.Model.ThanhVien;

import java.util.List;

public interface ILoaiSach {
    List<LoaiSach> get();
    void insert(LoaiSach loaiSach);
    void update(LoaiSach loaiSach);
    void delete(String loaisachten);

}
