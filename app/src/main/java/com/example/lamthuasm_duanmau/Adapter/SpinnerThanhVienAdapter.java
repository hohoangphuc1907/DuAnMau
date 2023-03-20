package com.example.lamthuasm_duanmau.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lamthuasm_duanmau.Model.Sach;
import com.example.lamthuasm_duanmau.Model.ThanhVien;
import com.example.lamthuasm_duanmau.R;

import java.util.List;

public class SpinnerThanhVienAdapter extends BaseAdapter {
    private Context context;
    private List<ThanhVien> data;

    public SpinnerThanhVienAdapter(Context context, List<ThanhVien> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public int getPositon(String tenThanhVien){
        int index = -1;
        for (int i = 0; i < data.size(); i++) {
            ThanhVien thanhVien= data.get(i);
            if (thanhVien.getHoTen().equals(tenThanhVien)){
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public View getView(int position, View _view, ViewGroup viewGroup) {
       View view=_view;
       if(_view==null){
           view=View.inflate(viewGroup.getContext(), R.layout.tenthanhvien_item_spinner,null);

       }
        TextView TenThanhVien=(TextView) view.findViewById(R.id.textViewSpinnerTenThanhVien);
        ThanhVien thanhVien= (ThanhVien) getItem(position);
        TenThanhVien.setText(thanhVien.getHoTen());




        return view;
    }
}
