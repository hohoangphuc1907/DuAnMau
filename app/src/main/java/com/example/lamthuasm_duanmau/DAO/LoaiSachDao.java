package com.example.lamthuasm_duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lamthuasm_duanmau.Model.LoaiSach;
import com.example.lamthuasm_duanmau.Model.ThanhVien;
import com.example.lamthuasm_duanmau.database.Database;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachDao implements ILoaiSach{
    Database mydatabase;
    public LoaiSachDao(Context context){
        mydatabase=new Database(context);
    }


    @Override
    public List<LoaiSach> get() {
        List<LoaiSach> list=new ArrayList<>();
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM LoaiSach",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String maLoai=cursor.getString(0);
            String TenLoai=cursor.getString(1);
            LoaiSach loaiSach=new LoaiSach(maLoai,TenLoai);
            list.add(loaiSach);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    @Override
    public void insert(LoaiSach loaiSach) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put("maLoai",loaiSach.getMaLoai());
        values.put("TenLoai",loaiSach.getTenLoai());
        database.insert("LoaiSach",null,values);

    }

    @Override
    public void update(LoaiSach loaiSach) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        ContentValues values=new ContentValues();
        String[] params=new String[]{loaiSach.getMaLoai()};
        values.put("TenLoai",loaiSach.getTenLoai());
        database.update("LoaiSach",values,"maLoai = ?",params);

    }

    @Override
    public void delete(String loaisachten) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();

        String[] params=new String[]{loaisachten};
        database.delete("LoaiSach","TenLoai = ?",params);

    }
    public Boolean tenloaisach(String tenloaisach) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        String sql = "SELECT * FROM LoaiSach WHERE TenLoai = ? ";
        Cursor cursor=database.rawQuery(sql,new String[]{tenloaisach});
        int count=cursor.getCount();
        cursor.close();
        return count>0;
    }
    public Boolean maloaisach(String maloaisach) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        String sql = "SELECT * FROM LoaiSach WHERE maLoai = ? ";
        Cursor cursor=database.rawQuery(sql,new String[]{maloaisach});
        int count=cursor.getCount();
        cursor.close();
        return count>0;
    }
}
