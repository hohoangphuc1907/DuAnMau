package com.example.lamthuasm_duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lamthuasm_duanmau.Model.LoaiSach;
import com.example.lamthuasm_duanmau.Model.PhieuMuon;
import com.example.lamthuasm_duanmau.database.Database;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PhieuMuonDAO implements IPhieuMuon{
    Database mydatabase;
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    public PhieuMuonDAO(Context context){
        mydatabase=new Database(context);
    }
    @Override
    public List<PhieuMuon> get() {

        List<PhieuMuon> list=new ArrayList<>();
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM PhieuMuon WHERE tienThue !=0 ",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String maPM=cursor.getString(0);
            String maTT=cursor.getString(1);
            String maTV=cursor.getString(2);
            String maSach=cursor.getString(3);
            int tienThue=cursor.getInt(4);

            Date ngay = null;
            try {
                ngay = sdf.parse(cursor.getString(5));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            int traSach=cursor.getInt(6);

            PhieuMuon phieuMuon=new PhieuMuon(maPM,maTT,maTV,maSach,tienThue,ngay,traSach);
            list.add(phieuMuon);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<PhieuMuon> getDS() {

        List<PhieuMuon> list=new ArrayList<>();
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM PhieuMuon WHERE tienThue == 0",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String maPM=cursor.getString(0);
            String maTT=cursor.getString(1);
            String maTV=cursor.getString(2);
            String maSach=cursor.getString(3);
            int tienThue=cursor.getInt(4);
            Date ngay= null;
            try {
                ngay = sdf.parse(cursor.getString(5));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int traSach=cursor.getInt(6);

            PhieuMuon phieuMuon=new PhieuMuon(maPM,maTT,maTV,maSach,tienThue,ngay,traSach);
            list.add(phieuMuon);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }




    @Override
    public void insert(PhieuMuon phieuMuon) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put("maPM",phieuMuon.getMaPM());
        values.put("maTT",phieuMuon.getMaTT());
        values.put("maTV",phieuMuon.getMaTV());
        values.put("maSach",phieuMuon.getMaSach());
        values.put("tienThue",phieuMuon.getTienThue());
        values.put("ngay",sdf.format(phieuMuon.getNgay()));
        values.put("traSach",phieuMuon.getTraSach());
        database.insert("PhieuMuon",null,values);

    }
    public void insertDK(PhieuMuon phieuMuon) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put("maSach",phieuMuon.getMaSach());
        values.put("tienThue",phieuMuon.getTienThue());
        values.put("ngay",sdf.format(phieuMuon.getNgay()));
        database.insert("PhieuMuon",null,values);

    }



    @Override
    public void update(PhieuMuon phieuMuon) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        ContentValues values=new ContentValues();
        String[] params=new String[]{phieuMuon.getMaPM()};
        values.put("traSach",phieuMuon.getTraSach());
        database.update("PhieuMuon",values,"maPM = ?",params);

    }

    @Override
    public void delete(String maphieumuon) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();

        String[] params=new String[]{maphieumuon};
        database.delete("PhieuMuon","maPM = ?",params);

    }
    public Boolean kiemtraphieumuon(String tenthanhvien, String masach) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        String sql = "SELECT * FROM PhieuMuon WHERE maTV = ? AND maSach = ? AND traSach=0";
        Cursor cursor=database.rawQuery(sql,new String[]{tenthanhvien,masach});
        int count=cursor.getCount();
        cursor.close();
        return count>0;
    }
    public Boolean kiemtraxoaphieumuon(String masach) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        String sql = "SELECT * FROM PhieuMuon WHERE maSach = ?";
        Cursor cursor=database.rawQuery(sql,new String[]{masach});
        int count=cursor.getCount();
        cursor.close();
        return count>0;
    }

    public int kiemtrasoluong(String masach ) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        String sql = "SELECT * FROM PhieuMuon WHERE maSach = ? AND traSach = 0";
        Cursor cursor=database.rawQuery(sql,new String[]{masach});
        int count = 0;
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            count+=cursor.getCount();
           cursor.moveToNext();
        }

        cursor.close();
        return count;
    }
}
