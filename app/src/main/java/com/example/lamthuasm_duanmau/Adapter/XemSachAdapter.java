package com.example.lamthuasm_duanmau.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lamthuasm_duanmau.Model.Sach;
import com.example.lamthuasm_duanmau.R;

import java.util.List;

public class XemSachAdapter extends BaseAdapter {
    Context context;
    List<Sach> sachList;

    public XemSachAdapter(Context context, List<Sach> sachList) {
        this.context = context;
        this.sachList = sachList;
    }

    @Override
    public int getCount() {
        return sachList.size();
    }

    @Override
    public Object getItem(int position) {
        return sachList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view=convertView;
        if (convertView==null){
            view=View.inflate(viewGroup.getContext(), R.layout.layout_xemsach,null);

        }
        Sach sach= (Sach) getItem(position);
        TextView txtMaSach=view.findViewById(R.id.txtMaSach);
        TextView txtTenSach=view.findViewById(R.id.txtTenSach);
        TextView txtGiaThue=view.findViewById(R.id.txtGiaThue);
        TextView txtmaLoai=view.findViewById(R.id.txtMaLoai);
        TextView txtsoLuong=view.findViewById(R.id.txtsoLuong1CuonSach);


        txtMaSach.setText("Mã sách :"+sach.getMaSach());
        txtTenSach.setText("Tên sách :"+sach.getTenSach());
        txtGiaThue.setText("Tiền Thuê :"+sach.getGiaThue());
        txtmaLoai.setText("Mã loại sách :"+sach.getMaLoai());
        txtsoLuong.setText("Số lượng :"+sach.getSoLuong());

        return view;
    }
}
