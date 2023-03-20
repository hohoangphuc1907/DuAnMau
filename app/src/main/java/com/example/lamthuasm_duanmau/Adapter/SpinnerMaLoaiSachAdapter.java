package com.example.lamthuasm_duanmau.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lamthuasm_duanmau.Model.LoaiSach;
import com.example.lamthuasm_duanmau.Model.Sach;
import com.example.lamthuasm_duanmau.R;

import java.util.List;

public class SpinnerMaLoaiSachAdapter extends BaseAdapter {
    private Context context;
    private List<LoaiSach> data;

    public SpinnerMaLoaiSachAdapter(Context context, List<LoaiSach> data) {
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
    public int getPositon(String tenSach){
        int index = -1;
        for (int i = 0; i < data.size(); i++) {
            LoaiSach sach= data.get(i);
            if (sach.getTenLoai().equals(tenSach)){
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
           view=View.inflate(viewGroup.getContext(), R.layout.tensach_item_spinner,null);

       }
        TextView TenSach=(TextView) view.findViewById(R.id.textViewSpinnerTenSach);
        LoaiSach sach= (LoaiSach) getItem(position);
        TenSach.setText(sach.getMaLoai());




        return view;
    }
}
