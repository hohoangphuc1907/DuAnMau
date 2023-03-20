package com.example.lamthuasm_duanmau;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.lamthuasm_duanmau.Adapter.SachAdapter;
import com.example.lamthuasm_duanmau.Adapter.XemSachAdapter;
import com.example.lamthuasm_duanmau.DAO.SachDao;
import com.example.lamthuasm_duanmau.Model.Sach;

import java.util.List;

public class XemSachActivity extends AppCompatActivity {
    List<Sach> list;
    ListView lvXemSach;
    XemSachAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xemsach);
        lvXemSach=findViewById(R.id.lvXemSach);
        list=new SachDao(this).get();
        adapter=new XemSachAdapter(this,list);
        lvXemSach.setAdapter(adapter);

    }
}