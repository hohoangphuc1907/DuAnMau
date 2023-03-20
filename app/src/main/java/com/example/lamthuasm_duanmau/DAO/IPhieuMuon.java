package com.example.lamthuasm_duanmau.DAO;

import com.example.lamthuasm_duanmau.Model.LoaiSach;
import com.example.lamthuasm_duanmau.Model.PhieuMuon;

import java.util.List;

public interface IPhieuMuon {
    List<PhieuMuon> get();
    void insert(PhieuMuon phieuMuon);
    void update(PhieuMuon phieuMuon);
    void delete(String maphieumuon);

}
