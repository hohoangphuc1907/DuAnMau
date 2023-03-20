package com.example.lamthuasm_duanmau;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lamthuasm_duanmau.Menu.MenuNguoiDung;

public class HomeNguoiDungFragment extends Fragment {
    ImageView imgSach,imgPhieuMuon,imgLoaiSach,imgThanhVien;
    Intent intent;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_home,container,false);
        imgSach=view.findViewById(R.id.imgSach);
        imgLoaiSach=view.findViewById(R.id.imgLoaiSach);
        imgPhieuMuon=view.findViewById(R.id.imgPhieuMuon);
        imgThanhVien=view.findViewById(R.id.imgThanhVien);

        imgSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent=new Intent(getContext(),XemSachActivity.class);
                startActivity(intent);

            }
        });
        imgLoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getContext(),XemLoaiSachActivity.class);
                startActivity(intent);
            }
        });
        imgThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=getActivity().getIntent();
                String hoTen=intent.getStringExtra("taikhoan");

                intent=new Intent(getContext(),XemThanhVienActivity.class);
                intent.putExtra("hoten",hoTen);
                startActivity(intent);
            }
        });
        imgPhieuMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=getActivity().getIntent();
                String hoTen=intent.getStringExtra("taikhoan");
                intent=new Intent(getContext(),PhieuDangKyActivity.class);
                intent.putExtra("hoten",hoTen);
                startActivity(intent);
            }
        });

        return view;

    }

}
