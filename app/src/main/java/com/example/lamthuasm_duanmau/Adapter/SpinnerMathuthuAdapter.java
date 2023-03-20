package com.example.lamthuasm_duanmau.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lamthuasm_duanmau.Model.LoaiSach;
import com.example.lamthuasm_duanmau.Model.ThuThu;
import com.example.lamthuasm_duanmau.R;

import java.util.List;

public class SpinnerMathuthuAdapter extends BaseAdapter {
    private Context context;
    private List<ThuThu> data;

    public SpinnerMathuthuAdapter(Context context, List<ThuThu> data) {
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


    @Override
    public View getView(int position, View _view, ViewGroup viewGroup) {
       View view=_view;
       if(_view==null){
           view=View.inflate(viewGroup.getContext(), R.layout.tensach_item_spinner,null);

       }
        TextView TenSach=(TextView) view.findViewById(R.id.textViewSpinnerTenSach);
        ThuThu thuthu= (ThuThu) getItem(position);
        TenSach.setText(thuthu.getMaTT());




        return view;
    }
}
