package com.example.lamthuasm_duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lamthuasm_duanmau.Model.ThanhVien;
import com.example.lamthuasm_duanmau.database.Database;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienDao implements IThanhVienDao{
    Database mydatabase;
    public ThanhVienDao(Context context){
        mydatabase=new Database(context);
    }


    @Override
    public List<ThanhVien> get() {
        List<ThanhVien> list=new ArrayList<>();
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM ThanhVien",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String maTV=cursor.getString(0);
            String ten=cursor.getString(1);
            String namSinh=cursor.getString(2);

            String mk=cursor.getString(3);
            ThanhVien thanhVien=new ThanhVien(maTV,ten,namSinh,mk);
            list.add(thanhVien);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<ThanhVien> getDuLieuThanhVien(String hoTen) {
        List<ThanhVien> list=new ArrayList<>();
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM ThanhVien WHERE hoTen= ?",new String[]{hoTen});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String maTV=cursor.getString(0);
            String ten=cursor.getString(1);
            String namSinh=cursor.getString(2);
            String mk=cursor.getString(3);
            ThanhVien thanhVien=new ThanhVien(maTV,ten,namSinh,mk);
            list.add(thanhVien);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    @Override
    public void insert(ThanhVien thanhVien) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put("maTV",thanhVien.getMaTV());
        values.put("hoTen",thanhVien.getHoTen());
        values.put("namSinh",thanhVien.getNamSinh());
        values.put("matKhau",thanhVien.getMatKhau());

        database.insert("ThanhVien",null,values);

    }

    @Override
    public void update(ThanhVien thanhVien) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        ContentValues values=new ContentValues();
        String[] params=new String[]{thanhVien.getMaTV()};

        values.put("hoTen",thanhVien.getHoTen());
        values.put("namSinh",thanhVien.getNamSinh());
        database.update("ThanhVien",values,"maTV = ?",params);

    }
    public void updateMatKhau(ThanhVien thanhVien) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        ContentValues values=new ContentValues();
        String[] params=new String[]{thanhVien.getHoTen()};
        values.put("matKhau",thanhVien.getMatKhau());
        database.update("ThanhVien",values,"hoTen = ?",params);

    }

    @Override
    public void delete(String thanhVienten) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();

        String[] params=new String[]{thanhVienten};
        database.delete("ThanhVien","hoTen = ?",params);

    }
    @Override
    public Boolean login(String userName, String password) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        String sql = "SELECT * FROM ThanhVien WHERE hoTen = ? AND matKhau = ?";
        Cursor cursor=database.rawQuery(sql,new String[]{userName,password});
        int count=cursor.getCount();
        cursor.close();
        return count>0;
    }
    public List<ThanhVien> getTenThanhVien() {
        List<ThanhVien> list=new ArrayList<>();
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM ThanhVien",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String maTV=cursor.getString(0);
            String ten=cursor.getString(1);
            String namSinh=cursor.getString(2);

            String mk=cursor.getString(3);
            ThanhVien thanhVien=new ThanhVien(maTV,ten,namSinh,mk);
            list.add(thanhVien);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public Boolean register(String userName) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        String sql = "SELECT * FROM ThanhVien WHERE hoTen = ? ";
        Cursor cursor=database.rawQuery(sql,new String[]{userName});
        int count=cursor.getCount();
        cursor.close();
        return count>0;
    }
}
