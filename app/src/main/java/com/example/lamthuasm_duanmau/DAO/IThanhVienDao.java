package com.example.lamthuasm_duanmau.DAO;

import com.example.lamthuasm_duanmau.Model.ThanhVien;

import java.util.List;

public interface IThanhVienDao {
    List<ThanhVien> get();
    Boolean login(String userName, String password);
    void insert(ThanhVien thanhVien);
    void update(ThanhVien thanhVien);
    void delete(String thanhVienten);

}
