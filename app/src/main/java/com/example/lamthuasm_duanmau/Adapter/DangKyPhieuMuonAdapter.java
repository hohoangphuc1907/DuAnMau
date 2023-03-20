package com.example.lamthuasm_duanmau.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lamthuasm_duanmau.Model.PhieuMuon;
import com.example.lamthuasm_duanmau.PhieuDangKyActivity;
import com.example.lamthuasm_duanmau.PhieuMuonActivity;
import com.example.lamthuasm_duanmau.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class DangKyPhieuMuonAdapter extends BaseAdapter {
    PhieuDangKyActivity context;
    List<PhieuMuon> phieuMuonList;
    SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
    public DangKyPhieuMuonAdapter(PhieuDangKyActivity context, List<PhieuMuon> phieuMuonList) {
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
            view=View.inflate(viewGroup.getContext(), R.layout.layout_dangkyphieumuon,null);

        }
        PhieuMuon phieuMuon= (PhieuMuon) getItem(position);
        TextView txtTenThanhVien=view.findViewById(R.id.txtTenThanhVien);
        TextView txtTenSachMuon=view.findViewById(R.id.txtTenSachMuon);
        TextView txtNgayMuon=view.findViewById(R.id.txtNgayMuon);


        txtTenThanhVien.setText("Tên TV :"+phieuMuon.getMaTV());
        txtTenSachMuon.setText("Tên Sách :"+phieuMuon.getMaSach());

        txtNgayMuon.setText("Ngày đăng ký :"+sdf.format(phieuMuon.getNgay()));





        return view;
    }
}
