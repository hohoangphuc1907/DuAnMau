package com.example.lamthuasm_duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lamthuasm_duanmau.Model.LoaiSach;
import com.example.lamthuasm_duanmau.Model.Sach;
import com.example.lamthuasm_duanmau.database.Database;

import java.util.ArrayList;
import java.util.List;

public class SachDao implements ISach{
    Database mydatabase;
    Context context;
    public SachDao(Context context){
        mydatabase=new Database(context);
    }


    @Override
    public List<Sach> get() {
        List<Sach> list=new ArrayList<>();
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM Sach",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String maSach=cursor.getString(0);
            String tenSach=cursor.getString(1);
            String giaThue=cursor.getString(2);
            String maloai=cursor.getString(3);
            int soLuong=cursor.getInt(4);
            Sach sach=new Sach(maSach,tenSach,giaThue,maloai,soLuong);
            list.add(sach);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    @Override
    public Sach get(String id) {
        List<Sach> list=new ArrayList<>();
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM Sach WHERE maSach =?",new String[]{id});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String maSach=cursor.getString(0);
            String tenSach=cursor.getString(1);
            String giaThue=cursor.getString(2);
            String maloai=cursor.getString(3);
            int soLuong=cursor.getInt(4);
            Sach sach=new Sach(maSach,tenSach,giaThue,maloai,soLuong);
            list.add(sach);
            cursor.moveToNext();
        }
        cursor.close();
        return list.get(0);
    }

    public List<Sach> getSpinner(String id) {
        List<Sach> list=new ArrayList<>();
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM Sach WHERE maSach =?",new String[]{id});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String maSach=cursor.getString(0);
            String tenSach=cursor.getString(1);
            String giaThue=cursor.getString(2);
            String maloai=cursor.getString(3);
            int soLuong=cursor.getInt(4);
            Sach sach=new Sach(maSach,tenSach,giaThue,maloai,soLuong);
            list.add(sach);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    @Override
    public void insert(Sach sach) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put("maSach",sach.getMaSach());
        values.put("tenSach",sach.getTenSach());
        values.put("giaThue",sach.getGiaThue());
        values.put("maLoai",sach.getMaLoai());
        values.put("soLuong",sach.getSoLuong());



        database.insert("Sach",null,values);

    }

    @Override
    public void update(Sach sach) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        ContentValues values=new ContentValues();
        String[] params=new String[]{sach.getMaSach()};
        values.put("tenSach",sach.getTenSach());
        values.put("giaThue",sach.getGiaThue());
        values.put("maLoai",sach.getMaLoai());
        values.put("soLuong",sach.getSoLuong());
        database.update("Sach",values,"maSach = ?",params);

    }

    @Override
    public void delete(String sachten) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();

        String[] params=new String[]{sachten};
        database.delete("Sach","tenSach = ?",params);

    }

    public List<Sach> getTenSach() {
        List<Sach> list=new ArrayList<>();
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM Sach",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String maSach=cursor.getString(0);
            String tenSach=cursor.getString(1);
            String giaThue=cursor.getString(2);
            String maloai=cursor.getString(3);
            int soLuong=cursor.getInt(4);
            Sach sach=new Sach(maSach,tenSach,giaThue,maloai,soLuong);
            list.add(sach);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }


}
