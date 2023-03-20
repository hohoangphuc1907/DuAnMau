package com.example.lamthuasm_duanmau;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.lamthuasm_duanmau.Adapter.PhieuMuonAdapter;
import com.example.lamthuasm_duanmau.Adapter.ThanhVienAdapter;
import com.example.lamthuasm_duanmau.DAO.PhieuMuonDAO;
import com.example.lamthuasm_duanmau.DAO.ThanhVienDao;
import com.example.lamthuasm_duanmau.Model.ThanhVien;

import java.util.List;

public class ThanhVienActivity extends AppCompatActivity {
    List<ThanhVien> list;
    ListView lvThanhVien;
    ThanhVienAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_vien);
        lvThanhVien=findViewById(R.id.lvThanhVien);
        list=new ThanhVienDao(this).get();
        adapter=new ThanhVienAdapter(this,list);
        lvThanhVien.setAdapter(adapter);
    }


    public void DiaLogXoaThanhVien(String ten){
        AlertDialog.Builder dialogXoa=new AlertDialog.Builder(ThanhVienActivity.this);
        dialogXoa.setMessage("Bạn có muốn xóa sinh viên "+ten+" này không");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ThanhVienDao dao=new ThanhVienDao(ThanhVienActivity.this);
                dao.delete(ten);
                list=new ThanhVienDao(ThanhVienActivity.this).get();
                adapter=new ThanhVienAdapter(ThanhVienActivity.this,list);
                lvThanhVien.setAdapter(adapter);

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