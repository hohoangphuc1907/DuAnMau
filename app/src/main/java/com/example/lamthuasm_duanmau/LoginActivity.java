package com.example.lamthuasm_duanmau;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lamthuasm_duanmau.DAO.ThanhVienDao;
import com.example.lamthuasm_duanmau.DAO.ThuThuDao;
import com.example.lamthuasm_duanmau.Menu.MenuAdmin;
import com.example.lamthuasm_duanmau.Menu.MenuNguoiDung;
import com.example.lamthuasm_duanmau.Menu.MenuThuThu;

public class LoginActivity extends AppCompatActivity {
    Button btnDangNhap;
    EditText edtTen;
    TextInputEditText Iedt;
    CheckBox chkRemember;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtTen=(EditText) findViewById(R.id.editTextTen);
        Iedt=(TextInputEditText) findViewById(R.id.editTextMatKhau);
        btnDangNhap=(Button) findViewById(R.id.buttonDangNhap);
        chkRemember=(CheckBox) findViewById(R.id.chkRemember);
        txt=findViewById(R.id.txtDangKy);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);

                startActivity(intent);
            }
        });
        //kiem tra login,neu da dang nhap thi chya qua mainactivity
        //nguoclaichodangnhap
        checkloginStatus();
        checkloginThanhVien();
        checkloginThuthu();

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=edtTen.getText().toString();
                String password=Iedt.getText().toString();

                Boolean check=(new ThanhVienDao(LoginActivity.this)).login(username,password);
                Boolean checkADTT=(new ThuThuDao(LoginActivity.this)).loginADTT(username,password);


                if(check==true){
                    if(chkRemember.isChecked()==true){
                        SharedPreferences.Editor editor=getSharedPreferences("login_statustv",MODE_PRIVATE).edit();
                        editor.putBoolean("isLoggedIntv",true);
                        editor.putString("taikhoan",username);
                        editor.putString("matkhau",password);
                        editor.apply();
                            Intent intent=new Intent(LoginActivity.this, MenuNguoiDung.class);
                            intent.putExtra("taikhoan",username);
                            intent.putExtra("matkhau",password);

                            startActivity(intent);

                        return;
                    }else {
                            Intent intent=new Intent(LoginActivity.this,MenuNguoiDung.class);
                            intent.putExtra("taikhoan",username);
                            intent.putExtra("matkhau",password);
                            startActivity(intent);
                        return;
                    }
                }else if(checkADTT==true){
                    if(chkRemember.isChecked()==true){

                        if (username.equals("admin")){
                            SharedPreferences.Editor editor=getSharedPreferences("login_status",MODE_PRIVATE).edit();
                            editor.putBoolean("isLoggedIn",true);
                            editor.apply();
                            Intent intent=new Intent(LoginActivity.this, MenuAdmin.class);
                            intent.putExtra("taikhoan",username);
                            intent.putExtra("matkhau",password);
                            startActivity(intent);

                        }else {
                            SharedPreferences.Editor editor=getSharedPreferences("login_statustt",MODE_PRIVATE).edit();
                            editor.putBoolean("isLoggedIntt",true);
                            editor.apply();
                            Intent intent=new Intent(LoginActivity.this, MenuThuThu.class);
                            intent.putExtra("taikhoan",username);
                            intent.putExtra("matkhau",password);
                            startActivity(intent);

                        }
                        return;
                    }else {
                        if (username.equals("admin")){
                            Intent intent=new Intent(LoginActivity.this, MenuAdmin.class);
                            intent.putExtra("taikhoan",username);
                            intent.putExtra("vaitro",0);
                            intent.putExtra("matkhau",password);
                            startActivity(intent);

                        }else {
                            Intent intent=new Intent(LoginActivity.this, MenuThuThu.class);
                            intent.putExtra("taikhoan",username);
                            intent.putExtra("matkhau",password);
                            startActivity(intent);

                        }
                    }


                } else{
                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void checkloginStatus(){
        SharedPreferences editor=getSharedPreferences("login_status",MODE_PRIVATE);
        boolean isLoggedIn = editor.getBoolean("isLoggedIn",false);
        if(isLoggedIn==true){
            startActivity(new Intent(getApplicationContext(),MenuAdmin.class));
            finish();
            return;
        }

    }

    private void checkloginThuthu(){
        SharedPreferences editor=getSharedPreferences("login_statustt",MODE_PRIVATE);
        boolean isLoggedIn = editor.getBoolean("isLoggedIntt",false);
        if(isLoggedIn==true){
            startActivity(new Intent(getApplicationContext(),MenuThuThu.class));
            finish();
            return;
        }

    }
    private void checkloginThanhVien(){
        SharedPreferences editor=getSharedPreferences("login_statustv",MODE_PRIVATE);
        boolean isLoggedIn = editor.getBoolean("isLoggedIntv",false);
        String username=editor.getString("taikhoan",null);
        String password=editor.getString("matkhau",null);
        if(isLoggedIn==true){
            Intent intent=new Intent(LoginActivity.this, MenuNguoiDung.class);
            intent.putExtra("taikhoan",username);
            intent.putExtra("matkhau",password);
            startActivity(intent);
            finish();
            return;
        }

    }
}