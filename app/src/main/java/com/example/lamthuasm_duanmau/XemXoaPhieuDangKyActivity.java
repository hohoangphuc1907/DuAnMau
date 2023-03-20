package com.example.lamthuasm_duanmau;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.lamthuasm_duanmau.Adapter.PhieuMuonAdapter;
import com.example.lamthuasm_duanmau.Adapter.XemPhieuMuonAdapter;
import com.example.lamthuasm_duanmau.DAO.PhieuMuonDAO;
import com.example.lamthuasm_duanmau.Model.PhieuMuon;

import java.util.List;

public class XemXoaPhieuDangKyActivity extends AppCompatActivity {
    List<PhieuMuon> list;
    ListView lvDangKy;
    XemPhieuMuonAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xemphieu_dang_ky);
        lvDangKy=findViewById(R.id.lvxemdangky);

        list=new PhieuMuonDAO(XemXoaPhieuDangKyActivity.this).getDS();
        adapter=new XemPhieuMuonAdapter(XemXoaPhieuDangKyActivity.this,list);
        lvDangKy.setAdapter(adapter);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomnagivation);
        bottomNavigationView.setSelectedItemId(R.id.bottomdanhsach);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.bottomphieumuon:
                        startActivity(new Intent(getApplicationContext(),PhieuMuonActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.bottomdanhsach:
                        return true;

                }

                return false;
            }
        });

    }
    public void DiaLogXoaSach(String ten){
        AlertDialog.Builder dialogXoa=new AlertDialog.Builder(XemXoaPhieuDangKyActivity.this);
        dialogXoa.setMessage("Bạn có muốn xóa này không");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PhieuMuonDAO dao=new PhieuMuonDAO(XemXoaPhieuDangKyActivity.this);
                dao.delete(ten);
                list=new PhieuMuonDAO(XemXoaPhieuDangKyActivity.this).getDS();
                adapter=new XemPhieuMuonAdapter(XemXoaPhieuDangKyActivity.this,list);
                lvDangKy.setAdapter(adapter);


            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialogXoa.show();

    }

}