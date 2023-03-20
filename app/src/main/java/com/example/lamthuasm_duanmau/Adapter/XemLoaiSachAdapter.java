package com.example.lamthuasm_duanmau.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lamthuasm_duanmau.LoaiSachActivity;
import com.example.lamthuasm_duanmau.Model.LoaiSach;
import com.example.lamthuasm_duanmau.R;
import com.example.lamthuasm_duanmau.XemLoaiSachActivity;

import java.util.List;

public class XemLoaiSachAdapter extends BaseAdapter {
    XemLoaiSachActivity context;
    List<LoaiSach> loaiSachList;

    public XemLoaiSachAdapter(XemLoaiSachActivity context, List<LoaiSach> loaiSachList) {
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
            view=View.inflate(viewGroup.getContext(), R.layout.layout_xemloaisach,null);

        }
        LoaiSach loaiSach= (LoaiSach) getItem(position);
        TextView txtMaLoai=view.findViewById(R.id.txtMaLoaiSach);
        TextView txtTenLoai=view.findViewById(R.id.txtTenLoaiSach);


        txtMaLoai.setText("Mã loại sách"+loaiSach.getMaLoai());
        txtTenLoai.setText("Tên loại sách"+loaiSach.getTenLoai());


        return view;
    }
}
