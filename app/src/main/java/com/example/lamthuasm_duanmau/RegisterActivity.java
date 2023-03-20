package com.example.lamthuasm_duanmau;


import android.os.Build;
import android.os.Bundle;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lamthuasm_duanmau.DAO.ThanhVienDao;
import com.example.lamthuasm_duanmau.Model.ThanhVien;

import java.util.Calendar;
import java.util.Random;

public class RegisterActivity extends AppCompatActivity {
    Button btnDangKy;
    EditText edtTen,edtNamSinh;
    TextInputEditText Iedt,IedtXacNhan;
    String id="1";
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edtTen=(EditText) findViewById(R.id.editTextTen);
        edtNamSinh=(EditText) findViewById(R.id.editTextNamSinh);
        Iedt=(TextInputEditText) findViewById(R.id.editTextMatKhau);
        IedtXacNhan=(TextInputEditText) findViewById(R.id.editTextMatKhauMoi);
        btnDangKy=(Button) findViewById(R.id.buttonDangNhap);
        txt=findViewById(R.id.txtDangKy);
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean check=new ThanhVienDao(getApplicationContext()).register(edtTen.getText().toString());
                if (Iedt.getText().toString().isEmpty()||IedtXacNhan.getText().toString().isEmpty()||edtTen.getText().toString().isEmpty()||edtNamSinh.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Không được bỏ trống", Toast.LENGTH_SHORT).show();

                }else if (check==true){
                    Toast.makeText(getApplicationContext(), "Tài khoản đã tồn tại", Toast.LENGTH_LONG).show();
                }else if (Iedt.getText().toString().length()<=2){
                    Toast.makeText(getApplicationContext(), "Mật khẩu quá ngắn", Toast.LENGTH_LONG).show();
                    Iedt.setError("Mật khẩu quá ngắn");

                } else{
                    int namsinh=Integer.parseInt(edtNamSinh.getText().toString());
                    if (namsinh>=year||(year-namsinh)<=5){
                        Toast.makeText(getApplicationContext(), "Chưa đủ tuổi hoặc nhập sai năm sinh", Toast.LENGTH_SHORT).show();
                    }else if (Iedt.getText().toString().equals(IedtXacNhan.getText().toString())){
                        Random random=new Random();
                        int number=random.nextInt(61);
                        ThanhVien thanhVien=new ThanhVien(number+"",edtTen.getText().toString(),edtNamSinh.getText().toString(),Iedt.getText().toString());
                        ThanhVienDao dao=new ThanhVienDao(getApplicationContext());
                        dao.insert(thanhVien);
                        Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(), "Nhập sai mật khẩu cũ", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });


    }
}