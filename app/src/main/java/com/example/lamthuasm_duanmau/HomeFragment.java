package com.example.lamthuasm_duanmau;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class HomeFragment extends Fragment {
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

                intent=new Intent(getContext(),SachActivity.class);
                startActivity(intent);

            }
        });
        imgLoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getContext(),LoaiSachActivity.class);
                startActivity(intent);
            }
        });
        imgThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getContext(),ThanhVienActivity.class);
                startActivity(intent);
            }
        });
        imgPhieuMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getContext(),PhieuMuonActivity.class);

                startActivity(intent);
            }
        });

        return view;

    }
}
