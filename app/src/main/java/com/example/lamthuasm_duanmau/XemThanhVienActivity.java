package com.example.lamthuasm_duanmau;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lamthuasm_duanmau.Adapter.SachAdapter;
import com.example.lamthuasm_duanmau.Adapter.XemThanhVienAdapter;
import com.example.lamthuasm_duanmau.DAO.SachDao;
import com.example.lamthuasm_duanmau.DAO.ThanhVienDao;
import com.example.lamthuasm_duanmau.Model.ThanhVien;

import java.util.List;

public class XemThanhVienActivity extends AppCompatActivity {
    List<ThanhVien> list;
    ListView lvXemThanhVien;
    XemThanhVienAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_thanh_vien);
        Intent intent=getIntent();
        String hoTen=intent.getStringExtra("hoten");
        lvXemThanhVien=findViewById(R.id.lvXemThanhVien);
        list=new ThanhVienDao(this).getDuLieuThanhVien(hoTen);
        adapter=new XemThanhVienAdapter(this,list);
        lvXemThanhVien.setAdapter(adapter);
    }


    public void dialogSuaPhieuMuon(String maTV) {
        Dialog dialog = new Dialog(XemThanhVienActivity.this);
        dialog.setContentView(R.layout.dialog_suathanhvien);
        dialog.setCanceledOnTouchOutside(false);
        EditText editTextTen=(EditText) dialog.findViewById(R.id.edtTen);
        EditText editTextNamSinh=(EditText) dialog.findViewById(R.id.edtNamSinh);
        Button button=(Button) dialog.findViewById(R.id.btnSuaPhieuMuon);



        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogSua=new AlertDialog.Builder(XemThanhVienActivity.this);
                dialogSua.setMessage("Bạn chắc chắn muốn cập nhật hay không ?");
                dialogSua.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String ten=editTextTen.getText().toString();
                        String NamSinh=editTextNamSinh.getText().toString();
                        ThanhVien thanhVien=new ThanhVien(maTV,ten,NamSinh,null);
                        ThanhVienDao dao=new ThanhVienDao(getApplicationContext());
                        dao.update(thanhVien);

                        Intent intent=getIntent();
                        String hoTen=intent.getStringExtra("hoten");

                        list=new ThanhVienDao(XemThanhVienActivity.this).getDuLieuThanhVien(hoTen);
                        adapter=new XemThanhVienAdapter(XemThanhVienActivity.this,list);
                        lvXemThanhVien.setAdapter(adapter);
                        dialog.dismiss();
                        intent=new Intent(XemThanhVienActivity.this,LoginActivity.class);
                        startActivity(intent);
                        AlertDialog.Builder dialogThongBao=new AlertDialog.Builder(getApplicationContext());
                        Toast.makeText(getApplicationContext(), "Đăng nhập bằng tên tài khoản bạn mới sửa", Toast.LENGTH_LONG).show();
                    }
                });
                dialogSua.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                dialogSua.show();
            }
        });

        dialog.show();
    }


}