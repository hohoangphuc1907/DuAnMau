package com.example.lamthuasm_duanmau;


import static java.time.LocalDate.now;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.lamthuasm_duanmau.Adapter.DangKyPhieuMuonAdapter;
import com.example.lamthuasm_duanmau.Adapter.SpinnerTenSachAdapter;
import com.example.lamthuasm_duanmau.Adapter.SpinnerThanhVienAdapter;
import com.example.lamthuasm_duanmau.DAO.PhieuMuonDAO;
import com.example.lamthuasm_duanmau.DAO.SachDao;
import com.example.lamthuasm_duanmau.DAO.ThanhVienDao;
import com.example.lamthuasm_duanmau.Model.PhieuMuon;
import com.example.lamthuasm_duanmau.Model.Sach;
import com.example.lamthuasm_duanmau.Model.ThanhVien;

import java.sql.Date;
import java.util.List;
import java.util.Random;

public class PhieuDangKyActivity extends AppCompatActivity {
    List<PhieuMuon> list;
    ListView lvDangKy;
    DangKyPhieuMuonAdapter adapter;
    ImageView imgThem;

    String selectedLop = null;
    String selectedTenThanhVien = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phieu_dang_ky);
        lvDangKy=findViewById(R.id.lvdangky);
        imgThem=findViewById(R.id.imgThemPhieuMuon);

        list=new PhieuMuonDAO(PhieuDangKyActivity.this).getDS();
        adapter=new DangKyPhieuMuonAdapter(PhieuDangKyActivity.this,list);
        lvDangKy.setAdapter(adapter);

        imgThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogThemPhieuMuon();
            }
        });

    }




    public void dialogThemPhieuMuon() {
        Dialog dialog = new Dialog(PhieuDangKyActivity.this);
        dialog.setContentView(R.layout.dialog_dangkyphieumuon);
        dialog.setCanceledOnTouchOutside(false);
        Spinner spinnerThanhVienMuon=(Spinner) dialog.findViewById(R.id.spinnerTenThanhVien);
        Spinner spinnerTenSach=(Spinner) dialog.findViewById(R.id.spinnerTenSach);




        Button button=(Button) dialog.findViewById(R.id.btnThemSach);

        List<Sach> sachList=(new SachDao(this)).getTenSach();
        SpinnerTenSachAdapter tenSachAdapter=new SpinnerTenSachAdapter(this,sachList);
        spinnerTenSach.setAdapter(tenSachAdapter);
        spinnerTenSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Sach tensach = (Sach) tenSachAdapter.getItem(position);
                selectedLop=tensach.getMaSach();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedLop=null;
            }
        });

        Intent intent=getIntent();
        String ten=intent.getStringExtra("hoten");
        List<ThanhVien> thanhVienList=(new ThanhVienDao(this)).getDuLieuThanhVien(ten);
        SpinnerThanhVienAdapter spinnerThanhVienAdapter=new SpinnerThanhVienAdapter(this,thanhVienList);
        spinnerThanhVienMuon.setAdapter(spinnerThanhVienAdapter);
        spinnerThanhVienMuon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ThanhVien thanhVien = (ThanhVien) spinnerThanhVienAdapter.getItem(position);
                selectedTenThanhVien=thanhVien.getHoTen();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedLop=null;
            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String ThanhVienMuon=selectedTenThanhVien;
                String tenSach=selectedLop;
                Random random=new Random();
                int number=random.nextInt(61);

                Date ngaymuon= java.sql.Date.valueOf(String.valueOf(now()));
                PhieuMuon phieuMuon=new PhieuMuon(number+"",null,ThanhVienMuon,tenSach,0,ngaymuon,3);
                PhieuMuonDAO dao=new PhieuMuonDAO(PhieuDangKyActivity.this);
                dao.insert(phieuMuon);
                list=new PhieuMuonDAO(PhieuDangKyActivity.this).getDS();
                adapter=new DangKyPhieuMuonAdapter(PhieuDangKyActivity.this,list);
                lvDangKy.setAdapter(adapter);
                dialog.dismiss();
            }
        });

        dialog.show();
    }





}