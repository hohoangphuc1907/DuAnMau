package com.example.lamthuasm_duanmau;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.lamthuasm_duanmau.DAO.ThuThuDao;
import com.example.lamthuasm_duanmau.Model.ThuThu;

public class DoiMatKhauADTTFragment extends Fragment {
    TextInputEditText IedtCu,IedtMoi,IedtXacNhan;
    Button btnLuu;
    TextInputLayout layoutIEDT;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_doimatkhau,container,false);
        IedtCu=view.findViewById(R.id.editTextMatKhauCu);
        IedtMoi=view.findViewById(R.id.editTextMatKhauMoi);
        layoutIEDT=view.findViewById(R.id.textinputmkmoi);
        IedtXacNhan=view.findViewById(R.id.editTextMatKhauxacnhan);
        btnLuu=view.findViewById(R.id.buttonLuu);
        Intent intent=getActivity().getIntent();
        String taikhoan=intent.getStringExtra("taikhoan");
        String matkhau=intent.getStringExtra("matkhau");
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
                    if (IedtCu.getText().toString().equals(matkhau)){
                        if (IedtMoi.getText().toString().equals(IedtXacNhan.getText().toString())){
                            ThuThuDao dao=new ThuThuDao(getContext());
                            ThuThu thuThu=new ThuThu(null,taikhoan,IedtMoi.getText().toString());
                            dao.updateMatKhau(thuThu);
                            Toast.makeText(getContext(), "Đã thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(), "Xác nhận lại mật khẩu chưa đúng", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(getContext(), "Nhập sai mật khẩu cũ", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });



        return view;
    }

}
