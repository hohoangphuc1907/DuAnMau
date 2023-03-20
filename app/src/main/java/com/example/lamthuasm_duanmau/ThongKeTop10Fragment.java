package com.example.lamthuasm_duanmau;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lamthuasm_duanmau.Adapter.ThongKeTop10Adapter;
import com.example.lamthuasm_duanmau.DAO.ThongKeDao;
import com.example.lamthuasm_duanmau.Model.Top;

import java.util.List;

public class ThongKeTop10Fragment extends Fragment {
    List<Top> list;
    ThongKeTop10Adapter adapter;
    ListView lv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.thongketop10_fragment,container,false);
        lv=view.findViewById(R.id.lvThongKeTop10);
        list=new ThongKeDao(getContext()).getTop();
        adapter=new ThongKeTop10Adapter(getContext(),list);
        lv.setAdapter(adapter);

        return view;

    }
}
