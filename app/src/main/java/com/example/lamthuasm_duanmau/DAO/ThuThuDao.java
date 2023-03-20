package com.example.lamthuasm_duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lamthuasm_duanmau.Model.ThanhVien;
import com.example.lamthuasm_duanmau.Model.ThuThu;
import com.example.lamthuasm_duanmau.database.Database;

import java.util.ArrayList;
import java.util.List;

public class ThuThuDao implements IThuThu{
    Database mydatabase;
    public ThuThuDao(Context context){
        mydatabase=new Database(context);
    }
    @Override
    public void insert(ThuThu thuThu) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put("hoTen",thuThu.getHoTen());
        values.put("matKhau",thuThu.getMatKhau());

        database.insert("ThuThu",null,values);
    }
    public Boolean loginADTT(String userName, String password) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        String sql = "SELECT * FROM ThuThu WHERE hoTen = ? AND matKhau = ?";
        Cursor cursor=database.rawQuery(sql,new String[]{userName,password});
        int count=cursor.getCount();
        cursor.close();
        return count>0;
    }
    public void updateMatKhau(ThuThu thuThu) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        ContentValues values=new ContentValues();
        String[] params=new String[]{thuThu.getHoTen()};
        values.put("matKhau",thuThu.getMatKhau());
        database.update("ThuThu",values,"hoTen = ?",params);

    }
    public List<ThuThu> get() {
        List<ThuThu> list=new ArrayList<>();
        String ten="admin";
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM ThuThu WHERE hoTen NOT LIKE '"+ten+"'",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String maTT=cursor.getString(0);
            String hoten=cursor.getString(1);
            String mk=cursor.getString(2);
            ThuThu thuthu=new ThuThu(maTT,hoten,mk);
            list.add(thuthu);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

}
