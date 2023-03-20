package com.example.lamthuasm_duanmau;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.lamthuasm_duanmau.Adapter.LoaiSachAdapter;
import com.example.lamthuasm_duanmau.Adapter.XemLoaiSachAdapter;
import com.example.lamthuasm_duanmau.DAO.LoaiSachDao;
import com.example.lamthuasm_duanmau.Model.LoaiSach;

import java.util.List;

public class XemLoaiSachActivity extends AppCompatActivity {
    List<LoaiSach> list;
    ListView lvLoaiSach;
    XemLoaiSachAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_loai_sach);
        lvLoaiSach=findViewById(R.id.lvLoaiSach);

        list=new LoaiSachDao(this).get();
        adapter=new XemLoaiSachAdapter(this,list);
        lvLoaiSach.setAdapter(adapter);
    }
}