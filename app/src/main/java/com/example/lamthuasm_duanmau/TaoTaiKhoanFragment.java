package com.example.lamthuasm_duanmau;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.lamthuasm_duanmau.DAO.ThanhVienDao;
import com.example.lamthuasm_duanmau.DAO.ThuThuDao;
import com.example.lamthuasm_duanmau.Model.ThanhVien;
import com.example.lamthuasm_duanmau.Model.ThuThu;

import java.util.Random;

public class TaoTaiKhoanFragment extends Fragment {
    TextInputEditText IedtCu,IedtMoi,IedtXacNhan;
    Button btnLuu;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_taotaikhoan,container,false);
        IedtCu=view.findViewById(R.id.editTextTenTaiKhoan);
        IedtMoi=view.findViewById(R.id.editTextMatKhau);
        IedtXacNhan=view.findViewById(R.id.editTextXacNhanPass);
        btnLuu=view.findViewById(R.id.buttonLuu);

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (IedtCu.getText().toString().isEmpty()||IedtMoi.getText().toString().isEmpty()||IedtXacNhan.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Không được bỏ trống", Toast.LENGTH_LONG).show();
                    if (IedtCu.getText().toString().isEmpty()){
                        IedtCu.setError("Không được bỏ trống");

                    } else if (IedtMoi.getText().toString().isEmpty()) {

                        IedtMoi.setError("Không được bỏ trống");
                    }else{
                        IedtXacNhan.setError("Không được bỏ trống");
                    }

                }else if (IedtMoi.getText().toString().length()<=2) {
                    Toast.makeText(getContext(), "Mật khẩu quá ngắn", Toast.LENGTH_LONG).show();
                    IedtMoi.setError("Mật khẩu quá ngắn");

                }else{
                    IedtCu.setError(null);
                    IedtMoi.setError(null);
                    IedtXacNhan.setError(null);
                    Random random=new Random();
                    int number=random.nextInt(61);
                    String ten=IedtCu.getText().toString();
                    String pass=IedtMoi.getText().toString();
                    String maTT=String.valueOf(number);
                    ThuThu thuThu=new ThuThu(maTT,ten,pass);
                    ThuThuDao dao=new ThuThuDao(getContext());
                    dao.insert(thuThu);
                }

            }
        });



        return view;
    }

}
