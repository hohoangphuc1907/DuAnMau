package com.example.lamthuasm_duanmau.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lamthuasm_duanmau.DAO.PhieuMuonDAO;
import com.example.lamthuasm_duanmau.Model.Sach;
import com.example.lamthuasm_duanmau.R;
import com.example.lamthuasm_duanmau.SachActivity;

import java.util.List;

public class SachAdapter extends BaseAdapter {
    SachActivity context;
    List<Sach> sachList;

    public SachAdapter(SachActivity context, List<Sach> sachList) {
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
            view=View.inflate(viewGroup.getContext(), R.layout.layout_sach,null);

        }
        Sach sach= (Sach) getItem(position);
        TextView txtMaSach=view.findViewById(R.id.txtMaSach);
        TextView txtTenSach=view.findViewById(R.id.txtTenSach);
        TextView txtGiaThue=view.findViewById(R.id.txtGiaThue);
        TextView txtmaLoai=view.findViewById(R.id.txtMaLoai);
        TextView txtsoLuong=view.findViewById(R.id.txtsoLuong1CuonSach);

        ImageView imgEdit=(ImageView) view.findViewById(R.id.imgSuaSach);
        ImageView imgDelete=(ImageView) view.findViewById(R.id.imgXoaSach);

        txtMaSach.setText("Mã sách :"+sach.getMaSach());
        txtTenSach.setText("Tên sách :"+sach.getTenSach());
        txtGiaThue.setText("Tiền Thuê :"+sach.getGiaThue());
        txtmaLoai.setText("Mã loại sách :"+sach.getMaLoai());
        txtsoLuong.setText("Số lượng :"+sach.getSoLuong()+"");
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.dialogSuaSach(sach.getMaSach());
            }
        });
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean check=new PhieuMuonDAO(context).kiemtraxoaphieumuon(sach.getMaSach());
                if (check==true){
                    Toast.makeText(context.getApplicationContext(), "Cuốn sách đang có trong phiếu mượn", Toast.LENGTH_SHORT).show();
                }else{
                    context.DiaLogXoaSach(sach.getTenSach());
                }

            }
        });

        return view;
    }
}
