package com.example.lamthuasm_duanmau.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lamthuasm_duanmau.Model.Sach;
import com.example.lamthuasm_duanmau.Model.Top;
import com.example.lamthuasm_duanmau.database.Database;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDao implements IThongKe{
    Database mydatabase;
    Context context;
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    public ThongKeDao(Context context){
        this.context=context;
        mydatabase=new Database(context);
    }


    @Override
    public List<Top> getTop() {
        List<Top> list=new ArrayList<>();
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT maSach,count(maSach) as soLuong FROM PhieuMuon WHERE traSach=1 GROUP BY maSach ORDER BY soLuong DESC LIMIT 10",null);
        SachDao sachDao=new SachDao(context);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String maSach=cursor.getString(cursor.getColumnIndex("maSach"));
            Sach sach=sachDao.get(maSach);
            String tenSach=sach.getTenSach();
            String soLuong=cursor.getString(cursor.getColumnIndex("soLuong"));
            Top top=new Top(tenSach,soLuong);
            list.add(top);
            cursor.moveToNext();

        }
        cursor.close();
        return list;
    }

    @Override
    public int getDoanhThu(String tuNgay, String denNgay) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        List<Integer> list=new ArrayList<Integer>();
        Cursor cursor=database.rawQuery("SELECT SUM(tienThue) as doanhThu FROM PhieuMuon WHERE ngay BETWEEN ? AND ? AND traSach=1",new String[]{tuNgay,denNgay});
       while(cursor.moveToNext()){
           try{
               list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("doanhThu"))));
           }catch (Exception e){
               list.add(0);
           }
       }
        return list.get(0);
    }
}
