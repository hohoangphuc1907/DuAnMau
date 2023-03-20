package com.example.lamthuasm_duanmau;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lamthuasm_duanmau.Adapter.SachAdapter;
import com.example.lamthuasm_duanmau.Adapter.SpinnerMaLoaiSachAdapter;
import com.example.lamthuasm_duanmau.Adapter.SpinnerTenSachAdapter;
import com.example.lamthuasm_duanmau.DAO.LoaiSachDao;
import com.example.lamthuasm_duanmau.DAO.SachDao;
import com.example.lamthuasm_duanmau.Model.LoaiSach;
import com.example.lamthuasm_duanmau.Model.Sach;

import java.util.List;

public class SachActivity extends AppCompatActivity {
    List<Sach> list;
    ListView lvSach;
    SachAdapter adapter;
    ImageView imgThem;
    Toolbar toolbar;
    String selectedLop = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach);
        lvSach=findViewById(R.id.lvSach);
        imgThem=findViewById(R.id.imgThemSach);
        toolbar=(Toolbar) findViewById(R.id.toolbarSach);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        list=new SachDao(this).get();
        adapter=new SachAdapter(this,list);
        lvSach.setAdapter(adapter);
        imgThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogThemSach();
            }
        });

    }
    public void DiaLogXoaSach(String ten){
        AlertDialog.Builder dialogXoa=new AlertDialog.Builder(SachActivity.this);
        dialogXoa.setMessage("Bạn có muốn xóa sinh viên "+ten+" này không");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SachDao dao=new SachDao(SachActivity.this);
                dao.delete(ten);
                list=new SachDao(SachActivity.this).get();
                adapter=new SachAdapter(SachActivity.this,list);
                lvSach.setAdapter(adapter);

            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialogXoa.show();

    }
    public void dialogSuaSach(String maSach) {
        Dialog dialog = new Dialog(SachActivity.this);
        dialog.setContentView(R.layout.dialog_suasach);
        dialog.setCanceledOnTouchOutside(false);
        Spinner spinnerMaLoaiSach=(Spinner) dialog.findViewById(R.id.spinnermaloaisach);
        EditText editTextTenSach=(EditText) dialog.findViewById(R.id.edtTenSach);
        EditText editTextGiaThue=(EditText) dialog.findViewById(R.id.edtGiaThueSach);
        EditText editTextsoLuong=(EditText) dialog.findViewById(R.id.edtsoLuong);
        Button button=(Button) dialog.findViewById(R.id.btnThemSach);

        List<LoaiSach> sachList=(new LoaiSachDao(this)).get();
        SpinnerMaLoaiSachAdapter tenSachAdapter=new SpinnerMaLoaiSachAdapter(this,sachList);
        spinnerMaLoaiSach.setAdapter(tenSachAdapter);
        spinnerMaLoaiSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LoaiSach maloai = (LoaiSach) tenSachAdapter.getItem(position);
                selectedLop=maloai.getMaLoai();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedLop=null;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String maLoai=selectedLop;
                String tenSach=editTextTenSach.getText().toString();
                String giaThue=editTextGiaThue.getText().toString();
                String soLuong=editTextsoLuong.getText().toString();
                int soluong=Integer.parseInt(soLuong);
                Sach sach=new Sach(maSach,tenSach,giaThue,maLoai,soluong);

                SachDao dao=new SachDao(SachActivity.this);
                dao.update(sach);
                list=new SachDao(SachActivity.this).get();
                adapter=new SachAdapter(SachActivity.this,list);
                lvSach.setAdapter(adapter);
            }
        });

        dialog.show();
    }


    public void dialogThemSach() {
        Dialog dialog = new Dialog(SachActivity.this);
        dialog.setContentView(R.layout.dialog_themsach);
        dialog.setCanceledOnTouchOutside(false);
        Spinner spinnerMaLoaiSach=(Spinner) dialog.findViewById(R.id.spinnermaloaisach);

        EditText editTextTenSach=(EditText) dialog.findViewById(R.id.edtTenSach);
        EditText editTextMaSach=(EditText) dialog.findViewById(R.id.edtMaSach);
        EditText editTextGiaThue=(EditText) dialog.findViewById(R.id.edtGiaThueSach);
        EditText editTextsoLuong=(EditText) dialog.findViewById(R.id.edtsoLuong);

        List<LoaiSach> sachList=(new LoaiSachDao(this)).get();
        SpinnerMaLoaiSachAdapter tenSachAdapter=new SpinnerMaLoaiSachAdapter(this,sachList);
        spinnerMaLoaiSach.setAdapter(tenSachAdapter);
        spinnerMaLoaiSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LoaiSach maloai = (LoaiSach) tenSachAdapter.getItem(position);
                selectedLop=maloai.getMaLoai();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedLop=null;
            }
        });

        Button button=(Button) dialog.findViewById(R.id.btnThemSach);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String maLoai=selectedLop;
                String tenSach=editTextTenSach.getText().toString();
                String giaThue=editTextGiaThue.getText().toString();
                String maSach=editTextMaSach.getText().toString();
                String soLuong=editTextsoLuong.getText().toString();
                if (tenSach.isEmpty()||giaThue.isEmpty()||maSach.isEmpty()||soLuong.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Không được bỏ trống", Toast.LENGTH_SHORT).show();
                }else {
                    int soluong = Integer.parseInt(soLuong);
                    Sach sach = new Sach(maSach, tenSach, giaThue, maLoai, soluong);
                    SachDao dao = new SachDao(SachActivity.this);
                    dao.insert(sach);
                    list = new SachDao(SachActivity.this).get();
                    adapter = new SachAdapter(SachActivity.this, list);
                    lvSach.setAdapter(adapter);
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }


}