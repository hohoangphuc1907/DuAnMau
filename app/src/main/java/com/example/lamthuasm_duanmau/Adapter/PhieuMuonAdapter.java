package com.example.lamthuasm_duanmau.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lamthuasm_duanmau.DAO.SachDao;
import com.example.lamthuasm_duanmau.Model.PhieuMuon;
import com.example.lamthuasm_duanmau.Model.Sach;
import com.example.lamthuasm_duanmau.PhieuMuonActivity;
import com.example.lamthuasm_duanmau.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class PhieuMuonAdapter extends BaseAdapter {
    PhieuMuonActivity context;
    List<PhieuMuon> phieuMuonList;
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    public PhieuMuonAdapter(PhieuMuonActivity context, List<PhieuMuon> phieuMuonList) {
        this.context = context;
        this.phieuMuonList = phieuMuonList;
    }

    @Override
    public int getCount() {
        return phieuMuonList.size();
    }

    @Override
    public Object getItem(int position) {
        return phieuMuonList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view=convertView;
        if (convertView==null){
            view=View.inflate(viewGroup.getContext(), R.layout.layout_phieumuon,null);

        }
        PhieuMuon phieuMuon= (PhieuMuon) getItem(position);
        TextView txtMaPM=view.findViewById(R.id.txtMaPhieuMuon);
        TextView txtTenThanhVien=view.findViewById(R.id.txtTenThanhVien);
        TextView txtTenSachMuon=view.findViewById(R.id.txtTenSachMuon);
        TextView txtNgayMuon=view.findViewById(R.id.txtNgayMuon);
        TextView txtTienThue=view.findViewById(R.id.txtTienThue);
        TextView txtTrangThai=view.findViewById(R.id.txtTrangThai);
        TextView txtmathuthu=view.findViewById(R.id.txtmathuthu);
       // Intent intent=context.getIntent();
       // int vaitro=intent.getIntExtra("vaitro",2);
       // if (vaitro==0){
      //      txtNgayMuon.setVisibility(View.GONE);
      //  }

        ImageView imgEdit=(ImageView) view.findViewById(R.id.imgSuaPhieuMuon);
        ImageView imgDelete=(ImageView) view.findViewById(R.id.imgXoaPhieuMuon);

        txtMaPM.setText("Mã PM :"+phieuMuon.getMaPM());
        txtTenThanhVien.setText("Tên TV :"+phieuMuon.getMaTV());
        txtTenSachMuon.setText("Mã Sách :"+phieuMuon.getMaSach());
        txtTienThue.setText("Gíá Thuê :"+phieuMuon.getTienThue());
        txtmathuthu.setText("Mã thủ thư :"+phieuMuon.getMaTT());
        txtNgayMuon.setText("Ngày Mượn :"+sdf.format(phieuMuon.getNgay()));
        if (phieuMuon.getTraSach()==1){
            txtTrangThai.setText("Đã trả");
        }else if(phieuMuon.getTraSach()==0){
            txtTrangThai.setText("Chưa trả");
        }else{
            txtTrangThai.setText("Chưa trả");
        }
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.dialogSuaPhieuMuon(phieuMuon.getMaPM());
            }
        });
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DiaLogXoaPhieuMuon(phieuMuon.getMaPM());
            }
        });

        return view;
    }
}
