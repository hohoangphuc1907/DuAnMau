package com.example.lamthuasm_duanmau.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lamthuasm_duanmau.LoaiSachActivity;
import com.example.lamthuasm_duanmau.Model.LoaiSach;
import com.example.lamthuasm_duanmau.R;

import java.util.List;

public class LoaiSachAdapter extends BaseAdapter {
    LoaiSachActivity context;
    List<LoaiSach> loaiSachList;

    public LoaiSachAdapter(LoaiSachActivity context, List<LoaiSach> loaiSachList) {
        this.context = context;
        this.loaiSachList = loaiSachList;
    }

    @Override
    public int getCount() {
        return loaiSachList.size();
    }

    @Override
    public Object getItem(int position) {
        return loaiSachList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view=convertView;
        if (convertView==null){
            view=View.inflate(viewGroup.getContext(), R.layout.layout_loaisach,null);

        }
        LoaiSach loaiSach= (LoaiSach) getItem(position);
        TextView txtMaLoai=view.findViewById(R.id.txtMaLoaiSach);
        TextView txtTenLoai=view.findViewById(R.id.txtTenLoaiSach);
        ImageView imgEdit=(ImageView) view.findViewById(R.id.imgSuaLoaiSach);
        ImageView imgDelete=(ImageView) view.findViewById(R.id.imgXoaLoaiSach);

        txtMaLoai.setText("Mã loại sách:"+loaiSach.getMaLoai());
        txtTenLoai.setText("Tên loại sách:"+loaiSach.getTenLoai());
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.dialogSuaTenLoaiSach(loaiSach.getMaLoai());
            }
        });
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            context.DiaLogXoaMaLoaiSach(loaiSach.getTenLoai());
            }
        });

        return view;
    }
}
