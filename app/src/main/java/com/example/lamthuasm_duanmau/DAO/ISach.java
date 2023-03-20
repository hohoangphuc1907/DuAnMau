package com.example.lamthuasm_duanmau.DAO;

import com.example.lamthuasm_duanmau.Model.LoaiSach;
import com.example.lamthuasm_duanmau.Model.Sach;

import java.util.List;

public interface ISach {
    List<Sach> get();
    Sach get(String maSach);
    void insert(Sach sach);
    void update(Sach sach);
    void delete(String sachten);

}
